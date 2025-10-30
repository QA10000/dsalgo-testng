package testCases;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import dataproviders.ExcelDataProvider;
import driverfactory.DriverManager;
import pages.ArrayPage;
import pages.Background;
import pages.RegisterPage;

public class HomeTest extends Hooks {
	private Background background;
	private ArrayPage arraypage;
	private HomePage homepage;
	private RegisterPage registerpage;
	private static final Logger logger = LogManager.getLogger(RegistrationTest.class);

	@BeforeMethod
	public void setUp() {
		WebDriver driver = null;
		try {
			driver = DriverManager.getDriver();
		} catch (Exception e) {
			e.printStackTrace();
		}
		background = new Background(driver);
		arraypage = new ArrayPage(driver);
		registerpage = new RegisterPage(driver);
		homepage = new HomePage(driver);	
		background.launchUrl();
		background.clickGetStarted();
		// String actualTitle = registerpage.getTitle();
		String actualTitle = homepage.getTitle();
		String expectedTitle = "NumpyNinja";
		logger.info("Verifying that the user lands on the Home page");
		Assert.assertNotNull(actualTitle, "Title was null - possible page load failure?");
		Assert.assertTrue(actualTitle.contains(expectedTitle), "Failure message");
		logger.info("user navigated to home page");
		arraypage.userLoggedin();
	}

	@Test(priority = 1)
	public void TestUserNavigateToHomePage() {
		arraypage.clickStartBtn();
		String actualTitle = homepage.getTitle();
		String expectedTitle = "Home
		logger.info("Verifying that the user lands on the Home page");
		Assert.assertNotNull(actualTitle, "Title was null - possible page load failure?");
		Assert.assertTrue(actualTitle.contains(expectedTitle), "Failure message");
		logger.info("user navigated to home page");
	}
}

	
	
