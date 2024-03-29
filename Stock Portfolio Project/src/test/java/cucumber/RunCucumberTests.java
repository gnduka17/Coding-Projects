package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * Run all the cucumber tests in the current package.
 */
@RunWith(Cucumber.class)
@CucumberOptions(strict = true, features= {"src/test/resources/cucumber/portfolio.feature:175"})
public class RunCucumberTests {
	@BeforeClass
	public static void setup() {
		WebDriverManager.chromedriver().setup();
	}

}
