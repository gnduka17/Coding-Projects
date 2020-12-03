package cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

import java.util.*;
import java.util.Map;

/**
 * Step definitions for Cucumber tests.
 */
public class StepDefinitions {
	private static final String ROOT_URL = "http://localhost:8080/";
	private static final String REG_URL = "http://localhost:8080/Register.jsp";
	private static final String MAIN_URL = "http://localhost:8080/MainPage.jsp";
	private final WebDriver driver = new ChromeDriver();
//	private Map<String, String> mobileEmulation = new HashMap<>();
//	private WebDriver mobileDriver = new ChromeDriver();
	private WebDriverWait wait = new WebDriverWait (driver, 5);
	
	
	/**********LOGIN FEATURE TESTS***************/
	
	//Feature 1
	@Given("I am on the login page")
	public void i_am_on_the_login_page() {
		driver.get(ROOT_URL);
//		mobileDriver.get(ROOT_URL);
		
	}
	@When("I enter in a wrong username")
	public void i_enter_in_a_wrong_username() {
//		WebElement queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
//		queryBox.sendKeys("wrong");
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("wrong");
	}
	@When("enter in a password")
	public void enter_in_a_password() {
//		WebElement queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
//		queryBox.sendKeys("password");
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("password");
	}
	@When("I click the login button")
	public void i_click_the_login_button() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
//		searchButton =mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
//		searchButton.click();
	
	}
	@Then("I should see an error message in regards to the wrong username entered")
	public void i_should_see_an_error_message_in_regards_to_the_wrong_username_entered() {
//		String result = mobileDriver.findElement(By.xpath("//*[@id=\"formerror\"]")).getText();
//		assertTrue(result.equals("Your username or password is incorrect."));
		String result= driver.findElement(By.xpath("//*[@id=\"formerror\"]")).getText();
		assertTrue(result.equals("Your username or password is incorrect."));
	}
	
	
	//Feature 2
	@Given("I am on the login page1")
	public void i_am_on_the_login_page1() {
		driver.get(ROOT_URL);
	}

	@When("I enter in the correct user name1")
	public void i_enter_in_the_correct_user_name1() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
	}

	@When("enter in a wrong password")
	public void enter_in_a_wrong_password() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("password");
	}
	@When("I click the login button1")
	public void i_click_the_login_button1() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@Then("I should see an error message in regards to the wrong password entered")
	public void i_should_see_an_error_message_in_regards_to_the_wrong_password_entered() {
		String result = driver.findElement(By.xpath("//*[@id=\"formerror\"]")).getText();
		assertTrue(result.equals("Your username or password is incorrect."));
	}

	//Feature 3
	@Given("I am on the login page2")
	public void i_am_on_the_login_page2() {
		driver.get(ROOT_URL);
	}

	@When("I enter in a correct username2")
	public void i_enter_in_a_correct_username2() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
	}

	@When("enter in a correct password")
	public void enter_in_a_correct_password() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
	}
	@When("I click the login button2")
	public void i_click_the_login_button2() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		
		searchButton.click();
	}
	@Then("I should be successfully logged in")
	public void i_should_be_successfully_logged_in() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/nav/a")));
		String result = driver.findElement(By.xpath("/html/body/nav/a")).getText();
		assertTrue(result.equals("USC CS310 Stock Portfolio Management"));
	}

	//Feature 4
	@Given("I am on the login page3")
	public void i_am_on_the_login_page3() {
		driver.get(ROOT_URL);
	}

	@When("I leave the input field for username empty")
	public void i_leave_the_input_field_for_username_empty() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("");
	}

	@When("I leave the input field for password empty")
	public void i_leave_the_input_field_for_password_empty() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("");
		
	}
	@When("I click the login button3")
	public void i_click_the_login_button3() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}
	@Then("an error should be displayed in regards to the input fields left blank")
	public void an_error_should_be_displayed_in_regards_to_the_input_fields_left_blank() {
		String result = driver.findElement(By.xpath("//*[@id=\"formerror\"]")).getText();
		assertTrue(result.equals("Please do not leave blank."));
	}
	
	//Feature 5
	@Given("I am on the login page4")
	public void i_am_on_the_login_page4() {
		driver.get(ROOT_URL);
	}

	@When("I leave the input field for username empty4")
	public void i_leave_the_input_field_for_username_empty4() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("");
	}

	@When("I type in a password")
	public void i_type_in_a_password() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("password");
	}
	@When("I click the login button4")
	public void i_click_the_login_button4() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}
	@Then("an error should be displayed in regards to the input field left blank")
	public void an_error_should_be_displayed_in_regards_to_the_input_field_left_blank() {
		String result = driver.findElement(By.xpath("//*[@id=\"formerror\"]")).getText();
		assertTrue(result.equals("Please enter your user name."));
	}

	//Feature 6
	@Given("I am on the login page5")
	public void i_am_on_the_login_page5() {
		driver.get(ROOT_URL);
	}

	@When("I type in a user name")
	public void i_type_in_a_user_name() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
	}

	@When("I leave the input field for password empty5")
	public void i_leave_the_input_field_for_password_empty5() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("");
	}
	@When("I click the login button5")
	public void i_click_the_login_button5() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}
	@Then("an error should be displayed in regards to the input fields left blank5")
	public void an_error_should_be_displayed_in_regards_to_the_input_fields_left_blank5() {
		String result = driver.findElement(By.xpath("//*[@id=\"formerror\"]")).getText();
		assertTrue(result.equals("Your username or password is incorrect."));
	}
	
	
	/**********PAGE FLOW FEATURE TESTS***************/

	//Feature1
	@Given("I am on the register page")
	public void i_am_on_the_register_page() {
		driver.get(REG_URL);
	}

	@When("I enter in a successful username")
	public void i_enter_in_a_successful_username() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("CS3101");
	}

	@When("enter in successful passwords in both input fields")
	public void enter_in_successful_passwords_in_both_input_fields() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("Project1");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/input"));
		queryBox.sendKeys("Project1");
	}

	@When("click on Create User button")
	public void click_on_Create_User_button() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"registerButton\"]"));
		searchButton.click();
	}

	@Then("I should be directed to the login page")
	public void i_should_be_directed_to_the_login_page() {
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("//*[@id=\"form\"]/div[1]/div/p")));
		String result = driver.findElement(By.xpath("//*[@id=\"form\"]/div[1]/div/p")).getText();
		assertTrue(result.equals("Login"));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#form > div:nth-child(1) > div > p")));
//		String result = driver.findElement(By.cssSelector("#form > div:nth-child(1) > div > p")).getText();
//		assertTrue(result.equals("Login"));
	}
	
	//Feature 2
	@Given("I am on the Login page1")
	public void i_am_on_the_Login_page1() {
		driver.get(ROOT_URL);
	}

	@When("I enter in the correct user name")
	public void i_enter_in_the_correct_user_name() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
	}

	@When("enter in the correct password for that user name")
	public void enter_in_the_correct_password_for_that_user_name() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
	}

	@When("click on Login button")
	public void click_on_Login_button() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@Then("I should be directed to the main page")
	public void i_should_be_directed_to_the_main_page() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/nav/a")));
		String result = driver.findElement(By.xpath("/html/body/nav/a")).getText();
		assertTrue(result.equals("USC CS310 Stock Portfolio Management"));
	}

	//Feature 3
	@Given("I am on the Login page")
	public void i_am_on_the_Login_page() {
		driver.get(ROOT_URL);
	}

	@When("I click on the Create User button")
	public void i_click_on_the_Create_User_button() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/a"));
		searchButton.click();
	}

	@Then("I should be directed to the register page")
	public void i_should_be_directed_to_the_register_page() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"form\"]/div[1]/div/p")));
		String result = driver.findElement(By.xpath("//*[@id=\"form\"]/div[1]/div/p")).getText();
		assertTrue(result.equals("Register"));
	}
	
	//Scenario 4
	@Given("I am on the main page")
	public void i_am_on_the_main_page() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I click the Logout link")
	public void i_click_the_Logout_link() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"logout_button\"]"));
		searchButton.click();
	}

	@Then("I should be redirected to the login page")
	public void i_should_be_redirected_to_the_login_page() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#form > div:nth-child(1) > div > p")));
		String result = driver.findElement(By.cssSelector("#form > div:nth-child(1) > div > p")).getText();
		assertTrue(result.equals("Login"));
	}
	
	//Scenario 5
	@Given("I am on the register page10")
	public void i_am_on_the_reg_page() {
		driver.get(REG_URL);
	}

	@When("I click the Cancel link")
	public void i_click_the_cancel_link() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[6]/div/a"));
		searchButton.click();
	}

	@Then("I should be redirected to the login page10")
	public void i_should_be_redirected_to_the_login_page10() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#form > div:nth-child(1) > div > p")));
		String result = driver.findElement(By.cssSelector("#form > div:nth-child(1) > div > p")).getText();
		assertTrue(result.equals("Login"));
	}
	
	//Scenario 6
	@Given("I am on the login page21")
	public void i_am_on_the_login_page21() {
		driver.get(ROOT_URL);
	}

	@When("I access the Main portfolio page without logging in")
	public void i_access_the_Main_portfolio_page_without_logging_in() {
		driver.get("http://localhost:8080/MainPage.jsp");
	}

	@Then("I should be redirected to the login page21")
	public void i_should_be_redirected_to_the_login_page21() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#form > div:nth-child(1) > div > p")));
		String result = driver.findElement(By.cssSelector("#form > div:nth-child(1) > div > p")).getText();
		assertTrue(result.equals("Login"));
	}
	
	
	/**********REGISTER FEATURE TESTS***************/

	//SCE 1
	@Given("I am on the register page0")
	public void i_am_on_the_register_page0() {
		driver.get(REG_URL);
	    
	}

	@When("I enter in a username that is already taken")
	public void i_enter_in_a_username_that_is_already_taken() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		
		queryBox.sendKeys("cucum310");
	}

	@When("enter in a password0")
	public void enter_in_a_password0() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing");
	}

	@When("enter in the same password")
	public void enter_in_the_same_password() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/input"));
		queryBox.sendKeys("testing");
	}

	@When("click the Create User button0")
	public void click_the_Create_User_button0() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"registerButton\"]"));
		searchButton.click();
	}

	@Then("I should see an error message in regards to the username already taken")
	public void i_should_see_an_error_message_in_regards_to_the_username_already_taken() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"formerror\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"formerror\"]")).getText();
		assertTrue(result.equals("Username already exist!"));
	}

	//SCE 2
	@Given("I am on the register page2")
	public void i_am_on_the_register_page2() {
		driver.get(REG_URL);
	}

	@When("I enter in a correct username")
	public void i_enter_in_a_correct_username() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("CucumberTest");
	}

	@When("enter in a password in the first input field")
	public void enter_in_a_password_in_the_first_input_field() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("cs310testing");
	}

	@When("enter in a different password in the second input field")
	public void enter_in_a_different_password_in_the_second_input_field() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/input"));
		queryBox.sendKeys("cs310");
	}

	@When("click the Create User button2")
	public void click_the_Create_User_button2() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"registerButton\"]"));
		searchButton.click();
	}

	@Then("I should see an error message in regards to the passwords not matching")
	public void i_should_see_an_error_message_in_regards_to_the_passwords_not_matching() {
		String result = driver.findElement(By.xpath("//*[@id=\"formerror\"]")).getText();
		assertTrue(result.equals("Passwords do not match."));
	}
	//SCE 3
	@Given("I am on the register page3")
	public void i_am_on_the_register_page3() {
		driver.get(REG_URL);
	}

	@When("I enter in a correct username3")
	public void i_enter_in_a_correct_username3() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("CucumberTest1");
	}

	@When("enter in a password that meets all requirement in the first input field")
	public void enter_in_a_password_that_meets_all_requirement_in_the_first_input_field() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("cs310testing1");
	}

	@When("enter in the same password as in the first in the second input field")
	public void enter_in_the_same_password_as_in_the_first_in_the_second_input_field() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/input"));
		queryBox.sendKeys("cs310testing1");
	}

	@When("click the Create User button3")
	public void click_the_Create_User_button3() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"registerButton\"]"));
		searchButton.click();
	}

	@Then("I should be successfully registered")
	public void i_should_be_successfully_registered() {
		String result = driver.findElement(By.xpath("//*[@id=\"form\"]/div[1]/div/p")).getText();
		assertTrue(result.equals("Login"));
	}

	//SCE 4
	@Given("I am on the register page4")
	public void i_am_on_the_register_page4() {
		driver.get(REG_URL);
	}

	@When("I don’t enter anything in username input field")
	public void i_don_t_enter_anything_in_username_input_field() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("");
	}

	@When("I don’t enter anything in both password input fields")
	public void i_don_t_enter_anything_in_both_password_input_fields() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/input"));
		queryBox.sendKeys("");
	}

	@When("click the Create User button4")
	public void click_the_Create_User_button4() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"registerButton\"]"));
		searchButton.click();
	}

	@Then("I should be given an error in regards to nothing being entered")
	public void i_should_be_given_an_error_in_regards_to_nothing_being_entered() {
		String result = driver.findElement(By.xpath("//*[@id=\"formerror\"]")).getText();
		assertTrue(result.equals("Please do not leave blank."));
	}

	//SCE 5
	@Given("I am on the register page5")
	public void i_am_on_the_register_page5() {
		driver.get(REG_URL);
	}

	@When("I enter in a user name in the input field5")
	public void i_enter_in_a_user_name_in_the_input_field5() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("hello");
	}

	@When("I enter in a password in the first input field")
	public void i_enter_in_a_password_in_the_first_input_field() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("hello123");
		
	}

	@When("not enter anything in the 2nd password input field")
	public void not_enter_anything_in_the_2nd_password_input_field() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/input"));
		queryBox.sendKeys("");
	}

	@When("click the Create User button5")
	public void click_the_Create_User_button5() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"registerButton\"]"));
		searchButton.click();
	}

	@Then("I should be given an error in regards to nothing being entered in the second input field")
	public void i_should_be_given_an_error_in_regards_to_nothing_being_entered_in_the_second_input_field() {
		String result = driver.findElement(By.xpath("//*[@id=\"formerror\"]")).getText();
		assertTrue(result.equals("Please enter password."));
	}

	//SCE 6
	@Given("I am on the register page6")
	public void i_am_on_the_register_page6() {
		driver.get(REG_URL);
	}

	@When("I enter in a user name in the input field6")
	public void i_enter_in_a_user_name_in_the_input_field6() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("hello");
	}

	@When("I enter in a password in the 2nd password input field")
	public void i_enter_in_a_password_in_the_2nd_password_input_field() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/input"));
		queryBox.sendKeys("hello123");
	}

	@When("not enter anything in the 1st password input field")
	public void not_enter_anything_in_the_1st_password_input_field() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("");
	}

	@When("click the Create User button6")
	public void click_the_Create_User_button6() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"registerButton\"]"));
		searchButton.click();
	}

	@Then("I should be given an error in regards to nothing being entered in the first input field")
	public void i_should_be_given_an_error_in_regards_to_nothing_being_entered_in_the_first_input_field() {
		String result = driver.findElement(By.xpath("//*[@id=\"formerror\"]")).getText();
		assertTrue(result.equals("Please enter password."));
	}

	//SCE 7
	@Given("I am on the register page7")
	public void i_am_on_the_register_page7() {
		driver.get(REG_URL);
	}

	@When("I enter in a user name in the input field")
	public void i_enter_in_a_user_name_in_the_input_field() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("hello");
	}

	@When("I don’t enter in a password in the 1st password input field")
	public void i_don_t_enter_in_a_password_in_the_1st_password_input_field() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("");
	}

	@When("don’t enter anything in the 2nd password input field")
	public void don_t_enter_anything_in_the_2nd_password_input_field() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/input"));
		queryBox.sendKeys("");
	}

	@When("click the Create User button7")
	public void click_the_Create_User_button7() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"registerButton\"]"));
		searchButton.click();
	}

	@Then("I should be given an error in regards to a password not being entered")
	public void i_should_be_given_an_error_in_regards_to_a_password_not_being_entered() {
		String result = driver.findElement(By.xpath("//*[@id=\"formerror\"]")).getText();
		assertTrue(result.equals("Please enter password."));
	}

	//SCE 8
	@Given("I am on the register page8")
	public void i_am_on_the_register_page8() {
		driver.get(REG_URL);
	}

	@When("I don’t enter in a user name in the input field")
	public void i_don_t_enter_in_a_user_name_in_the_input_field() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("");
	}

	@When("I enter in a password in the 1st password input field")
	public void i_enter_in_a_password_in_the_1st_password_input_field() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("heyhey");
	}

	@When("enter the same password in the 2nd password input field")
	public void enter_the_same_password_in_the_2nd_password_input_field() {
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/input"));
		queryBox.sendKeys("heyhey");
	}

	@When("click the Create User button")
	public void click_the_Create_User_button() {
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"registerButton\"]"));
		searchButton.click();
	}

	@Then("I should be given an error in regards to a user name not being entered")
	public void i_should_be_given_an_error_in_regards_to_a_user_name_not_being_entered() {
		String result = driver.findElement(By.xpath("//*[@id=\"formerror\"]")).getText();
		assertTrue(result.equals("Please enter a username."));
	}
	@Given("I am on the login page6")
	public void i_am_on_the_login_page6() {
		driver.get(ROOT_URL);
	}

	@When("I fail to login {int} times within a minute")
	public void i_fail_to_login_times_within_a_minute(Integer int1) {
		System.out.println("HEYYYYYYYYHYYYYY WHAT IS INT!!!!!!!!!!!!!!!!: " + int1);
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
		System.out.println("HEYYYYYYYYHYYYYY WHAT IS INT!!!!!!!!!!!!!!!!: " + 2);
		
//		String result = driver.findElement(By.xpath("//*[@id=\"formerror\"]")).getText();
//		System.out.println("print error!!!!!!!!: "+ result);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"userLog\"]")));
//		queryBox = driver.findElement(By.xpath("//*[@id=\"userLog\"]"));
//		queryBox.sendKeys("cucum310");
//		queryBox = driver.findElement(By.xpath("//*[@id=\"passLog\"]"));
//		queryBox.sendKeys("testing");
//		searchButton = driver.findElement(By.xpath("//*[@id=\"signInBut\"]"));
//		searchButton.click();
//		System.out.println("HEYYYYYYYYHYYYYY WHAT IS INT!!!!!!!!!!!!!!!!: " + 3.0);
		
		
	    
	}
	@Then("the user should be locked from logging in for a min")
	public void the_user_should_be_locked_from_logging_in_for_a_min() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"formerror\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"formerror\"]")).getText();
		assertTrue(result.equals("You are locked for a minute"));
		WebDriverWait wait = new WebDriverWait (driver, 62);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"form\"]/div[3]/input")));
	}

	@Given("I am on the main page22")
	public void i_am_on_the_main_page22() {
		driver.get(ROOT_URL);
		WebElement queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I dont move my mouse or press any keys")
	public void i_dont_move_my_mouse_or_press_any_keys() {
		WebDriverWait wait = new WebDriverWait (driver, 150);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"form\"]/div[1]/div/p")));
	}

	@Then("I should be redirected to the login page22")
	public void i_should_be_redirected_to_the_login_page22() {
		String result = driver.findElement(By.xpath("//*[@id=\"form\"]/div[1]/div/p")).getText();
		assertTrue(result.equals("Login"));  
	}

	@After()
	public void after() {
		driver.quit();
	}
	
}

