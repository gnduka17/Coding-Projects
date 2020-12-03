package csci310;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class Stock_apiTest {

	@Test
	public void testStock_price() {
		String code1 = "IBM";
    	try {
    		List<Double> intraday_open_price = Stock_api.stock_price(code1,"5min");

    		assertTrue(intraday_open_price.size() > 0);
    		
    		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
    		
    		
    		List<Double> daily_open_price = Stock_api.stock_price(code1,"day");

    		assertTrue(daily_open_price.size() > 0);
 
    		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//    	try {
//
//    		
//    		List<Double> weekly_open_price = Stock_api.stock_price(code1,"week");
//
//    		assertTrue(weekly_open_price.size() > 0);
//    		
//
//    		
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	try {
//    		String code2 = "AAPL";
//    		List<Double> monthly_open_price = Stock_api.stock_price(code2,"month");
//
//    		assertTrue(monthly_open_price.size() > 0);
//    		
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	try {
//    		
//    		Stock_api.stock_price("APPL","day");
////    		List<Double> invlid_call = Stock_api.stock_price("APPL","day");
//    		
//    		assertTrue(true);
// 
//    		
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
	}
	
	
	
	
	
	
	@Test
	public void testStock_price_with_timerange() {
		String code2 = "AAPL";
		
    	try {
    		DatePrice myDP = Stock_api.stock_price_with_timerange(code2,"2019-01-02","2019-01-14");
    		List<Double> open_price2 = myDP.getPrice();
    		assertTrue(open_price2.size() == 9);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testWrongCode() {
		String code2 = "APPL";
		
    	try {
    		DatePrice myDP = Stock_api.stock_price_with_timerange(code2,"2019-01-02","2019-01-14");
    		List<Double> open_price2 = myDP.getPrice();
    		assertTrue(open_price2.size() == 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
