package testCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import dataproviders.ExcelDataProvider;
import driverfactory.DriverManager;
import pages.Background;
import pages.LoginPage;
import utilities.enumClass.Module;
import utilities.enumClass.PageLinks;


public class LoginTest extends Hooks{
	private LoginPage loginpage;
	private Background background;
	private static final Logger logger = LogManager.getLogger(LoginTest.class);
	
	@BeforeMethod
	public void setUp() {
		WebDriver driver = null;
		try {
			driver = DriverManager.getDriver();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    background = new Background(driver);
	    loginpage = new LoginPage(driver);
	    background.launchUrl();
	    background.clickGetStarted();

	}
	
	@Test(priority = 1)
	 void testUserClicksSignInBtn() {
		loginpage.clickLoginLink();
	}

	@Test(priority = 2)
	public void testVerifySignInLink() {
		loginpage.clickLoginLink();
		String actualTitle = loginpage. getSigninLinkText();
		String expectedTitle = PageLinks.SIGNIN_LOGIN.getLinkText();
		logger.info("Verifying that the user lands on the login page. expectedTitle:" + expectedTitle + ", actualTitle: " + actualTitle);
		Assert.assertNotNull(actualTitle, "Title was null - possible page load failure?");
		Assert.assertTrue(actualTitle.contains(expectedTitle), "Failure message");
	}
	
	@Test(priority = 3, dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void testLoginWithValidData(Map<String, String> data) {
	    logger.info("Running test with data: " + data);
	    loginpage.clickLoginLink();
	    List<Map<String, String>> users = new ArrayList<>();
	    users.add(data);
	    loginpage.validateLogin(users);
	}
	
	@Test(priority = 4)
	public void testUserLogout() {
		background.userLoggedin();
		loginpage.logout();
		String expectedMessage = PageLinks.LOGGEDOUT_LOGIN.getLinkText();
		String actualMessage = loginpage.getLogoutMessage(); 
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Failure message");
	
	}
	
	@Test(priority = 5, dataProvider = "excelAllData", dataProviderClass = ExcelDataProvider.class)
	public void testLoginWithInvalidData(Map<String, String> data) {
	    logger.info("Running test with data: " + data);
	    loginpage.clickLoginLink();
	    List<Map<String, String>> users = new ArrayList<>();
	    users.add(data);
	    loginpage.validateLogin(users);
	}
	
	@Test(priority = 6)
	 void testNumpyNinjaLabel() {
		//String expectedLabel = "NumpyNinja";
		String expectedlabel= PageLinks.NUMPYNINJA_LOGIN.getLinkText();
		String actualLabel = loginpage.getNumpyNinjaLinkText();
        Assert.assertEquals(actualLabel, expectedlabel, "Label mismatch!");
	}
	
	@Test(priority = 7)
	 void testRegisterLink() {
		//String expectedLabel = "Register";
		String expectedlabel= PageLinks.REGISTER_LOGIN.getLinkText();
		String actualLabel = loginpage.getRegisterLinkText();
        Assert.assertEquals(actualLabel, expectedlabel, "Label mismatch!");
	}
	
	@Test(priority = 8)
	 void testDataStructuresLabel() {
		//String expectedLabel = "Data Structures";
	  String expectedLabel =PageLinks.DATASTRUCTURES_LOGIN.getLinkText();
	    String actualLabel = loginpage.getDataStructureDropdownText();
        Assert.assertEquals(actualLabel, expectedLabel, "Label mismatch!");
	}
	
	@Test(priority = 9)
	public void testLoginLinks() {
		//stackpage.clickStackGetStarted();
		//loginpage.clickImplementationLink();
		List<String> expectedLinks = PageLinks.getLinksForModules(Module.LOGIN);
		List<String> actualLinks = loginpage.getAllLinkTexts();
		logger.info("LoginTest::actualLinks: " + actualLinks.toString());
		List<String> missing = loginpage.verifyAllExpectedLinksArePresent(expectedLinks, actualLinks);
		Assert.assertTrue(missing.isEmpty(),"These expected links were missing: " + missing + "\nActual list: " + actualLinks);
	}
}
