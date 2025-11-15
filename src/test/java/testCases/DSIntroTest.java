package testCases;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import dataproviders.ExcelDataProvider;
import driverfactory.DriverManager;
import pages.HomePage;
import pages.Background;
import pages.DataStructurePage;
import utilities.CommonUtils;
import utilities.enumClass.Module;
import utilities.enumClass.PageLinks;

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

	@Test(priority = 7)
	public void TestUserNavigateToHomePage() {
		background.verifyHomePageIsDisplayed();
		logger.info("user navigated to home page");
	}
	
	@Test(priority = 8)
	public void userClicksGetStartedButtonOnDataStructurePanel() {
		homepage.clickGetStartedButtonHomePageDSIntroModule();
		String expectedMessage = CommonUtils.NOT_SIGNED_IN_MESSAGE;
		String actualMessage = homepage.getnotlogedMessage();
		logger.info("DataStructurePanel: The user should able to see a warning message : "+actualMessage+" || Expected Message : "+expectedMessage);
	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Warning message mismatch!");
	}
	
	@Test(priority = 9)
	public void userSeesDropdownOnTopLeftCorner() {
		String actualLabel = homepage.getDataStructureLblText();
		String expectedLabel = CommonUtils.DS_DROP_DOWN_LABEL;
		logger.info("DropdownOnTopLeftCorner: Verifying that the user sees label for Data Structure dropdown | expectedLabel | "+expectedLabel+" | actualLabel | "+actualLabel);
		Assert.assertEquals(actualLabel, expectedLabel, "Label mismatch!");
	}
	
	@Test(priority = 10)
	public void userSeesNumpyNinja() {
		String actualLabel = homepage.getNumpyNinjaLinkText();
		String expectedLabel = CommonUtils.NUMPY_NINJA_LABEL;
		logger.info("Verifying that the user sees label for NumpyNinja label | expectedLabel | "+expectedLabel+" | actualLabel | "+actualLabel);
		Assert.assertEquals(actualLabel, expectedLabel, "NumpyNinja Label mismatch!");
	}	

	@Test(priority = 11)
	public void checkRegisterLinkText() {
		String actualLabel = homepage.getRegisterLinkText();
		String regLinkLabel = CommonUtils.REGISTER_LINK_TEXT;
		logger.info("Verifying that the user sees Register link");
		Assert.assertEquals(actualLabel, regLinkLabel, "Reg. Link Text mismatch!");
	}

	@Test(priority = 12)
	public void checkSignInLinkText() {
		String actualLinkText = homepage.getSigninLinkText();
		String expectedSigninLinkLabel = CommonUtils.SIGNIN_LINK_TEXT;
		logger.info("Verifying that the user sees Signin Link");
		Assert.assertEquals(actualLinkText, expectedSigninLinkLabel, "Sign-In. Link Text mismatch!");
	}
	
	@Test(priority = 13)
	void testAllLinksInStack() {
		background.userLoggedin();
		dspage.clickDSIntroGetStarted();
		List<String> expectedLinks = PageLinks.getLinksForModules(Module.COMMON, Module.DSINTRO);
		List<String> actualLinks = dspage.getAllLinkTexts();
		logger.info("StackTest::actualLinks: " + actualLinks.toString()+ " ExpectedLinks: "+expectedLinks);
		List<String> missing = dspage.verifyAllExpectedLinksArePresent(expectedLinks, actualLinks);
		Assert.assertTrue(missing.isEmpty(),"These expected links were missing: " + missing + "\nActual list: " + actualLinks);
	}
}