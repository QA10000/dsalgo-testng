

package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import driverfactory.DriverManager;
import pages.HomePage;
import pages.Background;
import pages.DataStructurePage;
import utilities.PageLinks;

public class DSIntroTest extends Hooks { 
	private Background background;
	private HomePage homepage;
	private DataStructurePage dspage;	
	private static final Logger logger = LogManager.getLogger(DSIntroTest.class);

	@BeforeMethod
	public void setUp() {
		WebDriver driver = null;
		try {
			driver = DriverManager.getDriver();
		} catch (Exception e) {
			e.printStackTrace();
		}
		background = new Background(driver);
		homepage = new HomePage(driver);
		dspage = new DataStructurePage(driver);
		background.launchUrl();
		background.clickGetStarted();
		String actualTitle = homepage.getTitle();
		String expectedTitle = "NumpyNinja";
		logger.info("Verifying that the user lands on the Home page");
		Assert.assertNotNull(actualTitle, "Title was null - possible page load failure?");
		Assert.assertTrue(actualTitle.contains(expectedTitle), "Home page title mismatch");
		logger.info("user navigated to home page");
	}

	@Test(priority = 1)
	public void TestUserNavigateToHomePage() {
		logger.info("user navigated to home page");
	}
	
	@Test(priority = 2)
	public void userClicksGetStartedButtonOnDataStructurePanel() {
		homepage.clickGetStartedButtonHomePageDSIntroModule();
		String expectedMessage = dspage.getErrorMessage();
		String actualMessage = homepage.getnotlogedMessage();
		logger.info("DataStructurePanel: The user should able to see a warning message : "+actualMessage+" || Expected Message : "+expectedMessage);
	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Warning message mismatch!");
	}
	
	@Test(priority = 3)
	public void userSeesDropdownOnTopLeftCorner() {
		String actualLabel = homepage.getDataStructureLblText();
		String expectedLabel = PageLinks.DATASTRUCTURES_COMMON.getLinkText();
		logger.info("DropdownOnTopLeftCorner: Verifying that the user sees label for Data Structure dropdown | expectedLabel | "+expectedLabel+" | actualLabel | "+actualLabel);
		Assert.assertEquals(actualLabel, expectedLabel, "Label mismatch!");
	}
	
	@Test(priority = 4)
	public void userSeesNumpyNinja() {
		String actualLabel = homepage.getNumpyNinjaLinkText();
		//String expectedLabel = CommonUtils.NUMPY_NINJA_LABEL;
		String expectedLabel =PageLinks.NUMPYNINJA_COMMON.getLinkText(); 
		logger.info("Verifying that the user sees label for NumpyNinja label | expectedLabel | "+expectedLabel+" | actualLabel | "+actualLabel);
		Assert.assertEquals(actualLabel, expectedLabel, "NumpyNinja Label mismatch!");
	}	

	@Test(priority = 5)
	public void checkRegisterLinkText() {
		String actualLabel = homepage.getRegisterLinkText();
		String regLinkLabel =PageLinks.REGISTER_LOGIN.getLinkText();
		logger.info("Verifying that the user sees Register link");
		Assert.assertEquals(actualLabel, regLinkLabel, "Reg. Link Text mismatch!");
	}

	@Test(priority = 6)
	public void checkSignInLinkText() {
		String actualLinkText = homepage.getSigninLinkText();
		String expectedSigninLinkLabel = PageLinks.SIGNIN_LOGIN.getLinkText();
		logger.info("Verifying that the user sees Signin Link");
		Assert.assertEquals(actualLinkText, expectedSigninLinkLabel, "Sign-In. Link Text mismatch!");
	}
	
	@Test(priority = 7)
	public void timeComplexity() {
		background.userLoggedin();
		dspage.clickDataStructuresGetStarted();
		dspage.timeComplexityLabelDisplayed();
		dspage.clickTimeComplexity();

		String actualTitle = dspage.getTitle();
		String expectedTitle = PageLinks.TIMECOMPLEXITY_DSINTRO.getLinkText();

		Assert.assertEquals(actualTitle, expectedTitle, "Time Complexity. Title Text mismatch!");		
	}
	
	@Test(priority = 8)
	public void tryInvalidPythonCodeExecution() {
		background.userLoggedin();
		dspage.clickDataStructuresGetStarted();
		dspage.timeComplexityLabelDisplayed();
		dspage.clickTimeComplexity();
		dspage.clickTryHere();
		Assert.assertTrue(dspage.codeTextAreaDisplayed(), "Code Textbox Shown!!!");
		try {
			dspage.invalidPythonCode("prin");
		} catch (Exception ex) {
            logger.error("Alert did not appear after running invalid code | ", ex);
		}	
	}
	@Test(priority = 9)
	public void tryValidPythonCodeExecution() {
		String actualOP=null;
		background.userLoggedin();
		dspage.clickDataStructuresGetStarted();
		dspage.timeComplexityLabelDisplayed();
		dspage.clickTimeComplexity();
		dspage.clickTryHere();
		Assert.assertTrue(dspage.codeTextAreaDisplayed(), "Code Textbox Shown!!!");
		try {
			dspage.getPythonCode("print \"Hello\" ");
			actualOP = dspage.getPythonCodeExcMsg();
		} catch (Exception ex) {
            logger.error("Alert did not appear after running valid code | ", ex);
		}
		Assert.assertEquals(actualOP, "Hello", "Python code execution. Output mismatch!");
		dspage.getPythonCodeExcMsg();
	}	
}