package csci310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import csci310.DatePrice;

public class Stock_api {
	final static String alpha_vantage_api_key = "T9HHXYJABPOPLMD3";
//	final static String Finnhub_api_key = "btk1l8n48v6vglhucrig";
	final static String Tiingo_api_key = "cc909cc720b5a621fd6362283b3b88af61da1a59";
	
//	https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=demo
	
	
	
	public static List<Double> stock_price (String stock_ID, String time_span) throws IOException {
		List<Double> open_price = new ArrayList<>();
		
		String time_series = "TIME_SERIES_DAILY";
		String json_series = "Time Series (Daily)";
		String interval = "";

//		if(time_span.equals("week")) {
//			time_series = "TIME_SERIES_WEEKLY";
//			json_series = "Weekly Time Series";
//		}
//		else if(time_span.equals("month")) {
//			time_series = "TIME_SERIES_MONTHLY";
//			json_series = "Monthly Time Series";
//		}
		if(time_span.equals("5min")) {
			time_series = "TIME_SERIES_INTRADAY";
			json_series = "Time Series (5min)";
			interval = "interval=5min&";
		}
//		https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&apikey=demo
//		System.out.println("stock ID = " + stock_ID);
		
		String daily_price = "https://www.alphavantage.co/query?function="+ time_series +"&symbol=" +
				stock_ID + "&" + interval + "apikey=" + alpha_vantage_api_key;
//		System.out.println(daily_price);
		
		URL url = new URL(daily_price);
		URLConnection urlConnection = url.openConnection();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line;
		StringBuilder content = new StringBuilder();
		while( (line = bufferedReader.readLine()) != null) {
			content.append(line);
		}
		bufferedReader.close();
		System.out.println(content.toString());
		
//		System.out.println("stock ID = " + stock_ID);
		
		
		JsonObject job = JsonParser.parseString(content.toString()).getAsJsonObject();
		
		if(job.get("Error Message") != null) {
			String errorMessage = job.get("Error Message").toString();
//			System.out.println(errorMessage);
//			open_price.add(-1.0);
		}
		else {
	//		JsonObject meta = job.get("Meta Data").getAsJsonObject();
	//		System.out.println(meta);	
			JsonObject series = job.get(json_series).getAsJsonObject();
	//		System.out.println(series);
			
			Set<String> keys = series.keySet();
			for (String key: keys) {
	//		    System.out.print(key);
			    JsonObject day = series.get(key).getAsJsonObject();
	//		    System.out.println(day);
			    double open = day.get("1. open").getAsDouble();
	//		    double high = day.get("2. high").getAsDouble();
	//		    double low = day.get("3. low").getAsDouble();
	//		    double close = day.get("4. close").getAsDouble();
	//		    double volume = day.get("5. volume").getAsDouble();
	//		    System.out.println(open + " " +high + " " + low + " " + close);
			    open_price.add(open);
			}
		}
		
		return open_price;
	
	}
	
	public static DatePrice stock_price_with_timerange (String stock_ID, String startDate, String endDate) throws IOException {
		List<String> open_date = new ArrayList<String>();
		List<Double> open_price = new ArrayList<Double>();
		DatePrice myDP = new DatePrice(open_date,open_price);
//		https://api.tiingo.com/tiingo/daily/<ticker>/prices?startDate=2012-1-1&endDate=2016-1-1 
		String price_timerange = "https://api.tiingo.com/tiingo/daily/"+ stock_ID +"/prices?startDate=" +
				startDate + "&endDate=" + endDate + "&token=" + Tiingo_api_key;			
//		System.out.println(price_timerange);	
		try {
			URL url = new URL(price_timerange);
			URLConnection urlConnection = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			StringBuilder content = new StringBuilder();
			while( (line = bufferedReader.readLine()) != null) {
				content.append(line);
			}
			bufferedReader.close();
			System.out.println(content.toString());			
			

				JsonArray Jar = JsonParser.parseString(content.toString()).getAsJsonArray();
		//		System.out.println(Jar);
		
	
					for(int i = 0; i < Jar.size(); i++) {
			//			String date = Jar.get(i).getAsJsonObject().get("date").getAsString();
			//			date = date.substring(0, 10);
			//			double close = Jar.get(i).getAsJsonObject().get("close").getAsDouble();
			//			double high = Jar.get(i).getAsJsonObject().get("high").getAsDouble();
			//			double low = Jar.get(i).getAsJsonObject().get("low").getAsDouble();
						String date = Jar.get(i).getAsJsonObject().get("date").toString();
						date = date.substring(0,11);
						date = date.concat("\"");
								
						double open = Jar.get(i).getAsJsonObject().get("open").getAsDouble();
						open_date.add(date);
						open_price.add(open);
			//			System.out.println(date + " " + open + " " +high + " " + low + " " + close);

			}
			myDP = new DatePrice(open_date,open_price);
			
		}
		catch (Exception e)
		{
			String errorMessage = "Ticker not found";
			System.out.println(errorMessage);
		}
		return myDP;
	
	}
	
}

