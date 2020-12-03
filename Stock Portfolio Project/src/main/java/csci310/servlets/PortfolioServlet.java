package csci310.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.BasicConfigurator;

import com.google.api.client.util.IOUtils;
import com.google.api.core.ApiFuture;
import com.google.api.core.SettableApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.opencsv.CSVReader;

//import csci310.CSVParser;
import csci310.DatePrice;
import csci310.Person;
import csci310.Stock;
import csci310.Stock_api;


//@WebServlet(name = "PortfolioServlet", urlPatterns = {"/UploadFile"},
//initParams = { @WebInitParam(name = "uploadpath", value = "/var/www/upload/") })
//@MultipartConfig
@WebServlet("/PortfolioServlet")
public class PortfolioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file;

	public PortfolioServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		System.out.println("Portfolio servlet triggered");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		String year = dtf.format(now).substring(0,4);
		String month = dtf.format(now).substring(5,7);
		String day = dtf.format(now).substring(8,10);
		int lastYear = Integer.parseInt(year) - 1;
		String startYear = Integer.toString(lastYear);
		String startDate = startYear + "-" + month + "-" + day;
		String endDate = year + "-" + month + "-" + day;
		System.out.println(startDate); 
		System.out.println(endDate);
		int currMonth = Integer.parseInt(month);
		int currDay = Integer.parseInt(day);
		
//		add stock to portfolio
		if(request.getParameter("stockCode") != null && request.getParameter("watchlist") == null) {
			String curr_price_code = request.getParameter("stockCode");
			String stockCode = request.getParameter("stockCode");
			stockCode = stockCode.toUpperCase();
			System.out.println(stockCode);
			boolean dateCheck = true;
			
			DatePrice myDP  = Stock_api.stock_price_with_timerange(curr_price_code, startDate,endDate);
			List<Double> intraday_open_price = myDP.getPrice();
//			List<Double> intraday_open_price = Stock_api.stock_price(curr_price_code, "5min");
			String curr_price = "";
			if(intraday_open_price.size() == 0) {
				response.getWriter().write("Please enter the right stock code");
				request.setAttribute("errorMessage", "Please enter the right stock code");
				return;
			}
			else {
				curr_price = intraday_open_price.get(intraday_open_price.size() - 1).toString();
//				response.getWriter().write(curr_price);
				System.out.println("current price = " + curr_price);

			}


		
//			String currPrice = request.getParameter("currPrice");
			String numShares = request.getParameter("numShares");

			int shares = Integer.parseInt(numShares);
			System.out.println("num share = " + numShares);
			if (shares < 1) {
				dateCheck = false;
				response.getWriter().write("Please enter the number of shares to buy.");
				request.setAttribute("errorMessage", "Please enter a valid number.");
			}
			if(request.getParameter("buyInDate").isEmpty()) {
				response.getWriter().write("Please enter the purchase date.");
			}
			else {
				String buyDay = request.getParameter("buyInDate");
				String sellDay = "";
				System.out.println(buyDay); 
				buyDay = buyDay.replace("-", "");
				System.out.println(buyDay); 
	
				double buyInDate = Double.parseDouble(buyDay);
				
//						check the purchased data is within one year
				int buyInDate_ =  (int) buyInDate;
				int buyInYear = Integer.parseInt(Integer.toString(buyInDate_).substring(0, 4));
				int buyInMonth = Integer.parseInt(Integer.toString(buyInDate_).substring(4, 6));
				int buyInDay = Integer.parseInt(Integer.toString(buyInDate_).substring(6, 8));
				System.out.println(buyInYear + " " + buyInMonth + " "+ buyInDay);
				if(buyInYear < lastYear || (buyInYear == lastYear && buyInMonth < currMonth) || (buyInYear == lastYear && buyInMonth == currMonth && buyInDay < currDay)) {
					dateCheck = false;
					response.getWriter().write("Buy in date must be within the last year!");
					
//							check date
					
					
					
				}
			
				else if(request.getParameter("sellDate") != null && !request.getParameter("sellDate").isEmpty()) {
						sellDay = request.getParameter("sellDate");
						System.out.println(sellDay);
						sellDay = sellDay.replace("-", "");
						System.out.println("buy in date>>>>>>>>>>>>>");
	
						double sellDate = Double.parseDouble(sellDay);
						
	//								check the sell data is within one year
						int sellDate_ = (int) sellDate;
						int sellYear_ = Integer.parseInt(Integer.toString(sellDate_).substring(0, 4));
						int sellMonth_ = Integer.parseInt(Integer.toString(sellDate_).substring(4, 6));
						int sellDay_ = Integer.parseInt(Integer.toString(sellDate_).substring(6, 8));
						if((sellYear_ < buyInYear || (sellYear_ == buyInYear && sellMonth_ < buyInMonth) || (sellYear_ == buyInYear && sellMonth_ == buyInMonth && sellDay_ < buyInDay))) {
							dateCheck = false;
							response.getWriter().write("Sell date must after buy in date!");
	//									check date
							
							
						}
						
						
					
				}
				
			}
			
			if(dateCheck) {
				
				// check to see if account exists in Firebase
				BasicConfigurator.configure();
	
				// Use the application default credentials
				InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("credentials2.json");
				GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
				FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(credentials).build();
				if (FirebaseApp.getApps().isEmpty()) {
					FirebaseApp.initializeApp(options);
				}
				Firestore db = FirestoreClient.getFirestore();
				// DocumentSnapshot docSp = db.getResult();
				System.out.println(username);
				DocumentReference docRef = db.collection("users").document(username);
				ApiFuture<DocumentSnapshot> future = docRef.get();
	
				// QuerySnapshot querySnapshot;
				// querySnapshot = query.get();
				// ApiFuture<DocumentSnapshot> query =
				// db.collection("users").document(username).get();
				// Iterable<CollectionReference> collections =
				// db.collection("users").document(username).collection("");
				// QuerySnapshot querySnapshot;
				Person p = null;
				DocumentSnapshot document;
				
				try {
					document = future.get();
					p = document.toObject(Person.class);
					System.out.println("serialization successful");
					
					Map<String, List<Double>> tempStock = new HashMap<String, List<Double>>();
					tempStock = p.getStocks();
					List<Double> stuff = new ArrayList<Double>();
					if(!tempStock.containsKey(stockCode)) {
						
						
						stuff.add(Double.parseDouble(curr_price));
						stuff.add(Double.parseDouble(numShares));
						
//						String buyDay = request.getParameter("buyInDate");
//						String sellDay = request.getParameter("sellDate");
//						DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//						LocalDateTime ldt2 = LocalDateTime.now();
//						System.out.println(dtf.format(now));
						if(request.getParameter("buyInDate").isEmpty()) {
							response.getWriter().write("Please enter the purchase date.");
						}
						else {
							String buyDay = request.getParameter("buyInDate");
							String sellDay = "";
							System.out.println(buyDay); 
							buyDay = buyDay.replace("-", "");
							System.out.println(buyDay); 
				
							double buyInDate = Double.parseDouble(buyDay);
							stuff.add(buyInDate);
							
							if(request.getParameter("sellDate") != null && !request.getParameter("sellDate").isEmpty()) {
								sellDay = request.getParameter("sellDate");
								System.out.println(sellDay);
								sellDay = sellDay.replace("-", "");
								System.out.println("buy in date>>>>>>>>>>>>>");
		
								double sellDate = Double.parseDouble(sellDay);

								stuff.add(sellDate);
							}
								
								

								System.out.println("update check");
								tempStock.put(stockCode, stuff);
								Map<String, Object> update = new HashMap<>();
								update.put("username", p.getUsername());
								update.put("password", p.getPassword());
								update.put("validLoginTime", p.getValidLoginTime());
								update.put("attempts", p.getAttempts());
								update.put("stocks", tempStock);
								update.put("locked", p.getLocked());
								update.put("sixtySecPassed", p.getSixtySecPassed());
								//update.put("attempts", p.getAttempts());
								System.out.println("before fire");
								ApiFuture<com.google.cloud.firestore.WriteResult> writeResult = db.collection("users").document(username).set(update, SetOptions.merge());
								System.out.println(p.getStocks());
								System.out.println("after fire");
								System.out.println(
											"add stock " + stockCode + " at price = " + curr_price + " with num of shares " + numShares);
								response.getWriter().write("success");
								request.setAttribute("currPrice", curr_price);
								

							
						}
					}
					else {
//						request.setAttribute("errorMessage", "Stock already exist.");
						response.getWriter().write("Stock already exist.");
					}
//					if (document.exists()) {
//						// convert document to POJO
//						p = document.toObject(Person.class);
//						System.out.println("serialization successful");
//						
//					} else {
//						System.out.println("No such document!");
//					}
				} catch (Exception e) {
					System.out.println("block failed");
					response.getWriter().write("fail");
				}
				
			}
		}
		
		else if(request.getParameter("stockToDelete") != null) {
			System.out.println("inside deletion");
			String stockToDelete = request.getParameter("stockToDelete");
			
			// check to see if account exists in Firebase
			BasicConfigurator.configure();

			// Use the application default credentials
			InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("credentials2.json");
			GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
			FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(credentials).build();
			if (FirebaseApp.getApps().isEmpty()) {
				FirebaseApp.initializeApp(options);
			}
			Firestore db = FirestoreClient.getFirestore();
			// DocumentSnapshot docSp = db.getResult();
			System.out.println(username);
			DocumentReference docRef = db.collection("users").document(username);
			ApiFuture<DocumentSnapshot> future = docRef.get();

			// QuerySnapshot querySnapshot;
			// querySnapshot = query.get();
			// ApiFuture<DocumentSnapshot> query =
			// db.collection("users").document(username).get();
			// Iterable<CollectionReference> collections =
			// db.collection("users").document(username).collection("");
			// QuerySnapshot querySnapshot;
			Person p = null;
			DocumentSnapshot document;
			
			try {
				document = future.get();
				p = document.toObject(Person.class);
				System.out.println("serialization successful");
				
				Map<String, List<Double>> tempStock = new HashMap<String, List<Double>>();
				Map<String, Object> update = new HashMap<>();
				if(request.getParameter("watchListDelete") != null) {
					tempStock = p.getWatchList();
					System.out.println(stockToDelete);
					tempStock.remove(stockToDelete);
					
					update.put("username", p.getUsername());
					update.put("password", p.getPassword());
					update.put("validLoginTime", p.getValidLoginTime());
					update.put("attempts", p.getAttempts());
					update.put("stocks", p.getStocks());
					update.put("watchList", tempStock);
					update.put("locked", p.getLocked());
					update.put("sixtySecPassed", p.getSixtySecPassed());
				}
				else {
					tempStock = p.getStocks();
	//				List<Double> stuff = new ArrayList<Double>();
					System.out.println(stockToDelete);
					tempStock.remove(stockToDelete);
					
	//				Map<String, Object> update = new HashMap<>();
		
					update.put("username", p.getUsername());
					update.put("password", p.getPassword());
					update.put("validLoginTime", p.getValidLoginTime());
					update.put("attempts", p.getAttempts());
					update.put("stocks", tempStock);
					update.put("watchList", p.getWatchList());
					update.put("locked", p.getLocked());
					update.put("sixtySecPassed", p.getSixtySecPassed());
					//update.put("attempts", p.getAttempts());
	//				update.put("stocks", FieldValue.delete());
				}
				
				System.out.println("before fire");
				ApiFuture<com.google.cloud.firestore.WriteResult> writeResult = db.collection("users").document(username).set(update);
				System.out.println(p.getStocks());
				System.out.println("after fire");
				
				System.out.println("remove stock " + stockToDelete);
				response.getWriter().write("success");
				
			} catch (Exception e) {
				System.out.println("block failed");
				response.getWriter().write("fail");
			}
		}
		
		else if(request.getParameter("watchlist") != null) {
			System.out.println("inside add watchlist");
			ArrayList<Stock> watchList = (ArrayList<Stock>)session.getAttribute("watchList");
			
			String curr_price_code = request.getParameter("stockCode");
			String stockCode = request.getParameter("stockCode");
			stockCode = stockCode.toUpperCase();
			boolean dateCheck = true;
			System.out.println(stockCode);
			
			DatePrice myDP  = Stock_api.stock_price_with_timerange(curr_price_code, startDate,endDate);
			List<Double> intraday_open_price = myDP.getPrice();
//			List<Double> intraday_open_price = Stock_api.stock_price(curr_price_code, "5min");
			String curr_price = "";
			if(intraday_open_price.size() == 0) {
				response.getWriter().write("Please enter the right stock code");
				request.setAttribute("errorMessage", "Please enter the right stock code");
				return;
			}
			else {
				curr_price = intraday_open_price.get(intraday_open_price.size() - 1).toString();
//				response.getWriter().write(curr_price);
				System.out.println("current price = " + curr_price);

			}

			
		
//			String currPrice = request.getParameter("currPrice");
			String numShares = request.getParameter("numShares");

			int shares = Integer.parseInt(numShares);
			System.out.println("num share = " + numShares);
			if (shares < 1) {
				dateCheck = false;
				response.getWriter().write("Please enter the number of shares to buy.");
				request.setAttribute("errorMessage", "Please enter a valid number.");
			}
			if(request.getParameter("buyInDate").isEmpty()) {
				response.getWriter().write("Please enter the purchase date.");
			}
			else {
				String buyDay = request.getParameter("buyInDate");
				String sellDay = "";
				System.out.println(buyDay); 
				buyDay = buyDay.replace("-", "");
				System.out.println(buyDay); 
	
				double buyInDate = Double.parseDouble(buyDay);
				
//						check the purchased data is within one year
				int buyInDate_ =  (int) buyInDate;
				int buyInYear = Integer.parseInt(Integer.toString(buyInDate_).substring(0, 4));
				int buyInMonth = Integer.parseInt(Integer.toString(buyInDate_).substring(4, 6));
				int buyInDay = Integer.parseInt(Integer.toString(buyInDate_).substring(6, 8));
				System.out.println(buyInYear + " " + buyInMonth + " "+ buyInDay);
				if(buyInYear < lastYear || (buyInYear == lastYear && buyInMonth < currMonth) || (buyInYear == lastYear && buyInMonth == currMonth && buyInDay < currDay)) {
					dateCheck = false;
					response.getWriter().write("Buy in date must be within the last year!");
					
//							check date
					
					
					
				}
			
				else if(request.getParameter("sellDate") != null && !request.getParameter("sellDate").isEmpty()) {
						sellDay = request.getParameter("sellDate");
						System.out.println(sellDay);
						sellDay = sellDay.replace("-", "");
						System.out.println("buy in date>>>>>>>>>>>>>");
	
						double sellDate = Double.parseDouble(sellDay);
						
	//								check the sell data is within one year
						int sellDate_ = (int) sellDate;
						int sellYear_ = Integer.parseInt(Integer.toString(sellDate_).substring(0, 4));
						int sellMonth_ = Integer.parseInt(Integer.toString(sellDate_).substring(4, 6));
						int sellDay_ = Integer.parseInt(Integer.toString(sellDate_).substring(6, 8));
						if((sellYear_ < buyInYear || (sellYear_ == buyInYear && sellMonth_ < buyInMonth) || (sellYear_ == buyInYear && sellMonth_ == buyInMonth && sellDay_ < buyInDay))) {
							dateCheck = false;
							response.getWriter().write("Sell date must after buy in date!");
	//									check date
							
							
						}
						
						
					
				}
				
			}
			
			if(dateCheck) {
				
				// check to see if account exists in Firebase
				BasicConfigurator.configure();
	
				// Use the application default credentials
				InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("credentials2.json");
				GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
				FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(credentials).build();
				if (FirebaseApp.getApps().isEmpty()) {
					FirebaseApp.initializeApp(options);
				}
				Firestore db = FirestoreClient.getFirestore();
				// DocumentSnapshot docSp = db.getResult();
				System.out.println(username);
				DocumentReference docRef = db.collection("users").document(username);
				ApiFuture<DocumentSnapshot> future = docRef.get();
	
				// QuerySnapshot querySnapshot;
				Person p = null;
				DocumentSnapshot document;
				
				try {
					document = future.get();
					p = document.toObject(Person.class);
					System.out.println("serialization successful");
					
					Map<String, List<Double>> tempWatchList = new HashMap<String, List<Double>>();
					tempWatchList = p.getWatchList();
					List<Double> stuff = new ArrayList<Double>();
					if(!tempWatchList.containsKey(stockCode)) {
						
	
						stuff.add(Double.parseDouble(curr_price));
						stuff.add(Double.parseDouble(numShares));
						
//						String buyDay = request.getParameter("buyInDate");
//						String sellDay = request.getParameter("sellDate");
//						DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//						LocalDateTime ldt2 = LocalDateTime.now();
//						System.out.println(dtf.format(now));
						if(request.getParameter("buyInDate").isEmpty()) {
							response.getWriter().write("Please enter the purchase date.");
						}
						else {
							String buyDay = request.getParameter("buyInDate");
							String sellDay = "";
							System.out.println(buyDay); 
							buyDay = buyDay.replace("-", "");
							System.out.println(buyDay); 
				
							double buyInDate = Double.parseDouble(buyDay);

								stuff.add(buyInDate);
								if(request.getParameter("sellDate") != null && !request.getParameter("sellDate").isEmpty()) {
									sellDay = request.getParameter("sellDate");
									System.out.println(sellDay);
									sellDay = sellDay.replace("-", "");
									System.out.println("buy in date>>>>>>>>>>>>>");
		
									double sellDate = Double.parseDouble(sellDay);
									stuff.add(sellDate);
										
										
									}
									

								
									System.out.println("update check");
									tempWatchList.put(stockCode, stuff);
									Map<String, Object> update = new HashMap<>();
									update.put("username", p.getUsername());
									update.put("password", p.getPassword());
									update.put("validLoginTime", p.getValidLoginTime());
									update.put("attempts", p.getAttempts());
									update.put("stocks", p.getStocks());
									update.put("watchList", tempWatchList);
									update.put("locked", p.getLocked());
									update.put("sixtySecPassed", p.getSixtySecPassed());
									//update.put("attempts", p.getAttempts());
									System.out.println("before fire");
									ApiFuture<com.google.cloud.firestore.WriteResult> writeResult = db.collection("users").document(username).set(update, SetOptions.merge());
									System.out.println(p.getStocks());
									System.out.println("after fire");
									System.out.println(
											"add stock to watchlist" + stockCode + " at price = " + curr_price + " with num of shares " + numShares);
									response.getWriter().write("success");
									request.setAttribute("currPrice", curr_price);
							}
							
						
					}
					else {
//						request.setAttribute("errorMessage", "Stock already exist.");
						response.getWriter().write("Stock already exist.");
					}
//					if (document.exists()) {
//						// convert document to POJO
//						p = document.toObject(Person.class);
//						System.out.println("serialization successful");
//						
//					} else {
//						System.out.println("No such document!");
//					}
				} catch (Exception e) {
					System.out.println("block failed");
					response.getWriter().write("fail");
				}
			}
				
				

			
		}
		
	}

}
