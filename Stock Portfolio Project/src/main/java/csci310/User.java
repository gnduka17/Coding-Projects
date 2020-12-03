package csci310;

import java.util.ArrayList;

public class User {
	String username;
	Portfolio my_portfolio;
	
	public User(String name, ArrayList<Stock> my_por) {
		username = name;
		my_portfolio = new Portfolio(my_por);
	}
	
	public String getUsername() {
		return username;
	}
	
	
	public double curr_stock_earning(String stock_code, double curr_price) {
		double stock_earning = my_portfolio.stock_earning(stock_code, curr_price);
		return stock_earning;
	}
	
	public double curr_portfolio_earning(double[] curr_price) {
		double por_earning = my_portfolio.curr_portfolio_earnings(curr_price);
		return por_earning;
	}
}
