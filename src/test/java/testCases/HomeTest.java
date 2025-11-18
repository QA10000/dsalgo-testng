

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
import utilities.PageLinks;

public class HomeTest extends Hooks {  
	private Background background;
	private HomePage homepage;
	private static final Logger logger = LogManager.getLogger(HomeTest.class);

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
		background.launchUrl();
		background.clickGetStarted();
		// String actualTitle = registerpage.getTitle();
		String actualTitle = homepage.getTitle();
		String expectedTitle = "NumpyNinja";
		logger.info("Verifying that the user lands on the Home page");
		Assert.assertNotNull(actualTitle, "Title was null - possible page load failure?");
		Assert.assertTrue(actualTitle.contains(expectedTitle), "Home page title mismatch");
		logger.info("user navigated to home page");
	}

	@Test(priority = 1)
	public void TestUserNavigateToHomePage() {
	String expectedTitle = PageLinks.HOME_PAGE_TITLE.getLinkText();
		String actualTitle = homepage.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Title mismatch!");
		logger.info("user navigated to home page");
	}
	
	@Test(priority = 2)
	public void userClicksGetStartedButtonOnDataStructurePanel() {
		homepage.clickGetStartedButtonHomePageDSIntroModule();
		String expectedMessage = PageLinks.ERROR_MSG.getLinkText();
		String actualMessage = homepage.getnotlogedMessage();
		logger.info("DataStructurePanel: The user should able to see a warning message : "+actualMessage+" || Expected Message : "+expectedMessage);
	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Warning message mismatch!");
	}
	
	@Test(priority = 3)
	public void userClicksGetStartedButtonOnArrayPanel() {
		homepage.clickGetStartedButtonHomePageArrayModule();
		String expectedMessage = PageLinks.ERROR_MSG.getLinkText();
		String actualMessage = homepage.getnotlogedMessage();
		logger.info("ArrayPanel: The user should able to see a warning message : "+actualMessage+" || Expected Message : "+expectedMessage);
	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Warning message mismatch!");	
	}
	
	@Test(priority = 4)
	public void userClicksGetStartedButtonOnStackPanel() {
		homepage.clickGetStartedButtonHomePageStackModule();
		String expectedMessage = PageLinks.ERROR_MSG.getLinkText();
		String actualMessage = homepage.getnotlogedMessage();
		logger.info("StackPanel: The user should able to see a warning message : "+actualMessage+" || Expected Message : "+expectedMessage);
	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Warning message mismatch!");
	}
	
	@Test(priority = 5)
	public void userClicksGetStartedButtonOnLinkedListPanel() {
		homepage.clickGetStartedButtonHomePageLinkedListModule();
		//String expectedMessage = CommonUtils.NOT_SIGNED_IN_MESSAGE;
		String expectedMessage = PageLinks.ERROR_MSG.getLinkText();
		String actualMessage = homepage.getnotlogedMessage();
		logger.info("LinkedListPanel: The user should able to see a warning message : "+actualMessage+" || Expected Message : "+expectedMessage);
	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Warning message mismatch!");	
	}
	
	@Test(priority = 6)	
	public void userClicksGetStartedButtonOnQueuePanel() {
		homepage.clickGetStartedButtonHomePageQueueModule();
	//	String expectedMessage = CommonUtils.NOT_SIGNED_IN_MESSAGE;
	    String expectedMessage = PageLinks.ERROR_MSG.getLinkText();
		String actualMessage = homepage.getnotlogedMessage();
		logger.info("QueuePanel: The user should able to see a warning message : "+actualMessage+" || Expected Message : "+expectedMessage);
	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Warning message mismatch!");	
	}
	
	@Test(priority = 7)
	public void userClicksGetStartedButtonOnTreePanel() {
		homepage.clickGetStartedButtonHomePageTreeModule();    
		String expectedMessage = PageLinks.ERROR_MSG.getLinkText();
		String actualMessage = homepage.getnotlogedMessage();
		logger.info("TreePanel: The user should able to see a warning message : "+actualMessage+" || Expected Message : "+expectedMessage);
	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Warning message mismatch!");
	}
	
	@Test(priority = 8)
	public void userClicksGetStartedButtonOnGraphPanel() {
		homepage.clickGetStartedButtonHomePageGraphModule();
		String expectedMessage = PageLinks.ERROR_MSG.getLinkText();
		String actualMessage = homepage.getnotlogedMessage();
		logger.info("GraphPanel: The user should able to see a warning message : "+actualMessage+" || Expected Message : "+expectedMessage);
	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Warning message mismatch!");
	}
	
	@Test(priority = 9)
	public void userSeesDropdownOnTopLeftCorner() {
		String actualLabel = homepage.getDataStructureLblText();
		String expectedLabel = PageLinks.DATASTRUCTURES_COMMON.getLinkText();
		logger.info("DropdownOnTopLeftCorner: Verifying that the user sees label for Data Structure dropdown | expectedLabel | "+expectedLabel+" | actualLabel | "+actualLabel);
		Assert.assertEquals(actualLabel, expectedLabel, "Label mismatch!");
	}
	
	@Test(priority = 10)
	public void userSeesNumpyNinja() {
		String actualLabel = homepage.getNumpyNinjaLinkText();
		String expectedLabel =PageLinks.NUMPYNINJA_COMMON.getLinkText(); 
		logger.info("Verifying that the user sees label for NumpyNinja label | expectedLabel | "+expectedLabel+" | actualLabel | "+actualLabel);
		Assert.assertEquals(actualLabel, expectedLabel, "NumpyNinja Label mismatch!");
	}	

	@Test(priority = 11)
	public void checkRegisterLinkText() {
		String actualLabel = homepage.getRegisterLinkText();
		String regLinkLabel =PageLinks.REGISTER_LOGIN.getLinkText();
		logger.info("Verifying that the user sees Register link");
		Assert.assertEquals(actualLabel, regLinkLabel, "Reg. Link Text mismatch!");
	}

	@Test(priority = 12)
	public void checkSignInLinkText() {
		String actualLinkText = homepage.getSigninLinkText();
		String expectedSigninLinkLabel = PageLinks.SIGNIN_LOGIN.getLinkText();
		logger.info("Verifying that the user sees Signin Link");
		Assert.assertEquals(actualLinkText, expectedSigninLinkLabel, "Sign-In. Link Text mismatch!");
	}
}