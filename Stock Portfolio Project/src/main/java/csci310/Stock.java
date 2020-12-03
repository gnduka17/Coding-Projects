package csci310;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


public class Stock {
	String stock_code;
	double buy_in_price;
	double number_share;
	double buyInDate;
	double sellDate;
	
	public Stock(String code, double price, double share, double buyInDate_, double sellDate_) {
		stock_code = code;
		buy_in_price = price;
		number_share = share;
		buyInDate = buyInDate_;
		sellDate = sellDate_;

	}
	
	public String getStock_code() {
		return stock_code;
	}
	
	public double current_earnings(double curr_price) {
		return (curr_price - buy_in_price) * number_share;
	}
//	public double current_total() throws IOException {
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//		LocalDateTime now = LocalDateTime.now();
//		System.out.println(dtf.format(now));
//		String year = dtf.format(now).substring(0,4);
//		String month = dtf.format(now).substring(5,7);
//		String day = dtf.format(now).substring(8,10);
//		int lastYear = Integer.parseInt(year) - 1;
//		String startYear = Integer.toString(lastYear);
//		String startDate = startYear + "-" + month + "-" + day;
//		String endDate = year + "-" + month + "-" + day;
//		System.out.println(startDate); 
//		System.out.println(endDate);
//		
//		DatePrice myDP = Stock_api.stock_price_with_timerange(stock_code, startDate,endDate);
//		List<Double> intraday_open_price = myDP.getPrice();
////		Pair<String, Double> p1 = intraday_open_price.get(0);
//		double curr_price = intraday_open_price.get(0);
//		double total = curr_price * number_share;
//		
//		return total;
//	}

	
	public double getBuyInPrice() {
		return buy_in_price;
	}
	
	public double getNumShares() {
		return number_share;
	}
	public double getBuyInDate() {
		return buyInDate;
	}
	public double getSellDate() {
		return sellDate;
	}
	public Date getBuyInDateinFormat() throws ParseException {
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		
		String buyInDay = Double.toString(buyInDate);
		buyInDay = buyInDay.replace('E', '0');		
		buyInDay = buyInDay.substring(0,1) + buyInDay.substring(2,5) + "-" + 
				buyInDay.substring(5,7) + "-" + buyInDay.substring(7,9);
//		System.out.println(buyInDay);
	    Date d1 = sdformat.parse(buyInDay);
	    
	    return d1;
	}
	
	public Date getSellDateinFormat() throws ParseException {
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = sdformat.parse("2026-9-15");
		if(sellDate != 0) {
			String sellDay = Double.toString(sellDate);
			sellDay = sellDay.replace('E', '0');		
			sellDay = sellDay.substring(0,1) + sellDay.substring(2,5) + "-" + 
					sellDay.substring(5,7) + "-" + sellDay.substring(7,9);
	//		System.out.println(buyInDay);
		    d1 = sdformat.parse(sellDay);
		}
	    return d1;
	}
	
	
}
