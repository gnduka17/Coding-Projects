package csci310;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PortfolioTest {
	
	@Test
	public void testCurr_portfolio_earnings() {
		Stock s1 = new Stock("AAPL", 100, 10 , 202001001, 2021010);
		Stock s2 = new Stock("NIKE", 90, 20, 202001001, 2021010);
		
		ArrayList<Stock> por = new ArrayList<Stock>();
		por.add(s1);
		por.add(s2);
		
		Portfolio my_por = new Portfolio(por);
		double[] curr_price = new double[] {120,80};
		double por_earning = my_por.curr_portfolio_earnings(curr_price);

		assertTrue(por_earning == 0);
	}
	
	@Test
	public void teststock_earning() {
		Stock s1 = new Stock("AAPL", 100, 10, 202001001, 2021010);
		Stock s2 = new Stock("NIKE", 90, 20, 202001001, 2021010);
		
		ArrayList<Stock> por = new ArrayList<Stock>();
		por.add(s1);
		por.add(s2);
		
		Portfolio my_por = new Portfolio(por);

		double stock_earning = my_por.stock_earning("AAPL",110);

		assertTrue(stock_earning == 100);
	}

}
