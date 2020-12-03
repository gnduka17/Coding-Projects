package csci310;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Portfolio {
	static ArrayList<Stock> my_por = new ArrayList<Stock>();
	
	public Portfolio(ArrayList<Stock> stocks) {
		my_por = stocks;
		
	}
	
	public double curr_portfolio_earnings(double[] curr_price) {
		double por_earning = 0;
		for(int i = 0; i < my_por.size(); i++) {
			double temp = my_por.get(i).current_earnings(curr_price[i]);
			por_earning += temp;

		}
		
		return por_earning;
	}
	
	public double stock_earning(String stock_code, double curr_price) {
		double stock_earning = 0;
		for (Stock s : my_por) {
		      if(s.getStock_code().equals(stock_code)) {
		    	  stock_earning = s.current_earnings(curr_price);
		      }
		      
		}
		return stock_earning;
	}
//	public double curr_portfolio_price() throws IOException {
//		double por_total = 0;
//		for(int i = 0; i < my_por.size(); i++) {
//			double temp = my_por.get(i).current_total();
//			por_total += temp;
//
//		}
//		
//		return por_total;
//	}
	
//	public static List<Double>  portfolioPrice(String startDate, String endDate) throws IOException {
//		
//		List<Double> por_price = new ArrayList<>();
//		List<Double> proPrice = Stock_api.stock_price_with_timerange("AAPL", startDate,endDate);
//		int timeRange = proPrice.size();
//		for(int i = 0; i < timeRange; i++) {
//			double curr_total = 0;
//			for(int j = 0; j < my_por.size(); j++) {
//				curr_total += my_por.get(i).current_total();					
//			}
//			por_price.add(curr_total);
//			System.out.println(curr_total);
//		}
//		
//		return por_price;
//	}

}
