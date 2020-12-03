package csci310;

import java.util.ArrayList;
import java.util.List;




public class DatePrice {
	
	List<String> open_date = new ArrayList<String>();
	List<Double> open_price = new ArrayList<Double>();
	
	public DatePrice(List<String> date, List<Double> price)
    {
       this.open_date = date;
       this.open_price = price;
    }
	public List<String> getDate() {
		return open_date;
	}
	public List<Double> getPrice() {
		return open_price;
	}
	     
}
