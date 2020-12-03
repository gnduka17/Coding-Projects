package csci310.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
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

import csci310.CSVParser;
import csci310.Person;
import csci310.Portfolio;
import csci310.Stock;
import csci310.Stock_api;

@WebServlet(name = "CsvServlet", urlPatterns = { "/UploadFile" }, initParams = {
		@WebInitParam(name = "uploadpath", value = "/var/www/upload/") })
@MultipartConfig
public class CsvServlet extends HttpServlet {

	public CsvServlet() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		InputStream fileContent = null;
		String initialFile = "";
		CSVReader reader = null;
		if (session.getAttribute("testCase") == null) {
			System.out.println("h1");
			Part filePart = request.getPart("myfile");
			fileContent = filePart.getInputStream();
			reader = new CSVReader(new InputStreamReader(fileContent));
			response.getWriter().println("JUNIT TESTING");

		} else {
			System.out.println("h2");
			initialFile = (String) session.getAttribute("csvFile");
			Reader targetReader = new StringReader(initialFile);
			reader = new CSVReader(targetReader);
			// fileContent = new FileInputStream(initialFile);
			// reader = new CSVReader(new InputStreamReader(fileContent));

			// fileContent = new FileInputStream(initialFile);
			// System.out.println("file transfer successful");
		}

		ArrayList<Stock> stuff = new ArrayList<>();
		String username = (String) session.getAttribute("username");
		BasicConfigurator.configure();

		// Use the application default credentials
		InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("credentials2.json");
		GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

		FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(credentials).build();
		if (FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
		}

		Firestore db = FirestoreClient.getFirestore();
		System.out.println(username);
		DocumentReference docRef = db.collection("users").document(username);
		ApiFuture<DocumentSnapshot> future = docRef.get();

		Person p = null;
		DocumentSnapshot document2;
		DocumentSnapshot document;

		try {
			document2 = future.get();
			p = document2.toObject(Person.class);
			System.out.println("login serialization successful");
			Iterator it = p.getWatchList().entrySet().iterator();

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
				stuff.add(stock);
				it.remove(); // avoids a ConcurrentModificationException
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("block failed");
		}

		System.out.println("watchlist size: " + stuff.size());
		request.setAttribute("watchList", stuff);

		RequestDispatcher dispatch;
		System.out.println("CSV Servlet Triggered.");

		// create an instance of the CSV parser class
		CSVParser temp = new CSVParser();

		ArrayList<Stock> stockStorage = temp.parse((String) session.getAttribute("username"), reader);
		System.out.println("storage created");
		// dispatch to new page
		String next = "/MainPage.jsp";
		Portfolio myPortfolio = new Portfolio(stockStorage);
		request.setAttribute("portfolio", myPortfolio);
		request.setAttribute("stockList", stockStorage);
		// response.getWriter().write("dank");
		String message = "";
		System.out.println("portfolio size: " + stockStorage.size());
		if (!temp.correctDate) {
			response.getWriter().println("Error: invalid date.");
			session.setAttribute("csvSuccess", "Error: invalid date.");
			message = "Error: invalid date.";
		} else if (!temp.getValidStock()) {
			response.getWriter().println("Error: File contains stock that does not exist. Otherwise, "
					+ Integer.toString(temp.getStocksAdded()) + " stocks succecssfully added.");
			session.setAttribute("csvSuccess", "Error: File contains stock that does not exist. Otherwise, "
					+ Integer.toString(temp.getStocksAdded()) + " stocks succecssfully added.");
			message = "Error: File contains stock that does not exist. Otherwise, "
					+ Integer.toString(temp.getStocksAdded()) + " stocks succecssfully added.";
		} else if (!temp.correctCredentials) {
			response.getWriter().println("Credentials incorrect.");
			message = "Credentials incorrect";
		} else if (!temp.databaseExists) {
			response.getWriter().println("Database DNE");
			message = "Database DNE";
		} else if (!temp.catchBlock) {
			response.getWriter().println("Catch block 1");
			message = "Catch block 1";
		} else if (!temp.formattedCorrectly()) {
			response.getWriter().println("Error: the CSV file is not formatted correctly.");
			session.setAttribute("csvSuccess", "Error: the CSV file is not formatted correctly.");
			message = "Error: the CSV file is not formatted correctly.";
		} else {
			int duplicates = temp.checkDuplicate();
			int total = temp.checkNumStocks();
			int difference = total - duplicates;
			System.out.println("total is: " + total + " and duplicates is: " + duplicates);
			System.out.println("difference is: " + difference);
			if (difference == total) {
				response.getWriter().println("Stocks from CSV file added succesfully.");
				session.setAttribute("csvSuccess", "Stocks from CSV file added succesfully.");
				message = "Stocks from CSV file added successfully";
			} else {
				response.getWriter().println("Duplicate stocks detected.");
				session.setAttribute("csvSuccess", Integer.toString(temp.checkDuplicate())
						+ " duplicate stock(s) detected. \n" + Integer.toString(difference) + " new stock(s) added.");
				message = Integer.toString(temp.checkDuplicate())
						+ " duplicate stock(s) detected. \n" + Integer.toString(difference) + " new stock(s) added.";
			}
		}
		System.out.println("csv servlet completed -------------------------------------");
		request.setAttribute("username", username);
		response.setContentType("text/html");
		request.getSession().setAttribute("watchList", stuff);
		request.getSession().setAttribute("stockList", stockStorage);
		request.getSession().setAttribute("csvSuccess", message);
		request.getSession().setAttribute("username", username);
		response.sendRedirect("/fixRefresh");
		/*
		try {
			dispatch = request.getRequestDispatcher(next);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		// in.close();

	}
}
