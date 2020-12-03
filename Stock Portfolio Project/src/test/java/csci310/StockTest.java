package csci310;

import static org.junit.Assert.*;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class StockTest {

	@Test
	public void testCurrent_earnings() {
		Stock s1 = new Stock("AAPL", 100, 10, 202001001, 2021010);
		double curr_earning = s1.current_earnings(110);
		assertTrue(curr_earning == 100);
	}
	
	@Test
	public void testGetStock_code() {
		Stock s1 = new Stock("AAPL", 100, 10, 202001001, 2021010);
		String stock_code = s1.getStock_code();
		assertTrue(stock_code == "AAPL");
	}
	
	@Test
	public void testGetBuyInPrice() {
		Stock s1 = new Stock("AAPL", 100, 10, 202001001, 2021010);
		double buyin_prince = s1.getBuyInPrice();
		assertTrue(buyin_prince == 100);
	}
	
	@Test
	public void testGetNumShares() {
		Stock s1 = new Stock("AAPL", 100, 10, 202001001, 2021010);
		double numShares = s1.getNumShares();
		assertTrue(numShares == 10);
	}
	@Test
	public void testGetBuyInDate() {
		Stock s1 = new Stock("AAPL", 100, 10, 202001001, 2021010);
		double buyin_date = s1.getBuyInDate();
		assertTrue(buyin_date == 202001001);
	}
	
	@Test
	public void testGetSellDate() {
		Stock s1 = new Stock("AAPL", 100, 10, 202001001, 2021010);
		double sellDate = s1.getSellDate();
		assertTrue(sellDate == 2021010);
	}
	@Test
	public void testGetBuyInDateinFormat() throws ParseException {
		Stock s1 = new Stock("AAPL", 100, 10, 20201001, 2021010);
		Date buyDate = s1.getBuyInDateinFormat();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = sdformat.parse("2020-10-01");
		assertTrue(buyDate.equals(d1));
	}
	@Test
	public void testGetSellDateinFormat() throws ParseException {
		Stock s1 = new Stock("AAPL", 100, 10, 202001001, 20201010);
		Date sellDate = s1.getSellDateinFormat();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = sdformat.parse("2020-10-10");
		assertTrue(sellDate.equals(d1));
	}

}
