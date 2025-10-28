/*package testCases;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import dataproviders.ExcelDataProvider;
import pages.Background;
import pages.RegisterPage;


public class RegistrationTest extends Hooks {
	private RegisterPage registerpage;
	private Background background;
	private static final Logger logger = LogManager.getLogger(RegistrationTest.class);

	
/*@BeforeMethod
public void initPageObjects() {
    driver = Hooks.getDriver();
    background = new Background(driver);
    registerpage = new RegisterPage(driver);
}

@BeforeMethod
public void testUserLandsOnHomePage() {
background.launchUrl();
background.ClickGetStarted();
}*/
	
	/*@BeforeMethod
	public void setUp() {
	    driver = Hooks.getDriver();
	    background = new Background(driver);
	    registerpage = new RegisterPage(driver);
	    background.launchUrl();
	    background.clickGetStarted();
	}

@Test(priority = 1)
 void testUserClicksSignInBtn() {
	registerpage.clickRegisterLink();
}

@Test(priority = 2)
public void testUserlogsIn() {
	registerpage.clickRegisterLink();
	String actualTitle = registerpage.getTitle();
	String expectedTitle = "Registration";
	logger.info("Verifying that the user lands on the Registration page");
	Assert.assertNotNull(actualTitle, "Title was null - possible page load failure?");
	Assert.assertTrue(actualTitle.contains(expectedTitle), "Failure message");
}

@Test(priority = 3, dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
public void testRegisterWithValidData(Map<String, String> data) {
    logger.info("Running test with data: " + data);
    String username = data.get("username");
    String password = data.get("password");
    String confirmpassword = data.get("confirmpassword");
    registerpage.clickRegisterLink();
    registerpage.enterEmptyField(username, password, confirmpassword);
    String actualMessage = registerpage.getAlertMessage();
    String expectedMessage = "New Account Created. You are logged in as " + username;
    logger.info("Actual message after registration: " + actualMessage);
	Assert.assertTrue(actualMessage.contains(expectedMessage));

}
@Test(priority = 3, dataProvider = "excelDataInvalid", dataProviderClass = ExcelDataProvider.class)
public void testResgisterWithInvalidData(Map<String, String> data) {
	 logger.info("Running test with data: " + data);
	    String username = data.get("username");
	    String password = data.get("password");
	    String confirmpassword = data.get("confirmpassword");
	    registerpage.clickRegisterLink();
	    registerpage.enterEmptyField(username, password, confirmpassword);
	    String actualMessage = registerpage.getAlertMessage();
	    String expectedMessage = "password_mismatch:The two password fields didn’t match."; //2025-10-21 17:23:16 INFO  RegistrationTest:80 - Actual message after registration: password_mismatch:The two password fields didn’t match.
	    logger.info("Actual message after registration: " + actualMessage);
		Assert.assertTrue(actualMessage.contains(expectedMessage));
}

@Test(priority = 4, dataProvider = "excelDataEmpty", dataProviderClass = ExcelDataProvider.class)
public void testResgisterWithEmptyData(Map<String, String> data) {
	   logger.info("Running test with data: " + data);
	    String username = data.get("username");
	    String password = data.get("password");
	    String confirmpassword = data.get("confirmpassword");
	    registerpage.clickRegisterLink();
	    registerpage.enterEmptyField(username, password, confirmpassword);
		String actualMessage = registerpage.displayEmptyfieldMsg();
	    String expectedMessage = data.get("errorMessage");
	    logger.info("Actual message after registration: " + actualMessage);
		Assert.assertTrue(actualMessage.contains(expectedMessage));
}*/





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
import pages.Background;
import pages.RegisterPage;
import utilities.CommonUtils;


public class RegistrationTest extends Hooks {
	private RegisterPage registerpage;
	private Background background;
	private static final Logger logger = LogManager.getLogger(RegistrationTest.class);
	
@BeforeMethod
	public void setUp() {
  		WebDriver driver = DriverManager.getDriver();
	    background = new Background(driver);
	    registerpage = new RegisterPage(driver);
	    background.launchUrl();
	    background.clickGetStarted();
	}

@Test(priority = 1)
 void testUserClicksSignInBtn() {
	registerpage.clickRegisterLink();
}

@Test(priority = 2)
public void testUserlogsIn() {
	registerpage.clickRegisterLink();
	String actualTitle = registerpage.getTitle();
	String expectedTitle = "Registration";
	logger.info("Verifying that the user lands on the Registration page");
	Assert.assertNotNull(actualTitle, "Title was null - possible page load failure?");
	Assert.assertTrue(actualTitle.contains(expectedTitle), "Failure message");
}

@Test(priority = 3, dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
public void testRegisterWithValidData(Map<String, String> data) {
    logger.info("Running test with data: " + data);
    String username = data.get("username");
    String password = data.get("password");
    String confirmpassword = data.get("confirmpassword");
    registerpage.clickRegisterLink();
    registerpage.enterEmptyField(username, password, confirmpassword);
    String actualMessage = registerpage.getAlertMessage();
    String expectedMessage = "New Account Created. You are logged in as " + username;
    logger.info("Actual message after registration: " + actualMessage);
	Assert.assertTrue(actualMessage.contains(expectedMessage));

}
@Test(priority = 4, dataProvider = "excelDataInvalid", dataProviderClass = ExcelDataProvider.class)
public void testResgisterWithInvalidData(Map<String, String> data) {
	 logger.info("Running test with data: " + data);
	    String username = data.get("username");
	    String password = data.get("password");
	    String confirmpassword = data.get("confirmpassword");
	    registerpage.clickRegisterLink();
	    registerpage.enterEmptyField(username, password, confirmpassword);
	    String actualMessage = registerpage.getAlertMessage();
	    String expectedMessage = "password_mismatch:The two password fields didn’t match."; 
	    logger.info("Actual message after registration: " + actualMessage);
		Assert.assertTrue(actualMessage.contains(expectedMessage));
}

@Test(priority = 5, dataProvider = "excelDataEmpty", dataProviderClass = ExcelDataProvider.class)
public void testResgisterWithEmptyData(Map<String, String> data) {
	   logger.info("Running test with data: " + data);
	    String username = data.get("username");
	    String password = data.get("password");
	    String confirmpassword = data.get("confirmpassword");
	    registerpage.clickRegisterLink();
	    registerpage.enterEmptyField(username, password, confirmpassword);
		String actualMessage = registerpage.displayEmptyfieldMsg();
	    String expectedMessage = data.get("errorMessage");
	    logger.info("Actual message after registration: " + actualMessage);
		Assert.assertTrue(actualMessage.contains(expectedMessage));
}
}









