package csci310;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {
	private static User u;
	@BeforeClass
    public static void setup() {
		Stock s1 = new Stock("AAPL", 100, 10, 202001001, 2021010);
		Stock s2 = new Stock("NIKE", 90, 20, 202001001, 2021010);
		
		ArrayList<Stock> por = new ArrayList<Stock>();
		por.add(s1);
		por.add(s2);

		u = new User("Trojan", por);
    }

	@Test
	public void testgetUsername() {
		String usnm = u.getUsername();
		assertTrue(usnm == "Trojan");
	}
	
	@Test
	public void testcurr_stock_earning() {
		double stock_earning = u.curr_stock_earning("AAPL",110);
		assertTrue(100 == stock_earning);
	}
	
	@Test
	public void testcurr_portfolio_earning() {
		double[] curr_price = new double[] {120,80};
		double por_earning = u.curr_portfolio_earning(curr_price);
		assertTrue(por_earning == 0);

	}

}
