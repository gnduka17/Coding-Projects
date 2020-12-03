package csci310;

import java.util.List;
import java.util.Map;

import com.google.cloud.Timestamp;

public class Person {
	public Person(){}
	public Person (String password, String username, Map<String, List<Double>> stocks, Map<String, List<Double>> watchList)
	{
		this.username = username;
		this.password = password;
		this.stocks = stocks;
		this.watchList = watchList;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Map<String, List<Double>> getStocks() {
		return stocks;
	}
	public void setStocks(Map<String, List<Double>> stocks) {
		this.stocks = stocks;
	}
	public Map<String, List<Double>> getWatchList() {
		return watchList;
	}
	public void setWatchList(Map<String, List<Double>> watchList_) {
		this.watchList = watchList_;
	}
	public int getAttempts()
	{
		return this.attempts;
	}
	public void setAttempts(int attempts)
	{
		this.attempts = attempts;
	}
	public long getValidLoginTime() {
		return this.validLoginTime;
	}
	public void setValidLoginTime(long validLoginTime)
	{
		this.validLoginTime = validLoginTime;
	}
	public boolean getLocked() {
		return this.locked;
	}
	public void setLocked(boolean lock) {
		this.locked = lock;
	}
	public long getSixtySecPassed() {
		return this.sixtySecPassed;
	}
	public void setSixtySecPassed(long sixty) {
		this.sixtySecPassed = sixty;
	}
	private String username;
	private String password;
	private int attempts;
	private Map<String, List<Double>> stocks;
	private Map<String, List<Double>> watchList;
	long validLoginTime;
	private boolean locked;
	long sixtySecPassed;
	
}
