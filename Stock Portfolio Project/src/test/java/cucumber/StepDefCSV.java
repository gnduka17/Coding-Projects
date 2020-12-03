package cucumber;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefCSV {
	private static final String ROOT_URL = "http://localhost:8080/";
	private static final String REG_URL = "http://localhost:8080/Register.jsp";
	private static final String MAIN_URL = "http://localhost:8080/MainPage.jsp";
	private final WebDriver driver = new ChromeDriver();
//	private Map<String, String> mobileEmulation = new HashMap<>();
//	private WebDriver mobileDriver = new ChromeDriver();
	private WebDriverWait wait = new WebDriverWait(driver, 5);
	private static String path = StepDefCSV.class.getClassLoader().getResource("csci310/file.csv").getPath()
			.toString();
	private static String path2 = StepDefCSV.class.getClassLoader().getResource("csci310/testCSVFile.csv")
			.getPath().toString();
	private static String path3 = StepDefCSV.class.getClassLoader().getResource("csci310/badFormat.csv").getPath()
			.toString();
	private static String path4 = StepDefCSV.class.getClassLoader().getResource("csci310/badFormat2.csv")
			.getPath().toString();
	private static String path5 = StepDefCSV.class.getClassLoader().getResource("csci310/blankFile.csv").getPath()
			.toString();
	private static String path6 = StepDefCSV.class.getClassLoader().getResource("csci310/file2.csv").getPath()
			.toString();
	private static String path7 = StepDefCSV.class.getClassLoader().getResource("csci310/file3.csv").getPath()
			.toString();
	@BeforeClass
	public void initialize() {
		path = path.substring(1);
		// path =
		// csvCucumberTests.class.getClassLoader().getResource("csci310/file.csv").getPath();
		// path = "C:/Users/rw17a/Downloads/file.csv";
	}

	/********** LOGIN FEATURE TESTS ***************/
	// Scenario 6
	@Given("I am on the main page500")
	public void i_am_on_the_main_page500() {
		System.out.println(path);
		System.out.println(path2);
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("21savage");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("test");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I upload a CSV file that is formatted correctly")
	public void i_click_the_Logout_link() throws InterruptedException {
		WebElement searchButton = driver.findElement(By.id("uploadCSV"));
		searchButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"csvSubmitButton\"]")));

		WebElement uploadElement = driver.findElement(By.id("uploadSubmit"));
		System.out.println("path is: " + path);
		System.out.println("cut path is: " + path.substring(1));
		uploadElement.sendKeys(path.substring(1));

		// uploadElement.sendKeys(path);
		System.out.println("path entered");
		WebElement enterButton = driver.findElement(By.id("csvSubmitButton"));
		enterButton.click();

		System.out.println("upload clicked");
	}

	@Then("I should see a successful display")
	public void i_should_be_redirected_to_the_login_page() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addButtons\"]/p")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"uploadCsvMessage\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"addButtons\"]/p")).getText();

		System.out.println("result: " + result);
		assertTrue(result.contains("successfully"));
	}

	// Upload CSV file with duplicate stock
	@Given("I am on the main page501")
	public void i_am_on_the_main_page501() {
		System.out.println(path);
		System.out.println(path2);
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("21savage");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("test");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I upload a CSV file that is already added")
	public void i_upload_a_csv_file_that_is_already_added() throws InterruptedException {
		WebElement searchButton = driver.findElement(By.id("uploadCSV"));
		searchButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"csvSubmitButton\"]")));

		WebElement uploadElement = driver.findElement(By.id("uploadSubmit"));
		System.out.println("path is: " + path);
		System.out.println("cut path is: " + path.substring(1));
		uploadElement.sendKeys(path2.substring(1));

		// uploadElement.sendKeys(path);
		System.out.println("path entered");
		WebElement enterButton = driver.findElement(By.id("csvSubmitButton"));
		enterButton.click();

		System.out.println("upload clicked");
	}

	@Then("I should see a duplicate stock display")
	public void i_should_see_a_duplicate_stock_display() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addButtons\"]/p")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"uploadCsvMessage\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"addButtons\"]/p")).getText();

		System.out.println("result: " + result);
		assertTrue(result.contains("duplicate"));
	}

	// Upload CSV file with an invalid buy in date
	@Given("I am on the main page502")
	public void i_am_on_the_main_page502() {
		System.out.println(path);
		System.out.println(path2);
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("21savage");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("test");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I upload a CSV file with an invalid buy in date")
	public void i_upload_a_CSV_file_with_an_invalid_buy_in_date() throws InterruptedException {
		WebElement searchButton = driver.findElement(By.id("uploadCSV"));
		searchButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"csvSubmitButton\"]")));

		WebElement uploadElement = driver.findElement(By.id("uploadSubmit"));
		System.out.println("path is: " + path);
		System.out.println("cut path is: " + path.substring(1));
		uploadElement.sendKeys(path3.substring(1));

		// uploadElement.sendKeys(path);
		System.out.println("path entered");
		WebElement enterButton = driver.findElement(By.id("csvSubmitButton"));
		enterButton.click();

		System.out.println("upload clicked");
	}

	@Then("I should see an invalid date display")
	public void i_should_see_an_invalid_date_display() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addButtons\"]/p")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"uploadCsvMessage\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"addButtons\"]/p")).getText();

		System.out.println("result: " + result);
		assertTrue(result.contains("invalid"));
	}

	// Upload CSV file that is not formatted correctly
	@Given("I am on the main page503")
	public void i_am_on_the_main_page503() {
		System.out.println(path);
		System.out.println(path2);
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("21savage");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("test");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I upload a CSV file with an invalid format")
	public void i_upload_a_csv_file_with_an_invalid_format() throws InterruptedException {
		WebElement searchButton = driver.findElement(By.id("uploadCSV"));
		searchButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"csvSubmitButton\"]")));

		WebElement uploadElement = driver.findElement(By.id("uploadSubmit"));
		System.out.println("path is: " + path);
		System.out.println("cut path is: " + path.substring(1));
		uploadElement.sendKeys(path4.substring(1));

		// uploadElement.sendKeys(path);
		System.out.println("path entered");
		WebElement enterButton = driver.findElement(By.id("csvSubmitButton"));
		enterButton.click();

		System.out.println("upload clicked");
	}

	@Then("I should see a format error display")
	public void i_should_see_a_format_error_display() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addButtons\"]/p")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"uploadCsvMessage\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"addButtons\"]/p")).getText();

		System.out.println("testing result: " + result);
		assertTrue(result.contains("formatted"));
	}

	// Upload CSV file with the dates being the same
	@Given("I am on the main page504")
	public void i_am_on_the_main_page504() {
		System.out.println(path);
		System.out.println(path2);
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("21savage");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("test");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I upload a CSV file with the same dates")
	public void i_upload_a_csv_file_with_the_same_dates() throws InterruptedException {
		WebElement searchButton = driver.findElement(By.id("uploadCSV"));
		searchButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"csvSubmitButton\"]")));

		WebElement uploadElement = driver.findElement(By.id("uploadSubmit"));
		System.out.println("path is: " + path);
		System.out.println("cut path is: " + path.substring(1));
		uploadElement.sendKeys(path5.substring(1));

		// uploadElement.sendKeys(path);
		System.out.println("path entered");
		WebElement enterButton = driver.findElement(By.id("csvSubmitButton"));
		enterButton.click();

		System.out.println("upload clicked");
	}

	@Then("I should see an invalid date display3")
	public void i_should_see_an_invalid_date_display3() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addButtons\"]/p")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"uploadCsvMessage\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"addButtons\"]/p")).getText();

		System.out.println("result: " + result);
		assertTrue(result.contains("invalid"));
	}

	// Upload CSV file with invalid sell in date
	@Given("I am on main page505")
	public void i_am_on_main_page505() {
		System.out.println(path);
		System.out.println(path2);
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("21savage");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("test");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I upload a CSV file with an invalid sell in date")
	public void i_upload_a_csv_file_with_an_invalid_sell_in_date() throws InterruptedException {
		WebElement searchButton = driver.findElement(By.id("uploadCSV"));
		searchButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"csvSubmitButton\"]")));

		WebElement uploadElement = driver.findElement(By.id("uploadSubmit"));
		System.out.println("path is: " + path);
		System.out.println("cut path is: " + path.substring(1));
		uploadElement.sendKeys(path6.substring(1));

		// uploadElement.sendKeys(path);
		System.out.println("path entered");
		WebElement enterButton = driver.findElement(By.id("csvSubmitButton"));
		enterButton.click();

		System.out.println("upload clicked");
	}

	@Then("I should see an invalid date display2")
	public void i_should_see_an_invalid_date_display2() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addButtons\"]/p")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"uploadCsvMessage\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"addButtons\"]/p")).getText();

		System.out.println("result: " + result);
		assertTrue(result.contains("invalid"));
	}

	//  Upload a CSV file with nonexistent stock
	@Given("I am on main page506")
	public void i_am_on_main_page506() {
		System.out.println(path);
		System.out.println(path2);
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("21savage");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("test");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I upload a CSV file with invalid stock")
	public void i_upload_a_csv_file_with_invalid_stock() throws InterruptedException {
		WebElement searchButton = driver.findElement(By.id("uploadCSV"));
		searchButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"csvSubmitButton\"]")));

		WebElement uploadElement = driver.findElement(By.id("uploadSubmit"));
		System.out.println("path is: " + path);
		System.out.println("cut path is: " + path.substring(1));
		uploadElement.sendKeys(path7.substring(1));

		// uploadElement.sendKeys(path);
		System.out.println("path entered");
		WebElement enterButton = driver.findElement(By.id("csvSubmitButton"));
		enterButton.click();

		System.out.println("upload clicked");
	}

	@Then("I should see a stock error display")
	public void i_should_see_a_stock_error_display() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addButtons\"]/p")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"uploadCsvMessage\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"addButtons\"]/p")).getText();

		System.out.println("result: " + result);
		assertTrue(result.contains("does not exist"));
	}

	@After()
	public void after() {
		driver.quit();
//		mobileDriver.quit();
	}

}
