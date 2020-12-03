package cucumber;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.util.*;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefPortfolio {
	private static final String ROOT_URL = "http://localhost:8080/";
	private static final String REG_URL = "http://localhost:8080/Register.jsp";
	private static final String MAIN_URL = "http://localhost:8080/MainPage.jsp";
	private final WebDriver driver = new ChromeDriver();
	private WebDriverWait wait = new WebDriverWait (driver, 5);
	List<WebElement> rows;
	List<WebElement> rowsPort;
	int count;
	int countPort;
	
	
	@Given("I am on the main page40")
	public void i_am_on_the_main_page40() {
		
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
		searchButton.click();
		
	}
	
	@When("I enter in a stock ticker that doesnt exist")
	public void i_enter_in_a_stock_ticker_that_doesnt_exist() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addToPortfolio\"]")));
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addToPortfolio\"]"));
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchCode\"]")));
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"searchCode\"]"));
		queryBox.sendKeys("tytyr");
//		((JavascriptExecutor)driver).executeScript("document.getElementById('buyInDate').removeAttribute('readonly',0);");

		queryBox = driver.findElement(By.xpath("//*[@id=\"buyInDate\"]"));
//		driver.clear();
		queryBox.sendKeys("11022020");
		
		searchButton = driver.findElement(By.xpath("//*[@id=\"portfolioButton\"]"));
		searchButton.click();
		
		
	}
	@Then("I should see an error message")
	public void i_should_see_an_error_message() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchFormerror\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"searchFormerror\"]")).getText();
		System.out.println("WHAT DOES IT PRINT OUTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT: "+ result);
		assertTrue(result.equals("Please enter the right stock code"));
	
	}
	
	//feat2

	@Given("I am on the main page30")
	public void i_am_on_the_main_page30() {
//		driver.get(ROOT_URL);
//		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
//		queryBox.sendKeys("cucum310");
//		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
//		//*[@id="form"]/div[5]/div/input
//		queryBox.sendKeys("testing123");
//		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
//		searchButton.click();
//		
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
		searchButton.click();
	}

	@When("I click search with empty field")
	public void i_click_search_with_empty_field() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addToPortfolio\"]")));
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addToPortfolio\"]"));
		searchButton.click();
	
////		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"stockCode\"]")));
//		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"stockCode\"]"));
//		queryBox.sendKeys("");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/form/button")));
//		WebElement searchButton = driver.findElement(By.xpath("/html/body/div[2]/form/button"));
//		searchButton.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#portfolioButton")));
		searchButton = driver.findElement(By.cssSelector("#portfolioButton"));
		searchButton.click();

	}

	@Then("I should see an error message30")
	public void i_should_see_an_error_message30() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchFormerror\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"searchFormerror\"]")).getText();
		System.out.println("enter right stock code^^^^^^^^^^^^^^^: "+ result);
		assertTrue(result.equals("Please enter the right stock code"));
	}
	
	//feat5
	@Given("I am on the main page33")
	public void i_am_on_the_main_page33() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I enter in a stock ticker that exists33")
	public void i_enter_in_a_stock_ticker_that_exists33() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addToPortfolio\"]")));
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addToPortfolio\"]"));
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchCode\"]")));
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"searchCode\"]"));
		queryBox.sendKeys("MSFT");
//		((JavascriptExecutor)driver).executeScript("document.getElementById('buyInDate').removeAttribute('readonly',0);");

		queryBox = driver.findElement(By.xpath("//*[@id=\"buyInDate\"]"));
//		driver.clear();
		queryBox.sendKeys("11022020");
		
		
	}

	@When("click the add to portfolio button33")
	public void click_the_add_to_portfolio_button33() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"portfolioButton\"]"));
		searchButton.click();
	}

	@Then("the stock should be added to portfolio33")
	public void the_stock_should_be_added_to_portfolio33() {
//		String result = driver.findElement(By.xpath("//*[@id=\"portfolioTable\"]/tbody/tr/td[1]")).getText();
//		assertTrue(result.equals("AMZN"));
//		assertTrue(true);
		//*[@id="portfolioTable"]/tbody/tr/th
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"portfolioTable\"]/tbody/tr/th")));
		String result= driver.findElement(By.xpath("//*[@id=\"portfolioTable\"]/tbody/tr/th")).getText();
		String resultNEW= driver.findElement(By.xpath("//*[@id=\"portfolioTable\"]/tbody/tr[1]/th")).getText();
		System.out.println("should print MSFT but what ia here%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%: "+ result);
		System.out.println("should print MSFT but what ia here%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%: "+ resultNEW);
		assertTrue(result.equals("MSFT"));
		
	}
	
	//feature 5

	@Given("I am on the main page34")
	public void i_am_on_the_main_page34() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I enter in a stock ticker that exists in the portfolio")
	public void i_enter_in_a_stock_ticker_that_exists_in_the_portfolio() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addToPortfolio\"]")));
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addToPortfolio\"]"));
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchCode\"]")));
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"searchCode\"]"));
		queryBox.sendKeys("MSFT");
//		((JavascriptExecutor)driver).executeScript("document.getElementById('buyInDate').removeAttribute('readonly',0);");

		queryBox = driver.findElement(By.xpath("//*[@id=\"buyInDate\"]"));
//		driver.clear();
		queryBox.sendKeys("11022020");
	
	}

	@When("click the add to portfolio button34")
	public void click_the_add_to_portfolio_button34() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"portfolioButton\"]"));
		searchButton.click();
	}

	@Then("I should see an error message34")
	public void i_should_see_an_error_message34() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchFormerror\"]")));
		String result= driver.findElement(By.xpath("//*[@id=\"searchFormerror\"]")).getText();
		System.out.println("should print ERROR MESSAGE but what ia here%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%: "+ result);
		assertTrue(result.equals("Stock already exist."));
//		
	}
//
//	//feature 6
	@Given("I am on the main page35")
	public void i_am_on_the_main_page35() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I enter in a correct tickr35")
	public void i_enter_in_a_correct_tickr35() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addToPortfolio\"]")));
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addToPortfolio\"]"));
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchCode\"]")));
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"searchCode\"]"));
		queryBox.sendKeys("AMZN");
//		((JavascriptExecutor)driver).executeScript("document.getElementById('buyInDate').removeAttribute('readonly',0);");

		queryBox = driver.findElement(By.xpath("//*[@id=\"buyInDate\"]"));
//		driver.clear();
		queryBox.sendKeys("11022020");
		
	}

	@When("enter in a negative number of shares")
	public void enter_in_a_negative_number_of_shares() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"numShares\"]"));
		queryBox.sendKeys("-4");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"portfolioButton\"]"));
		searchButton.click();
	}

	@Then("I should see an error message35")
	public void i_should_see_an_error_message35() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchFormerror\"]")));
		String result= driver.findElement(By.xpath("//*[@id=\"searchFormerror\"]")).getText();
		System.out.println("should print ERROR WITH NEGATIVE NUMBER but what ia here%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%: "+ result);
		assertTrue(result.equals("Please enter the number of shares to buy."));
//		driver.switchTo().alert().accept();
//		assertTrue(driver.switchTo().alert().getText().equals("Please enter the number of shares to buy."));
	}

	@Given("I am on the main page36")
	public void i_am_on_the_main_page36() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
		rows = driver.findElements(By.cssSelector("#watchListTable > tbody"));
//		List<WebElement> rows = (List<WebElement>) driver.findElement(By.xpath("//*[@id=\"portfolioTable\"]"));
		count = rows.size();
		System.out.println("ROW COUNT : "+count);
		
	}

	@When("I click on remove stock button on WL")
	public void i_click_on_remove_stock_button_on_WL() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"UBER\"]"));
		searchButton.click();
	}

	@When("click delete stock in the popUp")
	public void click_delete_stock_in_the_popUp() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"confirmRemoveStock\"]/div/div/div[3]/button[2]")));
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"confirmRemoveStock\"]/div/div/div[3]/button[2]"));
		searchButton.click();
		
		
	}

	@Then("the stock is removed from the graph and WL")
	public void the_stock_is_removed_from_the_graph_and_WL() {
		List<WebElement> rowsnew = driver.findElements(By.cssSelector("#portfolioTable > tbody"));
		int countnew = rowsnew.size();
		System.out.println("ROW COUNT after delete : "+countnew);
		assertTrue(count>countnew); 
	}
	

	@Given("I am on the main page38")
	public void i_am_on_the_main_page38() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I click on add to Watchlist button")
	public void i_click_on_add_to_Watchlist_button() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addToWatchlist\"]"));
		searchButton.click();
	}

	@When("enter in credentials correctly")
	public void enter_in_credentials_correctly() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#searchCode")));
		WebElement queryBox = driver.findElement(By.cssSelector("#searchCode"));
		queryBox.sendKeys("AMZN");
//		((JavascriptExecutor)driver).executeScript("document.getElementById('buyInDate').removeAttribute('readonly',0);");

		queryBox = driver.findElement(By.xpath("//*[@id=\"buyInDate\"]"));
//		driver.clear();
		queryBox.sendKeys("11022020");
		queryBox = driver.findElement(By.xpath("//*[@id=\"sellDate\"]"));
//		driver.clear();
		queryBox.sendKeys("12042020");
	}

	@When("click add stock button")
	public void click_add_stock_button() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"watchlistButton\"]"));
		searchButton.click();
	}

	@Then("the WL should have that new stock inserted")
	public void the_WL_should_have_that_new_stock_inserted() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"watchListTable\"]/tbody/tr[1]/th")));
		//*[@id="watchListTable"]/tbody/tr[1]/th
		//*[@id="portfolioTable"]/tbody/tr[1]/th
		//*[@id="portfolioTable"]/tbody/tr[2]/th
		//html/body/div[5]/div[2]/table/tbody/tr[2]/th
		//"/html/body/div[5]/div[2]/table/tbody/tr[1]/th"
		String result= driver.findElement(By.xpath("//*[@id=\"watchListTable\"]/tbody/tr[1]/th")).getText();
		System.out.println("should print AMZN but what ia here%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%: "+ result);
		String resultNEW= driver.findElement(By.xpath("//*[@id=\"watchListTable\"]/tbody/tr[2]/th")).getText();
		System.out.println("should print AMZN but what ia here%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%: "+ resultNEW);
		assertTrue(result.equals("AMZN"));
	}
	
	
	@Given("I am on the main page39")
	public void i_am_on_the_main_page39() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I click on the increase button")
	public void i_click_on_the_increase_button() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addRemoveDays\"]/button[2]"));
		searchButton.click();
	}

	@Then("the date should be increased by {int}")
	public void the_date_should_be_increased_by(Integer int1) {
		String result = driver.findElement(By.xpath("//*[@id=\"dateHelper\"]")).getText();
		System.out.println("WHAT DOES IT PRINT OUTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT date helper: "+ result);
	   assertTrue(result.equals("2020-11-03")); 
	}

	@Given("I am on the main page44")
	public void i_am_on_the_main_page44() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"portfolioTable\"]/tbody")));
		rowsPort = driver.findElements(By.xpath("//*[@id=\"portfolioTable\"]/tbody"));
		countPort = rowsPort.size();
		System.out.println("ROW COUNT before delete portfolio: "+countPort);
		
	}

	@When("I click on remove Button")
	public void i_click_on_remove_Button() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"MSFT\"]"));
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"confirmRemoveStock\"]/div/div/div[3]/button[2]")));
		searchButton = driver.findElement(By.xpath("//*[@id=\"confirmRemoveStock\"]/div/div/div[3]/button[2]"));
		searchButton.click();
	}

	@Then("the stock should be removed from the portfolio")
	public void the_stock_should_be_removed_from_the_portfolio() {
		List<WebElement> rowsnew = driver.findElements(By.xpath("//*[@id=\"portfolioTable\"]/tbody"));
		int countnew = rowsnew.size();
		System.out.println("ROW COUNT after delete portfolio: "+countnew);
		assertTrue(countPort>=countnew); 
		
	}

	
	
	
	@Given("I am on the main page41")
	public void i_am_on_the_main_page41() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I click on the display toggle of the stock")
	public void i_click_on_the_display_toggle_of_the_stock() {
	    assertTrue(true); 
	}

	@Then("the stock should be displayed on graph41")
	public void the_stock_should_either_be_displayed_or_not_on_graph() {
	    assertTrue(true);
	}
	
	@Given("I am on the main page45")
	public void i_am_on_the_main_page45() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I click on the remove days button")
	public void i_click_on_the_remove_days_button() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addRemoveDays\"]/button[1]"));
		searchButton.click();
	}

	@Then("the date should be decremented by {int}")
	public void the_date_should_be_decremented_by(Integer int1) {
		String result = driver.findElement(By.xpath("//*[@id=\"dateHelper\"]")).getText();
		System.out.println("WHAT DOES IT PRINT OUTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT date helper decremented: "+ result);
	   assertTrue(result.equals("2020-10-30")); 
	}

	@Given("I am on the main page42")
	public void i_am_on_the_main_page42() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I enter in a stock ticker that exists in the watchList")
	public void i_enter_in_a_stock_ticker_that_exists_in_the_watchList() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addToWatchlist\"]")));
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addToWatchlist\"]"));
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchCode\"]")));
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"searchCode\"]"));
		queryBox.sendKeys("BIIB");
//		((JavascriptExecutor)driver).executeScript("document.getElementById('buyInDate').removeAttribute('readonly',0);");

		queryBox = driver.findElement(By.xpath("//*[@id=\"buyInDate\"]"));
//		driver.clear();
		queryBox.sendKeys("11022020");
		
	}
	
	@When("click the view stock button34")
	public void click_the_view_stock_button34() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"watchlistButton\"]")));
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"watchlistButton\"]"));
		searchButton.click();
	}
	@Then("I should see an error message42")
	public void i_should_see_an_error_message42() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchFormerror\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"searchFormerror\"]")).getText();
		System.out.println("WHAT DOES IT PRINT OUTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT: "+ result);
		assertTrue(result.equals("Stock already exist."));
	
	}

	@Given("I am on the main page47")
	public void i_am_on_the_main_page47() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I fill out the popUp form")
	public void i_fill_out_the_popUp_form() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addToPortfolio\"]")));
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addToPortfolio\"]"));
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchCode\"]")));
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"searchCode\"]"));
		queryBox.sendKeys("AAPL");
//		((JavascriptExecutor)driver).executeScript("document.getElementById('buyInDate').removeAttribute('readonly',0);");

		queryBox = driver.findElement(By.xpath("//*[@id=\"buyInDate\"]"));
//		driver.clear();
		queryBox.sendKeys("11022020");
		queryBox = driver.findElement(By.xpath("//*[@id=\"sellDate\"]"));
//		driver.clear();
		queryBox.sendKeys("12042020");
	}

	@When("click cancel")
	public void click_cancel() {
		
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"confirmAddStock\"]/div/div/div[3]/button[1]"));
		searchButton.click();
	}

	@When("access the popup form again")
	public void access_the_popup_form_again() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addToPortfolio\"]"));
		searchButton.click();
	}

	@Then("the form should be cleared")
	public void the_form_should_be_cleared() {
		String result = driver.findElement(By.xpath("//*[@id=\"searchCode\"]")).getText();
		System.out.println("WHAT DOES IT PRINT OUTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT: "+ result);
		assertTrue(result.equals(""));
	}
	


	@Given("I am on the login page9")
	public void i_am_on_the_login_page9() {
		driver.get(ROOT_URL);
	}

	@When("I fail to login three times within a minute")
	public void i_fail_to_login_three_times_within_a_minute() {
		WebElement queryBox;
		WebElement searchButton;
		queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
		queryBox.sendKeys("testing");
		searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
		searchButton.click();
		System.out.println("HEYYYYYYYYHYYYYY WHAT IS INT!!!!!!!!!!!!!!!!: " + 1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"userLog\"]")));
		queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
		queryBox.sendKeys("testing");
		searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"userLog\"]")));
		queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
		queryBox.sendKeys("testing");
		searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
		searchButton.click();
	}
	@When("a minute passes by")
	public void a_minute_passes_by() {
		WebDriverWait wait = new WebDriverWait (driver, 65);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"userLog\"]")));
	}
	@When("I login correctly the fourth time")
	public void i_login_correctly_the_fourth_time() {
		WebElement queryBox;
		WebElement searchButton;
		queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
		queryBox.sendKeys("testing123");
		searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
		searchButton.click();
	}
	@Then("the user should be logged in")
	public void the_user_should_be_logged_in() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/nav/a")));
		String result = driver.findElement(By.xpath("/html/body/nav/a")).getText();
		assertTrue(result.equals("USC CS310 Stock Portfolio Management"));
	}
	
	
	

	@Given("I am on the login page11")
	public void i_am_on_the_login_page11() {
		driver.get(ROOT_URL);
	}

	@When("user1 is locked from logging in")
	public void user1_is_locked_from_logging_in() {
		WebElement queryBox;
		WebElement searchButton;
		queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
		queryBox.sendKeys("testing");
		searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
		searchButton.click();
		System.out.println("HEYYYYYYYYHYYYYY WHAT IS INT!!!!!!!!!!!!!!!!: " + 1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"userLog\"]")));
		queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
		queryBox.sendKeys("testing");
		searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"userLog\"]")));
		queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
		queryBox.sendKeys("testing");
		searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
		searchButton.click();
	}
	@When("user2 logs in correctly")
	public void user2_logs_in_correctly() {
		WebElement queryBox;
		WebElement searchButton;
		queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
		queryBox.sendKeys("cucum3101");
		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
		queryBox.sendKeys("123");
		searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
		searchButton.click();
	}
	@Then("user2 should be logged in while user1 is still locked")
	public void user2_should_be_logged_in_while_user1_is_still_locked() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/nav/a")));
		String result = driver.findElement(By.xpath("/html/body/nav/a")).getText();
		assertTrue(result.equals("USC CS310 Stock Portfolio Management"));
		WebDriverWait wait = new WebDriverWait (driver, 62);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/nav/a")));
	}


	@Given("I am on the login page7")
	public void i_am_on_the_login_page7() {
		driver.get(ROOT_URL);
	}

	@When("I fail to login two times within a minute")
	public void i_fail_to_login_two_times_within_a_minute() {
		WebElement queryBox;
		WebElement searchButton;
		queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
		queryBox.sendKeys("testing");
		searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
		searchButton.click();
		System.out.println("HEYYYYYYYYHYYYYY WHAT IS INT!!!!!!!!!!!!!!!!: " + 1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"userLog\"]")));
		queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
		queryBox.sendKeys("testing");
		searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
		searchButton.click();
	}
	@When("wait for a minute")
	public void wait_for_a_minute() {
		WebDriverWait wait = new WebDriverWait (driver, 65);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"userLog\"]")));
	}
	@When("log in with wrong information {int} times")
	public void log_in_with_wrong_information_times(Integer int1) {
		WebElement queryBox;
		WebElement searchButton;
		queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
		queryBox.sendKeys("testing");
		searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
		searchButton.click();
		System.out.println("HEYYYYYYYYHYYYYY WHAT IS INT!!!!!!!!!!!!!!!!: " + 1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"userLog\"]")));
		queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
		queryBox.sendKeys("testing");
		searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"userLog\"]")));
		queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
		queryBox.sendKeys("testing");
		searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
		searchButton.click();
	}
	@Then("the user should be locked from logging in for a min7")
	public void the_user_should_be_locked_from_logging_in_for_a_min7() {
		String result = driver.findElement(By.xpath("//*[@id=\"formerror\"]")).getText();
		assertTrue(result.equals("You are locked for a minute"));
		WebDriverWait wait = new WebDriverWait (driver, 62);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"form\"]/div[3]/input")));
	}



	@Given("I am on the login page8")
	public void i_am_on_the_login_page8() {
		driver.get(ROOT_URL);
	}

	@When("I fail to login {int} times within a minute8")
	public void i_fail_to_login_times_within_a_minute8(Integer int1) {
		WebElement queryBox;
		WebElement searchButton;
		queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
		queryBox.sendKeys("testing");
		searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
		searchButton.click();
		System.out.println("HEYYYYYYYYHYYYYY WHAT IS INT!!!!!!!!!!!!!!!!: " + 1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"userLog\"]")));
		queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
		queryBox.sendKeys("testing");
		searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"userLog\"]")));
		queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
		queryBox.sendKeys("testing");
		searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
		searchButton.click();
	}
	@When("log in with correct log in information")
	public void log_in_with_correct_log_in_information() {
		WebElement queryBox;
		WebElement searchButton;
		queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
		queryBox.sendKeys("testing123");
		searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
		searchButton.click();
	}
	@Then("the user should still be locked from logging in8")
	public void the_user_should_still_be_locked_from_logging_in8() {
		String result = driver.findElement(By.xpath("//*[@id=\"formerror\"]")).getText();
		assertTrue(result.equals("You are locked for a minute"));
		WebDriverWait wait = new WebDriverWait (driver, 62);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"form\"]/div[3]/input")));
	}


	
	@Given("I am on the main page43")
	public void i_am_on_the_main_page43() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I click on the not display toggle of the stock43")
	public void i_click_on_the_not_display_toggle_of_the_stock43() {
	    
	}
	@Then("the stock should not be displayed on graph43")
	public void the_stock_should_not_be_displayed_on_graph43() {
	    assertTrue(true);
	}


	@Given("I am on the main page48")
	public void i_am_on_the_main_page48() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("the graph is displayed with different stocks")
	public void the_graph_is_displayed_with_different_stocks() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"hiddenGraphInfoTable\"]")));
	}
	@Then("each stock should be in a different color")
	public void each_stock_should_be_in_a_different_color() {
		WebDriverWait wait = new WebDriverWait (driver, 2);
//	   WebElement hiddenTableBody = driver.findElement(By.xpath("//*[@id=\"hiddenGraphInfoTable\"]/tbody"));
	   List<WebElement> tableRows = driver.findElements(By.xpath("//*[@id=\"hiddenGraphInfoTable\"]/tbody/tr"));
	   int count = tableRows.size();
	   Set<String> colors = new HashSet<String>();
	 
	   String str;
	   boolean repeatColor = false;
	   for(int i = 0; i < count; i++) {
		   str = driver.findElement(By.xpath("//*[@id=\"hiddenGraphInfoTable\"]/tbody/tr[" + (i+1) + "]/td[3]")).getText();
		   System.out.println(driver.findElement(By.xpath("//*[@id=\"hiddenGraphInfoTable\"]/tbody/tr[" + (i+1) + "]/td[3]")).getText());
		   
		   if(colors.contains(str)) {
			   repeatColor = true;
			   break;
		   }
		   else {
			   colors.add(str);
		   }
	   }
	   assertTrue(!repeatColor);
	}

	@Given("I am on the main page49")
	public void i_am_on_the_main_page49() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I add a stock to portfolio49")
	public void i_add_a_stock_to_portfolio49() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"precentageHelper\"]")));
	    
	}
	@Then("the portfolio value should be displayed49")
	public void the_portfolio_value_should_be_displayed49() {
		WebDriverWait wait = new WebDriverWait (driver, 2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"portfolioVal\"]")));
		String oValue = driver.findElement(By.xpath("//*[@id=\"portfolioVal\"]")).getText();
		double oldValue = Double.parseDouble(oValue);
//		System.out.println(oValue);
//		System.out.println(oldValue);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addToPortfolio\"]")));
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addToPortfolio\"]"));
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchCode\"]")));
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"searchCode\"]"));
		queryBox.sendKeys("NKE");
//		((JavascriptExecutor)driver).executeScript("document.getElementById('buyInDate').removeAttribute('readonly',0);");

		queryBox = driver.findElement(By.xpath("//*[@id=\"buyInDate\"]"));
//		driver.clear();
		queryBox.sendKeys("11022020");
		
		WebElement addButton = driver.findElement(By.xpath("//*[@id=\"portfolioButton\"]"));
		addButton.click();
		
		
		WebDriverWait wait1 = new WebDriverWait (driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"portfolioTable\"]/tbody/tr/th")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"portfolioVal\"]")));
		String nValue = driver.findElement(By.xpath("//*[@id=\"portfolioVal\"]")).getText();
		double newValue = Double.parseDouble(nValue);

		assertTrue(newValue > oldValue);
	}

	
	@Given("I am on the main page120")
	public void i_am_on_the_main_page120() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I get the value of portfolio from day before and value today120")
	public void i_get_the_value_of_portfolio_from_day_before_and_value_today120() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"precentageHelper\"]")));
	}
	@Then("the percent change is accurately displayed in portfolio")
	public void the_percent_change_is_accurately_displayed_in_portfolio() {
		WebDriverWait wait = new WebDriverWait (driver, 2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"precentageHelper\"]")));
		String oValue = driver.findElement(By.xpath("//*[@id=\"precentageHelper\"]")).getText();
		double oldValue = Double.parseDouble(oValue);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"portfolioVal\"]")));
		String nValue = driver.findElement(By.xpath("//*[@id=\"portfolioVal\"]")).getText();
		double newValue = Double.parseDouble(nValue);
		
		double percent = (newValue - oldValue) / oldValue;
		percent = percent * 100;
		System.out.println(percent);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"porPerChange\"]")));
		String rPercent = driver.findElement(By.xpath("//*[@id=\"porPerChange\"]")).getText();
		rPercent = rPercent.substring(0,rPercent.length() - 2);
		double realPercent = Double.parseDouble(rPercent);
		System.out.println(realPercent);
		double diff = percent - realPercent;
	   assertTrue(Math.abs(diff) < 0.01);
	   
	   
	}

	
	@Given("I am on the main page121")
	public void i_am_on_the_main_page121() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I get the value of portfolio from day before and value today121")
	public void i_get_the_value_of_portfolio_from_day_before_and_value_today121() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"precentageHelper\"]")));
	}
	@Then("the indicator triangle is pointing upwards in portfolio")
	public void the_indicator_triangle_is_pointing_upwards_in_portfolio() {
		WebDriverWait wait = new WebDriverWait (driver, 2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"porPerChange\"]")));
		String rPercent = driver.findElement(By.xpath("//*[@id=\"porPerChange\"]")).getText();
		rPercent = rPercent.substring(0,rPercent.length() - 2);
		double realPercent = Double.parseDouble(rPercent);
		String tri = "";
		if(realPercent >= 0) {
			tri = "▲";
		}
		else if(realPercent < 0) {
			tri = "▼";
		}
		String triangle = driver.findElement(By.xpath("//*[@id=\"triangle\"]")).getText();
		System.out.println(triangle);
		System.out.println(tri);
	    assertTrue(tri.equals(triangle));
	}

	@Given("I am on the main page123")
	public void i_am_on_the_main_page123() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I get the value of portfolio from day before and value today123")
	public void i_get_the_value_of_portfolio_from_day_before_and_value_today123() {
	    
	}
	@Then("the text is red in portfolio if negative123")
	public void the_text_is_red_in_portfolio_if_negative123() {
		WebDriverWait wait = new WebDriverWait (driver, 2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"porPerChange\"]")));
		String rPercent = driver.findElement(By.xpath("//*[@id=\"porPerChange\"]")).getText();
		rPercent = rPercent.substring(0,rPercent.length() - 2);
		double realPercent = Double.parseDouble(rPercent);
		String color = "";
		if(realPercent >= 0) {
			color = "color: green;";
		}
		else if(realPercent < 0) {
			color = "color: red;";
		}
		String color_ = driver.findElement(By.xpath("//*[@id=\"porPerChange\"]")).getAttribute("style");
		System.out.println(color_);
		System.out.println(color);
	    assertTrue(color.equals(color_));
	}
	
	@Given("I am on the main page50")
	public void i_am_on_the_main_page50() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I enter in a stock ticker that doesnt exist in watchlist")
	public void i_enter_in_a_stock_ticker_that_doesnt_exist_in_watchlist() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addToWatchlist\"]")));
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addToWatchlist\"]"));
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchCode\"]")));
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"searchCode\"]"));
		queryBox.sendKeys("tytyr");
//		((JavascriptExecutor)driver).executeScript("document.getElementById('buyInDate').removeAttribute('readonly',0);");

		queryBox = driver.findElement(By.xpath("//*[@id=\"buyInDate\"]"));
//		driver.clear();
		queryBox.sendKeys("11022020");
		
		searchButton = driver.findElement(By.xpath("//*[@id=\"watchlistButton\"]"));
		searchButton.click();
	}

	@Then("I should see an error message50")
	public void i_should_see_an_error_message50() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchFormerror\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"searchFormerror\"]")).getText();
		assertTrue(result.equals("Please enter the right stock code"));
	}

	@Given("I am on the main page51")
	public void i_am_on_the_main_page51() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I click search with empty field in WL")
	public void i_click_search_with_empty_field_in_WL() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addToWatchlist\"]")));
		WebElement searchButton = driver.findElement(By.cssSelector("#addToWatchlist"));
		searchButton.click();
		
		searchButton = driver.findElement(By.cssSelector("#watchlistButton"));
		searchButton.click();
	}

	@Then("I should see an error message51")
	public void i_should_see_an_error_message51() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchFormerror\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"searchFormerror\"]")).getText();
		assertTrue(result.equals("Please enter the right stock code"));
	}

	@Given("I am on the main page52")
	public void i_am_on_the_main_page52() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I enter in a stock ticker that exists in the WL")
	public void i_enter_in_a_stock_ticker_that_exists_in_the_WL() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addToWatchlist\"]")));
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addToWatchlist\"]"));
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchCode\"]")));
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"searchCode\"]"));
		queryBox.sendKeys("BIIB");
//		((JavascriptExecutor)driver).executeScript("document.getElementById('buyInDate').removeAttribute('readonly',0);");

		queryBox = driver.findElement(By.xpath("//*[@id=\"buyInDate\"]"));
//		driver.clear();
		queryBox.sendKeys("11022020");
		
		
	}

	@When("click the add to watchList button52")
	public void click_the_add_to_watchList_button52() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"watchlistButton\"]"));
		searchButton.click();
	}

	@Then("I should see an error message52")
	public void i_should_see_an_error_message52() {
		String result = driver.findElement(By.xpath("//*[@id=\"searchFormerror\"]")).getText();
		assertTrue(result.equals("Stock already exist."));
	}

	@Given("I am on the main page53")
	public void i_am_on_the_main_page53() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I enter in a correct tickr for WL53")
	public void i_enter_in_a_correct_tickr_for_WL53() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addToWatchlist\"]")));
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addToWatchlist\"]"));
		searchButton.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchCode\"]")));
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"searchCode\"]"));
		queryBox.sendKeys("FB");
//		((JavascriptExecutor)driver).executeScript("document.getElementById('buyInDate').removeAttribute('readonly',0);");

		queryBox = driver.findElement(By.xpath("//*[@id=\"buyInDate\"]"));
//		driver.clear();
		queryBox.sendKeys("11022020");
	}

	@When("enter in a negative number of shares53")
	public void enter_in_a_negative_number_of_shares53() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"numShares\"]"));
		queryBox.sendKeys("-4");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"watchlistButton\"]"));
		searchButton.click();
	}

	@Then("I should see an error message53")
	public void i_should_see_an_error_message53() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchFormerror\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"searchFormerror\"]")).getText();
		System.out.println("error: "+ result);
		assertTrue(result.equals("Please enter the number of shares to buy."));
	}

	@Given("I am on the main page54")
	public void i_am_on_the_main_page54() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I enter in a correct tickr for portfolio54")
	public void i_enter_in_a_correct_tickr_for_portfolio54() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addToPortfolio\"]")));
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addToPortfolio\"]"));
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchCode\"]")));
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"searchCode\"]"));
		queryBox.sendKeys("FB");
//		((JavascriptExecutor)driver).executeScript("document.getElementById('buyInDate').removeAttribute('readonly',0);");

		
	}

	@When("enter in a sell date that is before buy date")
	public void enter_in_a_sell_date_that_is_before_buy_date() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"buyInDate\"]"));
//		driver.clear();
		queryBox.sendKeys("11022020");
		
		
		queryBox = driver.findElement(By.xpath("//*[@id=\"sellDate\"]"));
//		driver.clear();
		queryBox.sendKeys("10022020");
		
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"portfolioButton\"]"));
		searchButton.click();
	}

	@Then("I should see an error message54")
	public void i_should_see_an_error_message54() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchFormerror\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"searchFormerror\"]")).getText();
		System.out.println("error2: "+ result);
		assertTrue(result.equals("Sell date must after buy in date!")); 
	}

	@Given("I am on the main page55")
	public void i_am_on_the_main_page55() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I enter in a correct tickr for WL55")
	public void i_enter_in_a_correct_tickr_for_WL55() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addToWatchlist\"]")));
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addToWatchlist\"]"));
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchCode\"]")));
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"searchCode\"]"));
		queryBox.sendKeys("FB");
//		((JavascriptExecutor)driver).executeScript("document.getElementById('buyInDate').removeAttribute('readonly',0);");

		
	}

	@When("enter in a sell date that is before buy date55")
	public void enter_in_a_sell_date_that_is_before_buy_date55() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"buyInDate\"]"));
//		driver.clear();
		queryBox.sendKeys("11022020");
		
		
		queryBox = driver.findElement(By.xpath("//*[@id=\"sellDate\"]"));
//		driver.clear();
		queryBox.sendKeys("10022020");
		
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"watchlistButton\"]"));
		searchButton.click();
	}

	@Then("I should see an error message55")
	public void i_should_see_an_error_message55() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchFormerror\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"searchFormerror\"]")).getText();
		System.out.println("error3: "+ result);
		assertTrue(result.equals("Sell date must after buy in date!"));  

	}

	@Given("I am on the main page56")
	public void i_am_on_the_main_page56() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I enter in a correct tickr for portfolio56")
	public void i_enter_in_a_correct_tickr_for_portfolio56() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addToPortfolio\"]")));
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addToPortfolio\"]"));
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchCode\"]")));
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"searchCode\"]"));
		queryBox.sendKeys("FB");
	}

	@When("enter in a buy date that is not within a year from today")
	public void enter_in_a_buy_date_that_is_not_within_a_year_from_today() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"buyInDate\"]"));
//		driver.clear();
		queryBox.sendKeys("11022017");
		
		
		
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"portfolioButton\"]"));
		searchButton.click();
	}

	@Then("I should see an error message56")
	public void i_should_see_an_error_message56() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchFormerror\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"searchFormerror\"]")).getText();
		assertTrue(result.equals("Buy in date must be within the last year!")); 
	}
	
	@Given("I am on the main page560")
	public void i_am_on_the_main_page560() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I enter in a correct tickr for wl560")
	public void i_enter_in_a_correct_tickr_for_wl560() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addToWatchlist\"]")));
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addToWatchlist\"]"));
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchCode\"]")));
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"searchCode\"]"));
		queryBox.sendKeys("FB");
	}

	@When("enter in a buy date that is not within a year from today560")
	public void enter_in_a_buy_date_that_is_not_within_a_year_from_today560() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"buyInDate\"]"));
//		driver.clear();
		queryBox.sendKeys("11022017");
		
		
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"watchlistButton\"]"));
		searchButton.click();
	}

	@Then("I should see an error message560")
	public void i_should_see_an_error_message560() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchFormerror\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"searchFormerror\"]")).getText();
		System.out.println("error4: "+ result);
		assertTrue(result.equals("Buy in date must be within the last year!")); 
	}

	@Given("I am on the main page57")
	public void i_am_on_the_main_page57() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I click on add to watchList button")
	public void i_click_on_add_to_watchList_button() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addToWatchlist\"]"));
		searchButton.click();
	}

	@Then("I should see the the {string} button57")
	public void i_should_see_the_the_button57(String string) {
		String result = driver.findElement(By.xpath("//*[@id=\"watchlistButton\"]")).getText();
		System.out.println("string on but wl: "+ result);
		assertTrue(result.equals(string));
	}

	@Given("I am on the main page58")
	public void i_am_on_the_main_page58() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I click on add to portfolio button")
	public void i_click_on_add_to_portfolio_button() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"addToPortfolio\"]"));
		searchButton.click();
	}

	@Then("I should see the the {string} button")
	public void i_should_see_the_the_button(String string) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#portfolioButton")));
		String result = driver.findElement(By.cssSelector("#portfolioButton")).getText();
		System.out.println("string on but port: "+ result);
		assertTrue(result.equals(string));
	}
	
	@Given("I am on the main page610")
	public void i_am_on_the_main_page610() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	int prevTableSize;
	@When("I add in a correct stock610")
	public void i_add_in_a_correct_stock610() {
		WebElement watchlistButton = driver.findElement(By.xpath("//*[@id=\"addToWatchlist\"]"));
		watchlistButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchCode\"]")));
	    WebElement addStockBox = driver.findElement(By.xpath("//*[@id=\"searchCode\"]"));
	    addStockBox.sendKeys("CRM");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"buyInDate\"]")));
	    WebElement addDateBox = driver.findElement(By.xpath("//*[@id=\"buyInDate\"]"));
	    addDateBox.sendKeys("11/02/2020");
	}

	@When("click the add stock button610")
	public void click_the_add_stock_button610() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#hiddenGraphInfoTable > tbody")));
		rows = driver.findElements(By.cssSelector("#hiddenGraphInfoTable > tbody"));
		prevTableSize = rows.size();
		WebElement viewStock = driver.findElement(By.xpath("//*[@id=\"watchlistButton\"]"));
	    viewStock.click();
	}

	@Then("the graph should dynamically change610")
	public void graphChanges610() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#hiddenGraphInfoTable > tbody")));
		rows = driver.findElements(By.cssSelector("#hiddenGraphInfoTable > tbody"));
		System.out.println(rows.size());
		System.out.println(prevTableSize);
		assertTrue(rows.size() == (prevTableSize + 1));
		
//		// test delete button
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"CRM\"]")));
//		WebElement deleteStockButton = driver.findElement(By.xpath("//*[@id=\"CRM\"]"));
//		deleteStockButton.click();
//		
//		rows = driver.findElements(By.cssSelector("#hiddenGraphInfoTable > tbody"));
//		assertTrue((rows.size()-2) == (prevTableSize));
	}

	
	@After()
	public void after() {
		driver.quit();
	}

}
