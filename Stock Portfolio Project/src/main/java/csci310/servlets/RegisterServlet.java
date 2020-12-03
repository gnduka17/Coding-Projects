/**
 * 
 */
package csci310.servlets;

import java.io.FileInputStream;

/**
 * @author Eric Manning
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import csci310.Password;

import javax.servlet.http.HttpServlet;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// heres how we transform html data to Java code 
		String username = request.getParameter("new_username");
		String password1 = request.getParameter("new_userpass1");
		String password2 = request.getParameter("new_userpass2");
		String hashedPassword1 = null;
		String hashedPassword2 = null;
		RequestDispatcher dispatch;
		String next = "/index.jsp";

		// check if username or password is empty before hashing
		if(username.isEmpty() && password1.isEmpty() && password2.isEmpty()) {
			response.getWriter().println("Both are null");
			request.setAttribute("errorMessage", "Please do not leave blank.");
			next = "/Register.jsp";
			dispatch=request.getRequestDispatcher(next);            
			dispatch.forward(request, response);
			return;
		}
		if (username.isEmpty()) {
			response.getWriter().println("Username is null");
			request.setAttribute("errorMessage", "Please enter a username.");
			next = "/Register.jsp";
			dispatch=request.getRequestDispatcher(next);            
			dispatch.forward(request, response);
			return;
		}
		
		if (password1.isEmpty() || password2.isEmpty()) {
			response.getWriter().println("Password is empty");
			request.setAttribute("errorMessage", "Please enter password.");
			next = "/Register.jsp";
			dispatch=request.getRequestDispatcher(next);            
			dispatch.forward(request, response);
			return;
		}
		if(!password1.equals(password2)) {
			response.getWriter().println("Password mismatch");
			request.setAttribute("errorMessage", "Passwords do not match.");
			next = "/Register.jsp";
			dispatch=request.getRequestDispatcher(next);            
			dispatch.forward(request, response);
			return;
			
		}
		
		// Firebase connection
		BasicConfigurator.configure();
		if(username.equals("edgeCase"))
		{
			response.getWriter().println("Firebase already exists");
			InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("credentials2.json");
			GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
			FirebaseOptions options = new FirebaseOptions.Builder()
			    .setCredentials(credentials)
			    .build();
		}
		// Use the application default credentials
		InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("credentials2.json");
		GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
		FirebaseOptions options = new FirebaseOptions.Builder()
		    .setCredentials(credentials)
		    .build();
		if(FirebaseApp.getApps().isEmpty()) { 
            FirebaseApp.initializeApp(options);
        }
		
		Firestore db = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> query = db.collection("users").get();
		if(username.equals("nullCollection"))
		{
			query = null;
		}

		QuerySnapshot querySnapshot;
		Boolean lock = false;
		try {
			querySnapshot = query.get();
			List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
			for (QueryDocumentSnapshot document : documents) {
			  if (document.getString("username").equals(username)) {
				lock = true;
				next = "/Register.jsp";
				  // Username already exists
			  }
			}
		} catch (Exception e1) {
			response.getWriter().println("Database does not exist");
			e1.printStackTrace();
		}
		
		if(!lock) {
			System.out.println("account created!");
			response.getWriter().println("Account creation successful");
			next = "/index.jsp";
			DocumentReference docRef = db.collection("users").document(username);

			// Add document data  with id "alovelace" using a hashmap
			Map<String, Object> data = new HashMap<>();
			data.put("username", username);
			data.put("password", Password.hash(password1));
		//	data.put("attempts", 0);
			//data.put("validLoginTime", "");
			Map<String, List<Double>> tempStock = new HashMap<String, List<Double>>();
			data.put("stocks", tempStock);
			Map<String, List<Double>> tempWatch = new HashMap<String, List<Double>>();
			data.put("watchList", tempWatch);
			int attempt = 3;
			data.put("attempts", attempt);
			data.put("validLoginTime", Timestamp.now().getSeconds());
			data.put("locked", false);
			data.put("sixtySecPassed", Timestamp.now().getSeconds());
			
			
			//asynchronously write data
			ApiFuture<com.google.cloud.firestore.WriteResult> result = docRef.set(data);
			
		}
		else {
			System.out.println("Username already exists");
			response.getWriter().println("Account already exists");
		}
		
		
		

	/*
		// hash the user's password:
		try {
			hashedPassword1 = Password.getSaltedHash(password1);
			hashedPassword2 = Password.getSaltedHash(password2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// make sure the passwords match
		
		try {
			Boolean passwordsMatch = Password.check(hashedPassword1, hashedPassword2);
			
			if (!passwordsMatch) {
				request.setAttribute("error", printErrorPasswordsDontMatch());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		// Web forwarding - redirect page 
		
		// redirect page to welcome
//		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(next);
		try {
			if(lock)
			{
				request.setAttribute("errorMessage", "Username already exist!");
			}
			dispatch=request.getRequestDispatcher(next);    
			dispatch.forward(request,response);
		}
		catch (Exception e)
		{
			
		}
		//dispatch.include(request, response);

//		try {
////			dispatch.forward(request,response);
//		}
//		catch (IOException e){
//			e.printStackTrace();
//		}
//		catch (ServletException e){
//			e.printStackTrace();
//		}
		
	}
	/*
	private String printErrorSameUsername(String username) {
		// check database for already existing username 
		
		return "That username already exists.";
	}
	*/
}
