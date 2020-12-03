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
import csci310.Portfolio;
import csci310.Stock;
import csci310.Stock_api;

//@WebServlet(name = "PortfolioServlet", urlPatterns = {"/UploadFile"},
//initParams = { @WebInitParam(name = "uploadpath", value = "/var/www/upload/") })
//@MultipartConfig
@WebServlet("/fixRefresh")
public class FixRefreshServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public FixRefreshServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("Refresh success");

		//System.out.println("hit called");
        HttpSession session = request.getSession();
        String param1 = (String) request.getSession().getAttribute("username");
        ArrayList<Stock> stockStorage = (ArrayList<Stock>)request.getSession().getAttribute("stockList");
        ArrayList<Stock> watchList = (ArrayList<Stock>)request.getSession().getAttribute("watchList");
        ArrayList<Stock> stockStorage2 = new ArrayList<Stock>();
        ArrayList<Stock> watchList2 = new ArrayList<Stock>();
        String message = "";
        if(request.getSession().getAttribute("csvSuccess") != null)
        {
        	System.out.println("not null");
        	String temp = (String)request.getSession().getAttribute("csvSuccess");
        	if(!temp.isEmpty())
        	{
        		message = temp;
        	}
        }
        Portfolio myPortfolio = new Portfolio(stockStorage);
        
        refreshWait();
        
		BasicConfigurator.configure();
		InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("credentials2.json");
		GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
		FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(credentials).build();
		if (FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
		}
		Firestore db = FirestoreClient.getFirestore();
		DocumentReference docRef = db.collection("users").document(param1);
		ApiFuture<DocumentSnapshot> future = docRef.get();

		Person p = null;
		Person p2 = null;
		DocumentSnapshot document2;
		DocumentSnapshot document;

		try {
			document2 = future.get();
			p = document2.toObject(Person.class);
			p2 = document2.toObject(Person.class);
			System.out.println("login serialization successful");
			Iterator it = p.getStocks().entrySet().iterator();
			Iterator it2 = p2.getWatchList().entrySet().iterator();

			while (it.hasNext()) {

				Map.Entry pair = (Map.Entry) it.next();
				String stockName = (String) pair.getKey();
				List<Double> stockInfo = (List<Double>) pair.getValue();
				double buyInDay = 0;
				if (stockInfo.size() > 2) {
					buyInDay = stockInfo.get(2);
				}
				double sellDay = 0;
				if (stockInfo.size() > 3) {
					sellDay = stockInfo.get(3);
				}
				Stock stock = new Stock(stockName, stockInfo.get(0), stockInfo.get(1), buyInDay, sellDay);
				stockStorage2.add(stock);
				it.remove(); // avoids a ConcurrentModificationException
			}
			
			while (it2.hasNext()) {

				Map.Entry pair = (Map.Entry) it2.next();
				String stockName = (String) pair.getKey();
				List<Double> stockInfo = (List<Double>) pair.getValue();
				double buyInDay = 0;
				if (stockInfo.size() > 2) {
					buyInDay = stockInfo.get(2);
				}
				double sellDay = 0;
				if (stockInfo.size() > 3) {
					sellDay = stockInfo.get(3);
				}
				Stock stock = new Stock(stockName, stockInfo.get(0), stockInfo.get(1), buyInDay, sellDay);
				watchList2.add(stock);
				it2.remove(); // avoids a ConcurrentModificationException
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("block failed");
		}
		request.setAttribute("portfolio", myPortfolio);
        request.setAttribute("watchList", watchList2);
        request.setAttribute("stockList", stockStorage2);
		request.setAttribute("username", param1);
		request.setAttribute("csvSuccess", message);
	
        session.setAttribute("username", param1);
        try {
		RequestDispatcher dispatch =request.getRequestDispatcher("/MainPage.jsp"); 
		dispatch.forward(request,response);
        }
        catch(Exception e)
        {
        	
        }
	}
	
	public void refreshWait()
	{
		synchronized (this) {
            try {
            	this.wait(1000*2);
            } catch (Exception e) {
            	
            }
        }
	}

}
