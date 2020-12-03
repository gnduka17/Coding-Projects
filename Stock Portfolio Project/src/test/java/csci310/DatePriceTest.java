package csci310;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DatePriceTest {

	@Test
	public void testGetDate() {
		List<String> open_date = new ArrayList<String>();
		List<Double> open_price = new ArrayList<Double>();
		open_date.add("hello");
		open_price.add(1.0);
		DatePrice myDP = new DatePrice(open_date,open_price);
		List<String> test_date = myDP.getDate();
		assert(test_date.size() > 0);
	}
	
	@Test
	public void testGetPrice() {
		List<String> open_date = new ArrayList<String>();
		List<Double> open_price = new ArrayList<Double>();
		open_date.add("hello");
		open_price.add(1.0);
		DatePrice myDP = new DatePrice(open_date,open_price);
		List<Double> test_price = myDP.getPrice();
		assert(test_price.size() > 0);
	}

}
