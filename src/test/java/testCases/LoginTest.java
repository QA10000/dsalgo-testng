package testCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import dataproviders.ExcelDataProvider;
import pages.Background;
import pages.LoginPage;


public class LoginTest extends Hooks{
	private LoginPage loginpage;
	private Background background;
	private static final Logger logger = LogManager.getLogger(LoginTest.class);
	
	@BeforeMethod
	public void setUp() {
	    driver = Hooks.getDriver();
	    background = new Background(driver);
	    loginpage = new LoginPage(driver);
	    background.launchUrl();
	    background.ClickGetStarted();
	}
	
	@Test(priority = 1)
	 void testUserClicksSignInBtn() {
		loginpage.clickLoginLink();
	}

	@Test(priority = 2)
	public void testVerifySignInLink() {
		loginpage.clickLoginLink();
		String actualTitle = loginpage. getSigninLinkText();
		String expectedTitle = "Sign in";
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
		String expectedMessage = "Logged out successfully";
		String actualMessage = loginpage.getLogoutMessage(); 
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Failure message");
	//	Assert.assertEquals(actualMessage, expectedMessage, "Success message mismatch!");
		
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
		String expectedLabel = "NumpyNinja";
		String actualLabel = loginpage.getNumpyNinjaLinkText();
        Assert.assertEquals(actualLabel, expectedLabel, "Label mismatch!");
	}
	
	@Test(priority = 7)
	 void testRegisterLink() {
		String expectedLabel = "Register";
		String actualLabel = loginpage.getRegisterLinkText();
        Assert.assertEquals(actualLabel, expectedLabel, "Label mismatch!");
	}
	
	@Test(priority = 8)
	 void testDataStructuresLabel() {
		String expectedLabel = "Data Structures";
		String actualLabel = loginpage.getDataStructureDropdownText();
        Assert.assertEquals(actualLabel, expectedLabel, "Label mismatch!");
	}
}
