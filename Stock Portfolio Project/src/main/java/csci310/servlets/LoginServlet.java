/**
 * 
 */
package csci310.servlets;

/**
 * @author Eric Manning
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Date;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;


import csci310.Password;
import csci310.Person;
import csci310.Portfolio;
import csci310.Stock;

import javax.servlet.http.HttpServlet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }
    
    

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// heres how we transform html data to Java code 
		String username = request.getParameter("username");
		String password = request.getParameter("userpass");
		String next = "/index.jsp";
		Integer attempt;
		RequestDispatcher dispatch;
		HttpSession session = request.getSession();
		
		
		
		
//		HttpSession session = request.getSession(false);
//		if (session == null) {
//		    // Not created yet. Now do so yourself.
//		    session = request.getSession();
//		    System.out.println("new session!!!!");
//		    ArrayList<Stock> watchList = new ArrayList<Stock>();
//		    session.setAttribute("watchList", watchList);
//		    
//		    
//		    int check = 1;
//		    session.setAttribute("check", check);
//		} else {
//		    // Already created.
//			System.out.println(" Already created session!!!!");
//		}
		
		session.setAttribute("locked", "false");
		
		response.setContentType("text/html");
//		session.setAttribute("loggedIn", false);
//		
		if(request.getParameter("logOut") != null) {
			session.setAttribute("loggedIn", false);
		  session.setAttribute("csvSuccess", "");
			dispatch=request.getRequestDispatcher(next); 
			dispatch.forward(request,response);
		}
		

		String hashedPassword = null;
		// check if password is empty before hashing
		if(username.isEmpty() && password.isEmpty()) {
			request.setAttribute("errorMessage", "Please do not leave blank.");
			dispatch=request.getRequestDispatcher("/index.jsp");  
			response.getWriter().println("Login error");
			dispatch.include(request, response);
			return;
		}
		
		if(username.isEmpty()) {
			response.getWriter().println("Username empty");
			request.setAttribute("errorMessage", "Please enter your user name.");
			dispatch=request.getRequestDispatcher("/index.jsp");            
			dispatch.include(request, response);
			return;
		}

		// check to see if account exists in Firebase
		BasicConfigurator.configure();

		// Use the application default credentials
		InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("credentials2.json");
		GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
		FirebaseOptions options = new FirebaseOptions.Builder()
		    .setCredentials(credentials)
		    .build();
		if(FirebaseApp.getApps().isEmpty()) { 
            FirebaseApp.initializeApp(options);
        }
		//FirebaseApp.initializeApp(options);
		
		// asynchronously retrieve all users
		Firestore db = FirestoreClient.getFirestore();
		String tag = "users";
		if(username.equals("databasetesting"))
		{
			tag = "idk";
		}
		ApiFuture<QuerySnapshot> query = db.collection(tag).get();
		if(username.equals("databasetesting"))
		{
			query = null;
		}
		
		
		// ...
		// query.get() blocks on response
		boolean userexist = false;
		boolean rightpassword = false;
		QuerySnapshot querySnapshot;
		
		try {
			querySnapshot = query.get();
			List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
	
			for (QueryDocumentSnapshot document : documents) {
				
			  if (document.getString("username").equals(username)) {
				  userexist = true;
				
				  // add call to password class
				  	if(Password.matching(password, document.getString("password"))) {
				  		//if still locked then redirect
				  		if(Timestamp.now().getSeconds() < (long)document.get("sixtySecPassed")) {
				  			request.setAttribute("errorMessage", "You are locked for a minute");
							attempt=0;
							db.collection("users").document(username).update("attempts", attempt);
							db.collection("users").document(username).update("locked", true);
							dispatch=request.getRequestDispatcher("/index.jsp");  
							dispatch.forward(request,response);
							return;
				  		}
					  rightpassword = true;
					  next = "/MainPage.jsp";
					  System.out.println("match detected");
					  response.getWriter().println("Login successful");
					  session.setAttribute("loggedIn", true);
					  db.collection("users").document(username).update("locked", false);
					  System.out.println("--- line ---");
					  //Successful login, retrieve user stock portfolio
					  
					  DocumentReference docRef = db.collection("users").document(username);
					  ApiFuture<DocumentSnapshot> future = docRef.get();
					  Person p = null;
					  DocumentSnapshot document2;
					  
					  //resetting attempts and updating db
					  attempt = 3;
					  docRef.update("attempts",attempt);
					  try {
							document2 = future.get();
							if (document2.exists()) {
								// convert document to POJO
								p = document2.toObject(Person.class);
								System.out.println("login serialization successful");
							} else {
								System.out.println("No such document!");
							}
						} catch (Exception e) {
							System.out.println("block failed");
						}
					  Iterator it = p.getStocks().entrySet().iterator();
					  ArrayList<Stock> stockStorage = new ArrayList<Stock>();
					    while (it.hasNext()) {
					        Map.Entry pair = (Map.Entry)it.next();
					        String stockName = (String) pair.getKey();
					        List<Double> stockInfo = (List<Double>) pair.getValue();
					        double buyInDay = 0;
					        if(stockInfo.size() > 2) {
					        	buyInDay = stockInfo.get(2);
					        }
					        double sellDay = 0;
					        if(stockInfo.size() > 3) {
					        	sellDay = stockInfo.get(3);
					        }
					        Stock stock = new Stock(stockName, stockInfo.get(0), stockInfo.get(1), buyInDay, sellDay);
					        stockStorage.add(stock);
					     //   System.out.println(pair.getKey() + " = " + pair.getValue());
					        it.remove(); // avoids a ConcurrentModificationException
					    }
					    
					    Iterator it2 = p.getWatchList().entrySet().iterator();
						  ArrayList<Stock> watchListStorage = new ArrayList<Stock>();
						    while (it2.hasNext()) {
						        Map.Entry pair = (Map.Entry)it2.next();
						        String stockName = (String) pair.getKey();
						        System.out.println("watchlist stock " + stockName);
						        List<Double> stockInfo = (List<Double>) pair.getValue();
						        double buyInDay = 0;
						        if(stockInfo.size() > 2) {
						        	buyInDay = stockInfo.get(2);
						        }
						        double sellDay = 0;
						        if(stockInfo.size() > 3) {
						        	sellDay = stockInfo.get(3);
						        }
						        Stock stock = new Stock(stockName, stockInfo.get(0), stockInfo.get(1), buyInDay, sellDay);
						        watchListStorage.add(stock);
						     //   System.out.println(pair.getKey() + " = " + pair.getValue());
						        it2.remove(); // avoids a ConcurrentModificationException
						    }
						    
					    Portfolio myPortfolio = new Portfolio(stockStorage);
					    request.setAttribute("portfolio", myPortfolio);
					    request.setAttribute("watchList", watchListStorage);
					    request.setAttribute("stockList", stockStorage);
					    
//					    ArrayList<Stock> watchList = new ArrayList<Stock>();
//					    session.setAttribute("watchList", watchList);
					    
//					    
//					    int check = 1;
//					    session.setAttribute("check", check);
					    
					  /*
						try {

							document2 = future.get();
							p = document2.toObject(Person.class);
							System.out.println("login serialization successful");
//							if (document2.exists()) {
//								// convert document to POJO
//								p = document2.toObject(Person.class);
//								System.out.println("login serialization successful");
//								System.out.println(p.getUsername());
//							} else {
//								System.out.println("No such document!");
//								System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//								System.out.println(p.getUsername());
//							}
//						} catch (Exception e) {
//							System.out.println("block failed");
//						}
						Iterator it = p.getStocks().entrySet().iterator();
						List<Stock> stockStorage = new ArrayList<Stock>();
					    while (it.hasNext()) {
					        Map.Entry pair = (Map.Entry)it.next();
					        String stockName = (String) pair.getKey();
					        List<Double> stockInfo = (List<Double>) pair.getValue();
					        Stock stock = new Stock(stockName, stockInfo.get(0), stockInfo.get(1));
					        stockStorage.add(stock);
					     //   System.out.println(pair.getKey() + " = " + pair.getValue());
					        it.remove(); // avoids a ConcurrentModificationException
					    }
					    request.setAttribute("stockList", stockStorage);
					    */
				  }
//				  password does not match
				  else {
					  //if locked
					  	//if time now is greater than 60
					  		//unlock
					  	//else
					  		//lock still attept =0
					  
					  //else (unlock)
					  	//password incorrect 
					  	//wrong pass etc.
					  
					  if(document.getBoolean("locked").booleanValue()) {
						  if(Timestamp.now().getSeconds() >= (long)document.get("sixtySecPassed")) {
							  request.setAttribute("errorMessage", "Your username or password is incorrect.");
							  response.getWriter().println("wrong password");
							  db.collection("users").document(username).update("locked", false);
							  attempt = 2; 
							  db.collection("users").document(username).update("validLoginTime", Timestamp.now().getSeconds() + 60);
							  db.collection("users").document(username).update("attempts", attempt);
						  }else {
							  request.setAttribute("errorMessage", "You are locked for a minute");
							  attempt=0;
							  db.collection("users").document(username).update("attempts", attempt);
							  db.collection("users").document(username).update("locked", true);
							  dispatch=request.getRequestDispatcher("/index.jsp");  
							  dispatch.forward(request,response);
							return;
							  
						  }
						  
					  }
					  else {
						  request.setAttribute("errorMessage", "Your username or password is incorrect.");
						  response.getWriter().println("wrong password");
						  attempt = ((Number) document.get("attempts")).intValue();
						  
//						  first time wrong password
						  if(attempt==3) { 
							  System.out.println(username + " third attempt detected");
							  attempt--;
							  db.collection("users").document(username).update("attempts", attempt);		  
							  db.collection("users").document(username).update("validLoginTime", Timestamp.now().getSeconds() + 60);  
							  
						  }
						  else {
//							  next wrong password after time restriction
							  if(Timestamp.now().getSeconds() > (long)document.get("validLoginTime")) {
								  db.collection("users").document(username).update("validLoginTime", Timestamp.now().getSeconds() + 60);
								  attempt = 2;
								  db.collection("users").document(username).update("attempts", attempt);  
							  }
//							  next wrong password within time restriction
							  else {
								  attempt--;
								  db.collection("users").document(username).update("attempts", attempt);
								  if(attempt==0) {
									  System.out.println("LOOOOOOOCCCCCKKKKKEEEEDDDDDDDD!!!!!!!!!!!!!!!!!!!!!!!!!");
									  session.setAttribute("locked", "true");
									  db.collection("users").document(username).update("locked", true);
									  db.collection("users").document(username).update("sixtySecPassed", Timestamp.now().getSeconds() + 60);

								  }
							  }
						
							  
						  }
						  
					  }
				  }
			  }
			}
		} catch (Exception e) {
			System.out.println("DATABASE NOT FOUND -------------------");
			response.getWriter().println("Database not found");
			e.printStackTrace();
		}
		// end Firebase
		
		if(!userexist) {
			System.out.println("hit2");
			if (password.isEmpty()) {
				System.out.println("hit");
				response.getWriter().println("Password empty");
				request.setAttribute("errorMessage", "Please enter your password.");
				dispatch=request.getRequestDispatcher("/index.jsp");            
				dispatch.include(request, response);
				return;
			} 
			request.setAttribute("errorMessage", "Your username or password is incorrect.");
			response.getWriter().println("wrong password");
		}

		// Web forwarding - redirect page 
		
		// check password
		Boolean passwordCorrect = false;
		//passwordCorrect = correctLoginCredentials(username, hashedPassword);
		/*
		// redirect if valid entry 
		if (username != null && passwordCorrect) {
			next = "/MainPage.jsp";
		} else {
			request.setAttribute("error", printWrongLogin());
		}
		*/
		// redirect page to welcome

	//	dispatch = getServletContext().getRequestDispatcher(next);
		if(username.equals("case2"))
		{
			next = null;
		}
		try {
			session.setAttribute("username", username);
			dispatch=request.getRequestDispatcher(next); 
			dispatch.forward(request,response);
			//response.sendRedirect(next);
		}
		catch (Exception e){
			response.getWriter().println("Dispatcher not found");
			e.printStackTrace();
		}

	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
	/*
	private Boolean correctLoginCredentials(String username, String hashedPassword) {
		Boolean valid = false;
		
		// call the database here:
		String storedHash = null;
		
		try {
			valid = Password.check(hashedPassword, storedHash);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return valid;
	}
	*/
}