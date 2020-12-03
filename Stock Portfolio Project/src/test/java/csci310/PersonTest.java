package csci310;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class PersonTest {

	private static Person p;
	@Before
	public void setup() {
		Map<String, List<Double>> tempList = new HashMap<String, List<Double>>();
		tempList.put("TSLA", new ArrayList<Double>());
		
		Map<String, List<Double>> tempWatch = new HashMap<String, List<Double>>();
		tempWatch.put("TSLA", new ArrayList<Double>());
		
		p = new Person("adf143a45sa","frank", tempList, tempWatch);
	}
	
	@Test
	public void testGetUsername() {
		assertTrue("frank".equals(p.getUsername()));
	}
	
	@Test
	public void testSetUsername() {
		p.setUsername("Jake");
		assertTrue("Jake".equals(p.getUsername()));
	}
	
	@Test
	public void testGetPassword() {
		assertTrue("adf143a45sa".equals(p.getPassword()));
	}
	
	@Test
	public void testSetPassword() {
		p.setPassword("password");
		assertTrue("password".equals(p.getPassword()));
	}

	@Test
	public void testGetStocks() {
		Map<String, List<Double>> empty = p.getStocks();
		assertTrue(empty.size() == 1);
	}
	
	@Test
	public void testSetStocks() {
		Map<String, List<Double>> empty2 = p.getStocks();
		empty2.put("NOC", new ArrayList<Double>());
		p.setStocks(empty2);
		Map<String, List<Double>> empty3 = p.getStocks();
		List<Double> temp = empty3.get("NOC");
		assertTrue(temp.size() == 0);
	}
	@Test
	public void testGetWatchList() {
		Map<String, List<Double>> empty = p.getWatchList();
		assertTrue(empty.size() == 1);
	}
	
	@Test
	public void testSetWatchList() {
		Map<String, List<Double>> empty2 = p.getWatchList();
		empty2.put("NOC", new ArrayList<Double>());
		p.setWatchList(empty2);
		Map<String, List<Double>> empty3 = p.getWatchList();
		List<Double> temp = empty3.get("NOC");
		assertTrue(temp.size() == 0);
	}
	

	
}
