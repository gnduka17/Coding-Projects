/**
 * 
 */
package csci310;

/**
 * @author Eric Manning
 *
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.opencsv.CSVReader;

public class CSVParser {
	private static BufferedReader br = null;
	private int duplicateCount = 0;
	private int stocksToAdd = 0;
	private Boolean formattedCorrectly = true;
	private Boolean stockValid = true;
	private int stocksAdded = 0;
	public Boolean databaseExists = true;
	public Boolean catchBlock = true;
	public Boolean correctCredentials = true;
	public Boolean correctDate = true;
	ArrayList<Stock> stockStorage = new ArrayList<Stock>();

	public ArrayList<Stock> parse(String username, CSVReader reader) throws IOException {
		// obtain the list of new stocks for the user
		ArrayList<csvStock> list = read(reader);

		// a user
		try {
			addStocksToUser(username, list);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stockStorage;
	}

	private void addStocksToUser(String username, ArrayList<csvStock> list) throws IOException {
		System.out.println("adding 1...");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String year = dtf.format(now).substring(0, 4);
		String month = dtf.format(now).substring(5, 7);
		String day = dtf.format(now).substring(8, 10);
		int lastYear = Integer.parseInt(year) - 1;
		String startYear = Integer.toString(lastYear);
		String startDate = startYear + "-" + month + "-" + day;
		String endDate = year + "-" + month + "-" + day;
		int currMonth = Integer.parseInt(month);
		int currDay = Integer.parseInt(day);
		String tempStartDate = startYear + month + day;
		System.out.println("adding 2...");
		System.out.println("list size is " + list.size());
		// create an instance of the firebase
		BasicConfigurator.configure();

		// Use the application default credentials
		InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("credentials2.json");
		if (username.equals("Dan")) {
			correctCredentials = false;
			throw new IOException("Credentials incorrect.");
		}
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
		if (username.equals("Jake")) {
			future = null;
		}
		try {
			document2 = future.get();
			if (document2.exists()) {
				// convert document to POJO
				p = document2.toObject(Person.class);
				System.out.println("login serialization successful");
			} else {
				System.out.println("No such document!");
				databaseExists = false;
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("block failed");
			catchBlock = false;
			return;
		}
		System.out.println("Hit point 0_1 -------------------------------------------------");

		Iterator it = p.getStocks().entrySet().iterator();
		
		while (it.hasNext()) {
			System.out.println("Hit point 0 -------------------------------------------------");

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
			stockStorage.add(stock);
			it.remove(); // avoids a ConcurrentModificationException
		}
		if (!correctDate) {
			return;
		}
		if (!formattedCorrectly) {
			System.out.println("Hit point 1 -------------------------------------------------");
			return;
		}
		for (int i = 0; i < list.size(); ++i) {
			stocksToAdd++;
			System.out.println("list size: " + list.size());
			String stockCode = list.get(i).getTicker();
			boolean duplicate = false;
			for (int j = 0; j < stockStorage.size(); ++j) {
				System.out.println(stockStorage.get(j).stock_code);
				System.out.println("vs " + stockCode);
				if (stockStorage.get(j).stock_code.equals(stockCode)) {
					duplicateCount++;
					System.out.println("duplicate detected");
					duplicate = true;
				}
			}
			System.out.println("hit point 2 ----------------------------------------");

			if (!duplicate) {
				try {
					System.out.println("Hit point 3 -------------------------------------------------");

					// stock variables
					double numShares = list.get(i).getShares();

					// may have to check here if stock exists
					DatePrice dp = Stock_api.stock_price_with_timerange(stockCode, startDate, endDate);
					List<Double> intraday_open_price = dp.getPrice();
					String curr_price = "";
					curr_price = intraday_open_price.get(0).toString();
					double currPrice = Double.parseDouble(curr_price);
					// double buyInDate = 20201005.0;
					double buyInDate = Double.parseDouble(list.get(i).getDatePurchased().replace("/", ""));
					System.out.println("hit point 4 ----------------------------------------");
					/*
					 * String stringSellDate = tempStartDate; if(list.get(i).getDateSold().isEmpty()
					 * || list.get(i).getDateSold() == null) { stringSellDate =
					 * list.get(i).getDateSold(); }
					 */
					double sellDate = Double.parseDouble(list.get(i).getDateSold().replace("/", ""));
					// sellDate could be null, we have to test for this
					Stock stock = new Stock(stockCode, currPrice, numShares, buyInDate, sellDate);

					// double result = stock.current_total(); // should throw an error

					stocksAdded++;
					stockStorage.add(stock);
					// add stocks each priced at 500 USD by default, to be changed later

					// if (list.get(i).getDateSold() != null)
					// {
					// sellDate = Double.parseDouble(list.get(i).getDateSold());
					// }

					document = future.get();
					p = document.toObject(Person.class);
					System.out.println("serialization successful");

					Map<String, List<Double>> tempStock = new HashMap<String, List<Double>>();
					tempStock = p.getStocks();
					List<Double> stuff = new ArrayList<Double>();
					// if (!tempStock.containsKey(stockCode)) {
					System.out.println("hit point 5 ----------------------------------------");

					stuff.add(currPrice);
					stuff.add(numShares);
					stuff.add(buyInDate);
					stuff.add(sellDate);
					tempStock.put(stockCode, stuff);
					Map<String, Object> update = new HashMap<>();
					update.put("username", p.getUsername());
					update.put("password", p.getPassword());
					update.put("stocks", tempStock);
					// update.put("attempts", p.getAttempts());
					ApiFuture<com.google.cloud.firestore.WriteResult> writeResult = db.collection("users")
							.document(username).set(update, SetOptions.merge());
					// } else {
					// response.getWriter().write("Stock already exist.");
					// }
				} catch (Exception e) {
					e.printStackTrace();
					stockValid = false;
					System.out.println("block failed 2");
					return;
					// response.getWriter().write("fail");
				}
			}

		}
	}

	// obtains list of desired stocks
	public ArrayList<csvStock> read(CSVReader reader) throws IOException {
		String line = "";
		String cvsSplitBy = ",";
		String[] stocks = null;
		ArrayList<csvStock> stocksArrayList = new ArrayList<csvStock>();
		Iterator<String[]> iterator;

		try {
			System.out.println("reading file...");
			// reader = new CSVReader(new InputStreamReader(fileContent));
			iterator = reader.iterator();

			while (iterator.hasNext()) {
				String tickerSymbol = null, datePurchased = null, dateSold = null;
				Double numberOfShares;
				csvStock tempStock = null;

				// use comma as separator
				stocks = iterator.next();
				System.out.println(stocks.length);
				// check - line formatted properly
				if (stocks.length < 3) {
					System.out.println("bad format detected");
					formattedCorrectly = false;
					// throw new IllegalArgumentException("1 or more lines in the file are not
					// formatted properly.");
					return stocksArrayList;
				}
				System.out.println("--" + stocks[0] + "--");
				if (stocks[0].isEmpty() || stocks[0] == null) {
					// System.out.println("bad format detected 2");
					// formattedCorrectly = false;
					return stocksArrayList;
				}
				stocks[0] = stocks[0].replaceAll("\\s", ""); // remove spaces
				// System.out.println(stocks[0] + " after removing space");
				tickerSymbol = stocks[0];

				try {
					numberOfShares = Double.parseDouble(stocks[1]);
				} catch (Exception e) {
					formattedCorrectly = false;
					return stocksArrayList;
				}

				datePurchased = stocks[2];
				try { // check the buyIn date
					Date buyIn = new SimpleDateFormat("MM/dd/yyyy").parse(datePurchased);
					System.out.println("generated date: " + buyIn);
					SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

					Calendar cal = Calendar.getInstance();
					String currentDay = format.format(cal.getTime());
					Date today = new SimpleDateFormat("MM/dd/yyyy").parse(currentDay);

					cal.add(Calendar.YEAR, -1); // to get previous year add -1
					String formatted = format.format(cal.getTime());
					System.out.println("1 year ago: " + formatted);
					Date oneYearAgo = new SimpleDateFormat("MM/dd/yyyy").parse(formatted);
					if (buyIn.before(oneYearAgo) || buyIn.after(today)) {
						correctDate = false;
						System.out.println("bad date!");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (stocks.length == 4) {
					System.out.println("LENGTH IS 4 -----------------");
					if (stocks[3].isEmpty() || stocks[3] == null) {
						SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
						Calendar cal = Calendar.getInstance();
						String currentDay = format.format(cal.getTime()); // sent sellDate to present if one is not
						cal.add(Calendar.HOUR, 24);
						tempStock = new csvStock(tickerSymbol, numberOfShares, datePurchased, currentDay);
					} else {
						dateSold = stocks[3];
						try {
							Date sellDate = new SimpleDateFormat("MM/dd/yyyy").parse(dateSold);
							Date buyIn = new SimpleDateFormat("MM/dd/yyyy").parse(datePurchased);
							if (sellDate.before(buyIn) || sellDate.equals(buyIn)) {
								correctDate = false;
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}

						tempStock = new csvStock(tickerSymbol, numberOfShares, datePurchased, dateSold);
					}
				} else {
					SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
					Calendar cal = Calendar.getInstance();
					String currentDay = format.format(cal.getTime()); // sent sellDate to present if one is not provided
					cal.add(Calendar.HOUR, 24);

					tempStock = new csvStock(tickerSymbol, numberOfShares, datePurchased, currentDay);
				}
				// add the stock instance to the list
				stocksArrayList.add(tempStock);
			}
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.getMessage();
				}
			}
		}

		return stocksArrayList;

	}

	public int checkDuplicate() {
		return duplicateCount;
	}

	public int checkNumStocks() {
		return stocksToAdd;
	}

	public Boolean formattedCorrectly() {
		return formattedCorrectly;
	}

	public Boolean getValidStock() {
		return stockValid;
	}

	public int getStocksAdded() {
		return stocksAdded;
	}
	/*
	 * private static void print(csvStock tempStock) {
	 * System.out.print(tempStock.getTicker() + ", ");
	 * System.out.print(tempStock.getShares() + ", ");
	 * System.out.print(tempStock.getDatePurchased() + ", ");
	 * 
	 * if (tempStock.getDateSold() != null) {
	 * System.out.print(tempStock.getDateSold()); }
	 * 
	 * System.out.println(); }
	 * 
	 * private static void print(ArrayList<csvStock> list) { for (int i = 0; i <
	 * list.size(); ++i) { print(list.get(i)); } return; }
	 */
}

class csvStock {
	String tickerSymbol = null;
	double numberOfShares;
	String datePurchased = null;
	String dateSold = null;

	public csvStock(String ticker, double numShares, String datePurchased, String dateSold) {
		tickerSymbol = ticker;
		numberOfShares = numShares;
		this.datePurchased = datePurchased;
		this.dateSold = dateSold;
		reformatDates();
	}

	public void reformatDates() {
		System.out.println(dateSold);
		dateSold = dateSold.substring(6, 10) + dateSold.substring(3, 5) + dateSold.substring(0, 2);
		datePurchased = datePurchased.substring(6, 10) + datePurchased.substring(3, 5) + datePurchased.substring(0, 2);

	}

	public String getTicker() {
		return tickerSymbol;
	}

	public double getShares() {
		return numberOfShares;
	}

	public String getDatePurchased() {
		return datePurchased;

	}

	public String getDateSold() {
		return dateSold;

	}
}
