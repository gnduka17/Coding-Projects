package cucumber;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefMobile {
	private static final String ROOT_URL = "http://localhost:8080/";
	private static final String REG_URL = "http://localhost:8080/Register.jsp";
	private static final String MAIN_URL = "http://localhost:8080/MainPage.jsp";
//	private final WebDriver driver = new ChromeDriver();
	private Map<String, String> mobileEmulation = new HashMap<>();
	private WebDriver mobileDriver = new ChromeDriver();
	private WebDriverWait wait = new WebDriverWait (mobileDriver, 5);
	Point box;
	Dimension boxDim;
	Dimension windowsize;
	Point boxban;
	Dimension boxbanDim;
	Point boxban1;
	Dimension boxbanDim1;
	
	Point box2;
	Dimension boxDim2;
	
	Point boxban2;
	Dimension boxbanDim2;
	
	
	/**************MOBILE TEST****************/
	@Before()
	public void mobileTest() {
		mobileEmulation.put("deviceName", "iPhone 6/7/8");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
		mobileDriver = new ChromeDriver(chromeOptions);
		mobileDriver.manage().window().setSize(new Dimension(750, 1334));
	}
	
	@Given("I am on the mobile login page80")
	public void i_am_on_the_mobile_login_page80() {
		mobileDriver.get(ROOT_URL);
	}

	@When("I get the dimensions of the login Box and of the main Page80")
	public void i_get_the_dimensions_of_the_login_Box_and_of_the_main_Page80() {
		box = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ box.getX()+ " yvalue getY: "+ box.getY() + " xval: "+ box.x+ " "+ box.y);
		boxDim = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxDim.getHeight()+ " getwidth: "+ boxDim.getWidth());
				
		windowsize = mobileDriver.manage().window().getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR the main page IS: "+ windowsize.getHeight()+ " width: "+ windowsize.getWidth());
	
			
		
	}
	@Then("the login box isnt spilling over  with the main page80")
	public void the_login_box_isnt_spilling_over_with_the_main_page80() {
		double x = (box.x/2)+boxDim.getWidth();
	   assertTrue(x<windowsize.getWidth()&&box.getX()>0);
	}
	
	@Given("I am on the mobile login page800")
	public void i_am_on_the_mobile_login_page800() {
		mobileDriver.get(ROOT_URL);
	}

	@When("I get the dimensions of the login Box and of the banner800")
	public void I_get_the_dimensions_of_the_login_Box_and_of_the_banner800() {
		boxban = mobileDriver.findElement(By.xpath("/html/body/nav")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban.getX()+ " yvalue getY: "+ boxban.getY() + " xval: "+ boxban.x+ " "+ boxban.y);
		boxbanDim = mobileDriver.findElement(By.xpath("/html/body/nav")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim.getHeight()+ " getwidth: "+ boxbanDim.getWidth());
		
		box = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ box.getX()+ " yvalue getY: "+ box.getY() + " xval: "+ box.x+ " "+ box.y);
		boxDim = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxDim.getHeight()+ " getwidth: "+ boxDim.getWidth());
			
	}
	@Then("the login box and banner shouldn't overlap one another800")
	public void the_login_box_isnt_overlappingwithbanner800() {
	    assertTrue(boxbanDim.getHeight()<box.getY());
	}

	@Given("I am on the main portfolio page98")
	public void i_am_on_the_main_portfolio_page98() {
		mobileDriver.get(ROOT_URL);
		WebElement queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I click add to portfolio button98")
	public void i_click_add_to_portfolio_button98() {
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addToPortfolio")));
		WebElement searchButton = mobileDriver.findElement(By.id("addToPortfolio"));
		searchButton.click();
	}
	@When("get the x and y locations of the buttons and inputs in the popup98")
	public void get_the_x_and_y_locations_of_the_buttons_and_inputs_in_the_popup98() {
		
		
		//*[@id="confirmAddStock"]/div/div/div[2] surround
		//*[@id="confirmAddStock"]/div/div/div[3] buttons
		
		//*[@id="addStockForm"]/div[4] last input
		
		
		//*[@id="confirmAddStock"]/div/div  whole thing surround
		
		boxban = mobileDriver.findElement(By.xpath("//*[@id=\"confirmAddStock\"]/div/div/div[2]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban.getX()+ " yvalue getY: "+ boxban.getY() + " xval: "+ boxban.x+ " "+ boxban.y);
		boxbanDim = mobileDriver.findElement(By.xpath("//*[@id=\"confirmAddStock\"]/div/div/div[2]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim.getHeight()+ " getwidth: "+ boxbanDim.getWidth());
		
		box2 = mobileDriver.findElement(By.xpath("//*[@id=\"confirmAddStock\"]/div/div/div[3]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ box2.getX()+ " yvalue getY: "+ box2.getY() + " xval: "+ box2.x+ " "+ box2.y);
		boxDim2 = mobileDriver.findElement(By.xpath("//*[@id=\"confirmAddStock\"]/div/div/div[3]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxDim2.getHeight()+ " getwidth: "+ boxDim2.getWidth());
			
	}
	@Then("the popup inputs and buttons dont overlap each other and with the box98")
	public void the_popup_inputs_and_buttons_dont_overlap_each_other_and_with_the_box98() {
		
		double inputH = boxban.getY() + boxbanDim.getHeight();
		double butH = box2.getY();
	
		
		box = mobileDriver.findElement(By.xpath("//*[@id=\"confirmAddStock\"]/div/div")).getLocation();
		boxDim = mobileDriver.findElement(By.xpath("//*[@id=\"confirmAddStock\"]/div/div")).getSize();
		
	System.out.println("a: "+ inputH+"a: "+butH+ "a: "+ (boxban.x+boxbanDim.getWidth()) + "a: "+(boxDim.getWidth()+box.getX()) + "a: "+ (box2.x+boxDim2.getWidth()) + "a: "+ (boxDim.getWidth()+box.getX()));
		
		assertTrue(inputH<=butH && (boxban.x+boxbanDim.getWidth())<=(boxDim.getWidth()+box.getX()) && (box2.x+boxDim2.getWidth())<=(boxDim.getWidth()+box.getX()) && boxban.getX()>=box.getX() &&box2.getX()>=box.getX());
	
	}
	//*[@id="addStockForm"]
	//*[@id="confirmAddStock"]/div/div/div[2]
	//*[@id="confirmAddStock"]/div/div/div[3]
	//*[@id="portfolioButton"]

	@Given("I am on the main portfolio page96")
	public void i_am_on_the_main_portfolio_page96() {
		mobileDriver.get(ROOT_URL);
		WebElement queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}


	@When("I click add to portfolio button96")
	public void i_click_add_to_portfolio_button96() {
		WebElement searchButton = mobileDriver.findElement(By.id("addToPortfolio"));
		searchButton.click();
	}
	@When("get the x and y locations of the popup and main page96")
	public void get_the_x_and_y_locations_of_the_popup_and_main_page96() {
		boxban = mobileDriver.findElement(By.xpath("//*[@id=\"confirmAddStock\"]/div/div")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban.getX()+ " yvalue getY: "+ boxban.getY() + " xval: "+ boxban.x+ " "+ boxban.y);
		boxbanDim = mobileDriver.findElement(By.xpath("//*[@id=\"confirmAddStock\"]/div/div")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim.getHeight()+ " getwidth: "+ boxbanDim.getWidth());

		windowsize = mobileDriver.manage().window().getSize();
		
	}
	@Then("the popup doesnt cut off96")
	public void the_popup_doesnt_cut_off96() {
		double x = boxban.getX() + boxbanDim.getWidth(); 
		System.out.println("a: "+ x+"a: "+windowsize.width+ "a: "+ boxbanDim.getHeight() + "a: "+windowsize.height);

	    assertTrue(x<windowsize.width && boxbanDim.getHeight() < windowsize.height);
	}



	@Given("I am on the main portfolio page95")
	public void i_am_on_the_main_portfolio_page95() {
		mobileDriver.get(ROOT_URL);
		WebElement queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I click add to watchlist button95")
	public void i_click_add_to_watchlist_button95() {
		WebElement searchButton = mobileDriver.findElement(By.id("addToWatchlist"));
		searchButton.click();
	}
	@When("get the x and y locations of the buttons and inputs in the popup95")
	public void get_the_x_and_y_locations_of_the_buttons_and_inputs_in_the_popup95() {
		
		boxban = mobileDriver.findElement(By.xpath("//*[@id=\"confirmAddStock\"]/div/div/div[2]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban.getX()+ " yvalue getY: "+ boxban.getY() + " xval: "+ boxban.x+ " "+ boxban.y);
		boxbanDim = mobileDriver.findElement(By.xpath("//*[@id=\"confirmAddStock\"]/div/div/div[2]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim.getHeight()+ " getwidth: "+ boxbanDim.getWidth());
		
		box2 = mobileDriver.findElement(By.xpath("//*[@id=\"confirmAddStock\"]/div/div/div[3]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ box2.getX()+ " yvalue getY: "+ box2.getY() + " xval: "+ box2.x+ " "+ box2.y);
		boxDim2 = mobileDriver.findElement(By.xpath("//*[@id=\"confirmAddStock\"]/div/div/div[3]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxDim2.getHeight()+ " getwidth: "+ boxDim2.getWidth());
}
	@Then("the popup inputs and buttons dont overlap each other and with the box95")
	public void the_popup_inputs_and_buttons_dont_overlap_each_other_and_with_the_box95() {
		double inputH = boxban.getY() + boxbanDim.getHeight();
		double butH = box2.getY();

		box = mobileDriver.findElement(By.xpath("//*[@id=\"confirmAddStock\"]/div/div")).getLocation();
		boxDim = mobileDriver.findElement(By.xpath("//*[@id=\"confirmAddStock\"]/div/div")).getSize();
		
		System.out.println("a: "+ inputH+"a: "+butH+ "a: "+ (boxban.x+boxbanDim.getWidth()) + "a: "+(boxDim.getWidth()+box.getX()) + "a: "+ (box2.x+boxDim2.getWidth()) + "a: "+ (boxDim.getWidth()+box.getX()));
		
		assertTrue(inputH<=butH && (boxban.x+boxbanDim.getWidth())<=(boxDim.getWidth()+box.getX()) && (box2.x+boxDim2.getWidth())<=(boxDim.getWidth()+box.getX()) && boxban.getX()>=box.getX() &&box2.getX()>=box.getX());
	
	}


	@Given("I am on the main portfolio page93")
	public void i_am_on_the_main_portfolio_page93() {
		mobileDriver.get(ROOT_URL);
		WebElement queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I click add to watchlist button93")
	public void i_click_add_to_watchlist_button93() {
		WebElement searchButton = mobileDriver.findElement(By.id("addToWatchlist"));
		searchButton.click();
	}
	@When("get the x and y locations of the popup and main page93")
	public void get_the_x_and_y_locations_of_the_popup_and_main_page93() {
		boxban = mobileDriver.findElement(By.xpath("//*[@id=\"confirmAddStock\"]/div/div")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban.getX()+ " yvalue getY: "+ boxban.getY() + " xval: "+ boxban.x+ " "+ boxban.y);
		boxbanDim = mobileDriver.findElement(By.xpath("//*[@id=\"confirmAddStock\"]/div/div")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim.getHeight()+ " getwidth: "+ boxbanDim.getWidth());

		windowsize = mobileDriver.manage().window().getSize();
	}
	@Then("the popup doesnt cut off93")
	public void the_popup_doesnt_cut_off93() {
		double x = boxban.getX() + boxbanDim.getWidth(); 
		System.out.println("a: "+ x+"a: "+windowsize.width+ "a: "+ boxbanDim.getHeight() + "a: "+windowsize.height);

	    assertTrue(x<windowsize.width && boxbanDim.getHeight() < windowsize.height);
	}


	@Given("I am on the main portfolio page85")
	public void i_am_on_the_main_portfolio_page85() {
		mobileDriver.get(ROOT_URL);
		WebElement queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I get the dimensions of the hamburger menu and of the banner")
	public void i_get_the_dimensions_of_the_hamburger_menu_and_of_the_banner() {
		WebElement searchButton = mobileDriver.findElement(By.xpath("/html/body/nav/button"));
		searchButton.click();
		
		//surround all elemens 
		boxban = mobileDriver.findElement(By.xpath("//*[@id=\"navbarNav\"]/ul")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban.getX()+ " yvalue getY: "+ boxban.getY() + " xval: "+ boxban.x+ " "+ boxban.y+ "  "+ boxban );
		boxbanDim = mobileDriver.findElement(By.xpath("//*[@id=\"navbarNav\"]/ul")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim.getHeight()+ " getwidth: "+ boxbanDim.getWidth()+ "  "+ boxbanDim);
		
		box = mobileDriver.findElement(By.xpath("/html/body/nav/a")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ box.getX()+ " yvalue getY: "+ box.getY() + " xval: "+ box.x+ " "+ box.y+ "  "+ box);
		boxDim = mobileDriver.findElement(By.xpath("/html/body/nav/a")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxDim.getHeight()+ " getwidth: "+ boxDim.getWidth()+ " "+ boxDim);
		
		
		
		
		boxban1 = mobileDriver.findElement(By.xpath("//*[@id=\"navbarNav\"]/ul/li[1]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban1.getX()+ " yvalue getY: "+ boxban1.getY() + " xval: "+ boxban1.x+ " "+ boxban1.y+ "  "+ boxban1 );
		boxbanDim1 = mobileDriver.findElement(By.xpath("//*[@id=\"navbarNav\"]/ul/li[1]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim1.getHeight()+ " getwidth: "+ boxbanDim1.getWidth()+ "  "+ boxbanDim1);
		
		
		boxban2 = mobileDriver.findElement(By.xpath("//*[@id=\"navbarNav\"]/ul/li[2]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban2.getX()+ " yvalue getY: "+ boxban2.getY() + " xval: "+ boxban2.x+ " "+ boxban2.y+ "  "+ boxban2 );
		boxbanDim2 = mobileDriver.findElement(By.xpath("//*[@id=\"navbarNav\"]/ul/li[2]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim2.getHeight()+ " getwidth: "+ boxbanDim2.getWidth()+ "  "+ boxbanDim2);
		
		box2 = mobileDriver.findElement(By.xpath("//*[@id=\"navbarNav\"]/ul/li[3]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ box2.getX()+ " yvalue getY: "+ box2.getY() + " xval: "+ box2.x+ " "+ box2.y+ "  "+ box2);
		boxDim2 = mobileDriver.findElement(By.xpath("//*[@id=\"navbarNav\"]/ul/li[3]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxDim2.getHeight()+ " getwidth: "+ boxDim2.getWidth()+ " "+ boxDim2);
		

	}
	@Then("the banner elements aren't overlapping with each other")
	public void the_banner_elements_aren_t_overlapping_with_each_other() {
	    assertTrue(box.getX()+boxDim.getHeight()<boxban.getY() && boxban1.getY()<boxban2.getY() && boxban1.getY()<box2.getY() && boxban2.getY() < box2.getY());
	}



	@Given("I am on the main portfolio page92")
	public void i_am_on_the_main_portfolio_page92() {
		mobileDriver.get(ROOT_URL);
		WebElement queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I get the x and y locations of the graph92")
	public void i_get_the_x_and_y_locations_of_the_graph92() {
		
		boxban = mobileDriver.findElement(By.xpath("//*[@id=\"graph\"]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban.getX()+ " yvalue getY: "+ boxban.getY() + " xval: "+ boxban.x+ " "+ boxban.y+ "  "+ boxban );
		boxbanDim = mobileDriver.findElement(By.xpath("//*[@id=\"graph\"]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim.getHeight()+ " getwidth: "+ boxbanDim.getWidth()+ "  "+ boxbanDim);
		windowsize = mobileDriver.manage().window().getSize();
		System.out.println("ws width: "+ windowsize.width);

	}
	@Then("the graph doesnt spill past the main page92")
	public void the_graph_doesnt_spill_past_the_main_page92() {
	    assertTrue(boxban.getX()+(boxbanDim.getWidth()/2)<windowsize.getWidth() && boxban.getX()>=0);
	}


	@Given("I am on the mobile login page81")
	public void i_am_on_the_mobile_login_page81() {
		mobileDriver.get(ROOT_URL);
	}


	@When("I get the dimensions of the login inputs and of the Box81")
	public void i_get_the_dimensions_of_the_login_inputs_and_of_the_Box81() {
		boxban = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[3]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban.getX()+ " yvalue getY: "+ boxban.getY() + " xval: "+ boxban.x+ " "+ boxban.y+ "  "+ boxban );
		boxbanDim = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[3]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim.getHeight()+ " getwidth: "+ boxbanDim.getWidth()+ "  "+ boxbanDim);
		
		box = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[4]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ box.getX()+ " yvalue getY: "+ box.getY() + " xval: "+ box.x+ " "+ box.y+ "  "+ box);
		boxDim = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[4]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxDim.getHeight()+ " getwidth: "+ boxDim.getWidth()+ " "+ boxDim);
		
		
		boxban1 = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[1]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban1.getX()+ " yvalue getY: "+ boxban1.getY() + " xval: "+ boxban1.x+ " "+ boxban1.y+ "  "+ boxban1 );
		boxbanDim1 = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[1]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim1.getHeight()+ " getwidth: "+ boxbanDim1.getWidth()+ "  "+ boxbanDim1);
		
		
		
	}
	@Then("the login inputs dont overlap with each other and with the box81")
	public void the_login_inputs_dont_overlap_with_each_other_and_with_the_box81() {
	    assertTrue(boxban.getY()< box.getY() && boxban.getX()+boxbanDim.getWidth()<=(boxbanDim1.getWidth()+boxban1.getX()));
	}


	@Given("I am on the main portfolio page87")
	public void i_am_on_the_main_portfolio_page87() {
		mobileDriver.get(ROOT_URL);
		WebElement queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}


	@When("I get the locations of the buttons87")
	public void i_get_the_locations_of_the_buttons87() {
		
		//TOP BUTTONS
		boxban = mobileDriver.findElement(By.xpath("//*[@id=\"addRemoveDays\"]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban.getX()+ " yvalue getY: "+ boxban.getY() + " xval: "+ boxban.x+ " "+ boxban.y+ "  "+ boxban );
		boxbanDim = mobileDriver.findElement(By.xpath("//*[@id=\"addRemoveDays\"]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim.getHeight()+ " getwidth: "+ boxbanDim.getWidth()+ "  "+ boxbanDim);
		
		//LOWER BUTTONS
		box = mobileDriver.findElement(By.xpath("//*[@id=\"addButtons\"]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ box.getX()+ " yvalue getY: "+ box.getY() + " xval: "+ box.x+ " "+ box.y+ "  "+ box);
		boxDim = mobileDriver.findElement(By.xpath("//*[@id=\"addButtons\"]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxDim.getHeight()+ " getwidth: "+ boxDim.getWidth()+ " "+ boxDim);
		
	}
	@Then("the buttons on the portfolio pages do not overlap87")
	public void the_buttons_on_the_portfolio_pages_do_not_overlap87() {
	    assertTrue(boxban.getY()+boxbanDim.getHeight() < box.getY());
	}


	@Given("I am on the main portfolio page89")
	public void i_am_on_the_main_portfolio_page89() {
		mobileDriver.get(ROOT_URL);
		WebElement queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}


	@When("I get the locations and dimensions of the portfolio and watchlist tables89")
	public void i_get_the_locations_and_dimensions_of_the_portfolio_and_watchlist_tables89() {
		boxban = mobileDriver.findElement(By.id("portfolioTable")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban.getX()+ " yvalue getY: "+ boxban.getY() + " xval: "+ boxban.x+ " "+ boxban.y+ "  "+ boxban );
		boxbanDim = mobileDriver.findElement(By.id("portfolioTable")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim.getHeight()+ " getwidth: "+ boxbanDim.getWidth()+ "  "+ boxbanDim);
		
		box = mobileDriver.findElement(By.id("watchListTable")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ box.getX()+ " yvalue getY: "+ box.getY() + " xval: "+ box.x+ " "+ box.y+ "  "+ box);
		boxDim = mobileDriver.findElement(By.id("watchListTable")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxDim.getHeight()+ " getwidth: "+ boxDim.getWidth()+ " "+ boxDim);
		
	}
	@Then("the two tables do not overlap with one another89")
	public void the_two_tables_do_not_overlap_with_one_another89() {
		System.out.println("checking stuff "+boxban.getY()+boxbanDim.getHeight()+ "  " + box.getY());

	   assertTrue(box.getY()+boxDim.getHeight()<boxban.getY());
	}


	@Given("I am on the main portfolio page90")
	public void i_am_on_the_main_portfolio_page90() {
		mobileDriver.get(ROOT_URL);
		WebElement queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I get the x and y locations of the labels in portfolio90")
	public void i_get_the_x_and_y_locations_of_the_labels_in_portfolio90() {
		boxban = mobileDriver.findElement(By.xpath("//*[@id=\"portfolioTable\"]/thead/tr/th[1]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban.getX()+ " yvalue getY: "+ boxban.getY() + " xval: "+ boxban.x+ " "+ boxban.y+ "  "+ boxban );
		boxbanDim = mobileDriver.findElement(By.xpath("//*[@id=\"portfolioTable\"]/thead/tr/th[1]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim.getHeight()+ " getwidth: "+ boxbanDim.getWidth()+ "  "+ boxbanDim);
		
		box = mobileDriver.findElement(By.xpath("//*[@id=\"portfolioTable\"]/thead/tr/th[2]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ box.getX()+ " yvalue getY: "+ box.getY() + " xval: "+ box.x+ " "+ box.y+ "  "+ box);
		boxDim = mobileDriver.findElement(By.xpath("//*[@id=\"portfolioTable\"]/thead/tr/th[2]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxDim.getHeight()+ " getwidth: "+ boxDim.getWidth()+ " "+ boxDim);
		
		boxban1 = mobileDriver.findElement(By.xpath("//*[@id=\"portfolioTable\"]/thead/tr/th[3]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban1.getX()+ " yvalue getY: "+ boxban1.getY() + " xval: "+ boxban1.x+ " "+ boxban1.y+ "  "+ boxban1 );
		boxbanDim1 = mobileDriver.findElement(By.xpath("//*[@id=\"portfolioTable\"]/thead/tr/th[3]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim1.getHeight()+ " getwidth: "+ boxbanDim1.getWidth()+ "  "+ boxbanDim1);
		
		boxban2 = mobileDriver.findElement(By.xpath("//*[@id=\"portfolioTable\"]/thead/tr/th[4]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban2.getX()+ " yvalue getY: "+ boxban2.getY() + " xval: "+ boxban2.x+ " "+ boxban2.y+ "  "+ boxban2 );
		boxbanDim2 = mobileDriver.findElement(By.xpath("//*[@id=\"portfolioTable\"]/thead/tr/th[4]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim2.getHeight()+ " getwidth: "+ boxbanDim2.getWidth()+ "  "+ boxbanDim2);
		
		box2 = mobileDriver.findElement(By.xpath("//*[@id=\"portfolioTable\"]/thead/tr/th[5]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ box2.getX()+ " yvalue getY: "+ box2.getY() + " xval: "+ box2.x+ " "+ box2.y+ "  "+ box2);
		boxDim2 = mobileDriver.findElement(By.xpath("//*[@id=\"portfolioTable\"]/thead/tr/th[5]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxDim2.getHeight()+ " getwidth: "+ boxDim2.getWidth()+ " "+ boxDim2);
		
	}
	@Then("the column labels do not overlap90")
	public void the_column_labels_do_not_overlap90() {
		System.out.println("ftssssssssssssssssssssssssssssssssssssssssss stuff ");

		assertTrue(boxban.getX()+boxbanDim.getWidth()<=box.getX() && box.getX()+boxDim.getWidth()<=boxban1.getX() && boxban1.getX()+boxbanDim1.getWidth()<=boxban2.getX() && boxban2.getX()+ boxbanDim2.getWidth()<=box2.getX());
	}
	
	

	@Given("I am on the mobile register page82")
	public void i_am_on_the_mobile_register_page82() {
		mobileDriver.get(REG_URL);
	}

	@When("I get the dimensions of the register Box and of the main Page82")
	public void i_get_the_dimensions_of_the_register_Box_and_of_the_main_Page82() {
		
		box = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ box.getX()+ " yvalue getY: "+ box.getY() + " xval: "+ box.x+ " "+ box.y);
		boxDim = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxDim.getHeight()+ " getwidth: "+ boxDim.getWidth());
				
		windowsize = mobileDriver.manage().window().getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR the main page IS: "+ windowsize.getHeight()+ " width: "+ windowsize.getWidth());
	
			
		
	}
	
	@Then("the register box isnt spilling over with the main page82")
	public void the_register_box_isnt_spilling_over_with_the_main_page82() {
		double x = (box.x/2)+boxDim.getWidth();
		   assertTrue(x<windowsize.getWidth()&&box.getX()>0);
	}


	@Given("I am on the mobile register page83")
	public void i_am_on_the_mobile_register_page83() {
		mobileDriver.get(REG_URL);
	}

	@When("I get the dimensions of the register inputs and of the Box83")
	public void i_get_the_dimensions_of_the_register_inputs_and_of_the_Box83() {
		
		
		boxban = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[3]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban.getX()+ " yvalue getY: "+ boxban.getY() + " xval: "+ boxban.x+ " "+ boxban.y+ "  "+ boxban );
		boxbanDim = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[3]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim.getHeight()+ " getwidth: "+ boxbanDim.getWidth()+ "  "+ boxbanDim);
		
		box = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[4]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ box.getX()+ " yvalue getY: "+ box.getY() + " xval: "+ box.x+ " "+ box.y+ "  "+ box);
		boxDim = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[4]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxDim.getHeight()+ " getwidth: "+ boxDim.getWidth()+ " "+ boxDim);
		
		
		boxban1 = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[1]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban1.getX()+ " yvalue getY: "+ boxban1.getY() + " xval: "+ boxban1.x+ " "+ boxban1.y+ "  "+ boxban1 );
		boxbanDim1 = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[1]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim1.getHeight()+ " getwidth: "+ boxbanDim1.getWidth()+ "  "+ boxbanDim1);
		
		
	}

	
	@Then("the register inputs dont overlap with each other and with the box83")
	public void the_register_inputs_dont_overlap_with_each_other_and_with_the_box83() {
		 assertTrue(boxban.getY()< box.getY() && boxban.getX()+boxbanDim.getWidth()<=(boxbanDim1.getWidth()+boxban1.getX()));
	}

	

	@Given("I am on the main portfolio page91")
	public void i_am_on_the_main_portfolio_page91() {
		
		mobileDriver.get(ROOT_URL);
		WebElement queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I get the x and y locations of the labels in watchlist91")
	public void i_get_the_x_and_y_locations_of_the_labels_in_watchlist91() {
		boxban = mobileDriver.findElement(By.xpath("//*[@id=\"watchListTable\"]/thead/tr/th[1]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban.getX()+ " yvalue getY: "+ boxban.getY() + " xval: "+ boxban.x+ " "+ boxban.y+ "  "+ boxban );
		boxbanDim = mobileDriver.findElement(By.xpath("//*[@id=\"watchListTable\"]/thead/tr/th[1]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim.getHeight()+ " getwidth: "+ boxbanDim.getWidth()+ "  "+ boxbanDim);
		
		box = mobileDriver.findElement(By.xpath("//*[@id=\"watchListTable\"]/thead/tr/th[2]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ box.getX()+ " yvalue getY: "+ box.getY() + " xval: "+ box.x+ " "+ box.y+ "  "+ box);
		boxDim = mobileDriver.findElement(By.xpath("//*[@id=\"watchListTable\"]/thead/tr/th[2]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxDim.getHeight()+ " getwidth: "+ boxDim.getWidth()+ " "+ boxDim);
		
		boxban1 = mobileDriver.findElement(By.xpath("//*[@id=\"watchListTable\"]/thead/tr/th[3]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban1.getX()+ " yvalue getY: "+ boxban1.getY() + " xval: "+ boxban1.x+ " "+ boxban1.y+ "  "+ boxban1 );
		boxbanDim1 = mobileDriver.findElement(By.xpath("//*[@id=\"watchListTable\"]/thead/tr/th[3]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim1.getHeight()+ " getwidth: "+ boxbanDim1.getWidth()+ "  "+ boxbanDim1);
		
		boxban2 = mobileDriver.findElement(By.xpath("//*[@id=\"watchListTable\"]/thead/tr/th[4]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban2.getX()+ " yvalue getY: "+ boxban2.getY() + " xval: "+ boxban2.x+ " "+ boxban2.y+ "  "+ boxban2 );
		boxbanDim2 = mobileDriver.findElement(By.xpath("//*[@id=\"watchListTable\"]/thead/tr/th[4]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim2.getHeight()+ " getwidth: "+ boxbanDim2.getWidth()+ "  "+ boxbanDim2);
		
		box2 = mobileDriver.findElement(By.xpath("//*[@id=\"watchListTable\"]/thead/tr/th[5]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ box2.getX()+ " yvalue getY: "+ box2.getY() + " xval: "+ box2.x+ " "+ box2.y+ "  "+ box2);
		boxDim2 = mobileDriver.findElement(By.xpath("//*[@id=\"watchListTable\"]/thead/tr/th[5]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxDim2.getHeight()+ " getwidth: "+ boxDim2.getWidth()+ " "+ boxDim2);
		
		
		
	}
	@Then("the column labels do not overlap91")
	public void the_column_labels_do_not_overlap91() {
		assertTrue(boxban.getX()+boxbanDim.getWidth()<=box.getX() && box.getX()+boxDim.getWidth()<=boxban1.getX() && boxban1.getX()+boxbanDim1.getWidth()<=(boxban2.getX()*2) && boxban2.getX()+ boxbanDim2.getWidth()<=box2.getX());

	}


	@Given("I am on the main portfolio page99")
	public void i_am_on_the_main_portfolio_page99() {
		mobileDriver.get(ROOT_URL);
		WebElement queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I get the x and y locations of the first and second fields of the portfolio table99")
	public void i_get_the_x_and_y_locations_of_the_first_and_second_fields_of_the_portfolio_table99() {
		boxban = mobileDriver.findElement(By.xpath("//*[@id=\"portfolioTable\"]/tbody/tr[1]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban.getX()+ " yvalue getY: "+ boxban.getY() + " xval: "+ boxban.x+ " "+ boxban.y+ "  "+ boxban );
		boxbanDim = mobileDriver.findElement(By.xpath("//*[@id=\"portfolioTable\"]/tbody/tr[1]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim.getHeight()+ " getwidth: "+ boxbanDim.getWidth()+ "  "+ boxbanDim);
		
		box = mobileDriver.findElement(By.xpath("//*[@id=\"portfolioTable\"]/tbody/tr[2]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ box.getX()+ " yvalue getY: "+ box.getY() + " xval: "+ box.x+ " "+ box.y+ "  "+ box);
		boxDim = mobileDriver.findElement(By.xpath("//*[@id=\"portfolioTable\"]/tbody/tr[2]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxDim.getHeight()+ " getwidth: "+ boxDim.getWidth()+ " "+ boxDim);
		
	}
	@Then("the fields dont overlap with each other99")
	public void the_fields_dont_overlap_with_each_other99() {
		assertTrue(boxban.getY()+boxbanDim.getHeight()<=box.getY());
	}


	@Given("I am on the main portfolio page100")
	public void i_am_on_the_main_portfolio_page100() {
		mobileDriver.get(ROOT_URL);
		WebElement queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I get the x and y locations of the first and second fields of the watchlist table100")
	public void i_get_the_x_and_y_locations_of_the_first_and_second_fields_of_the_watchlist_table100() {
		boxban = mobileDriver.findElement(By.xpath("//*[@id=\"watchListTable\"]/tbody/tr[1]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban.getX()+ " yvalue getY: "+ boxban.getY() + " xval: "+ boxban.x+ " "+ boxban.y+ "  "+ boxban );
		boxbanDim = mobileDriver.findElement(By.xpath("//*[@id=\"watchListTable\"]/tbody/tr[1]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim.getHeight()+ " getwidth: "+ boxbanDim.getWidth()+ "  "+ boxbanDim);
		
		box = mobileDriver.findElement(By.xpath("//*[@id=\"watchListTable\"]/tbody/tr[2]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ box.getX()+ " yvalue getY: "+ box.getY() + " xval: "+ box.x+ " "+ box.y+ "  "+ box);
		boxDim = mobileDriver.findElement(By.xpath("//*[@id=\"watchListTable\"]/tbody/tr[2]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxDim.getHeight()+ " getwidth: "+ boxDim.getWidth()+ " "+ boxDim);
	}
	@Then("the fields dont overlap with each other100")
	public void the_fields_dont_overlap_with_each_other100() {
		assertTrue(boxban.getY()+boxbanDim.getHeight()<=box.getY());
	}
	
	@Given("I am on the main portfolio page1000")
	public void i_am_on_the_main_portfolio_page1000() {
		mobileDriver.get(ROOT_URL);
		WebElement queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
		queryBox.sendKeys("cucum310");
		queryBox = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
		queryBox.sendKeys("testing123");
		WebElement searchButton = mobileDriver.findElement(By.xpath("//*[@id=\"form\"]/div[5]/div/input"));
		searchButton.click();
	}

	@When("I get the x and y locations of the graph and the second banner1000")
	public void i_get_the_x_and_y_locations_of_the_first_and_second_fields1000() {
		
		boxban = mobileDriver.findElement(By.xpath("//*[@id=\"percentageChange\"]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ boxban.getX()+ " yvalue getY: "+ boxban.getY() + " xval: "+ boxban.x+ " "+ boxban.y+ "  "+ boxban );
		boxbanDim = mobileDriver.findElement(By.xpath("//*[@id=\"percentageChange\"]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxbanDim.getHeight()+ " getwidth: "+ boxbanDim.getWidth()+ "  "+ boxbanDim);
		
		box = mobileDriver.findElement(By.xpath("//*[@id=\"graph\"]")).getLocation();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS FOR POINT box IS: "+ box.getX()+ " yvalue getY: "+ box.getY() + " xval: "+ box.x+ " "+ box.y+ "  "+ box);
		boxDim = mobileDriver.findElement(By.xpath("//*[@id=\"graph\"]")).getSize();
		System.out.println("THE DIMENSIONNSSSSSSSSSSSS height FOR box IS: "+ boxDim.getHeight()+ " getwidth: "+ boxDim.getWidth()+ " "+ boxDim);
	}
	@Then("the fields dont overlap with each other1000")
	public void the_fields_dont_overlap_with_each_other1000() {
		assertTrue(boxban.getY()+boxbanDim.getHeight()<=box.getY());
	}



	@After()
	public void after() {
//		driver.quit();
		mobileDriver.quit();
	}

}
