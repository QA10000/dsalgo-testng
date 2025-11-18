package testCases;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ExcelDataProvider;
import utilities.PageLinks;
import driverfactory.DriverManager;
import pages.ArrayPage;
import pages.Background;
import pages.RegisterPage;

public class ArrayTest extends Hooks {
	private Background background;
	private ArrayPage arraypage;
	private RegisterPage registerpage;
	private static final Logger logger = LogManager.getLogger(ArrayTest.class);

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
		background.launchUrl();
		background.clickGetStarted();
		String actualTitle = arraypage.getTitle();
		String expectedTitle = "NumpyNinja";
		logger.info("Verifying that the user lands on the Registration page");
		Assert.assertNotNull(actualTitle, "Title was null - possible page load failure?");
		Assert.assertTrue(actualTitle.contains(expectedTitle), "Failure message");
		logger.info("user navigated to home page");
		arraypage.userLoggedin();
	}

	@Test(priority = 1)
	public void TestUserNavigateToArrayPage() {
		arraypage.clickStartBtn();
		String actualTitle = registerpage.getTitle();
		String expectedTitle = "Array";
		logger.info("Verifying that the user lands on the Registration page");
		Assert.assertNotNull(actualTitle, "Title was null - possible page load failure?");
		Assert.assertTrue(actualTitle.contains(expectedTitle), "Failure message");
		logger.info("user navigated to home page");
	}

	@Test(priority = 2, dataProvider = "excelDataOptions", dataProviderClass = ExcelDataProvider.class)
	public void testArrayPageWithInvalidCode(Map<String, String> data) throws InterruptedException {
		logger.info("Running test with data: " + data);
		String optionOnArray = data.get("OptionOnArray");
		String code = data.get("code");
		String expectedMessage = data.get("errorMessage");
		arraypage.clickStartBtn();
		Thread.sleep(3000); 
		arraypage.clickArraySection(optionOnArray);
		arraypage.clickTryMeButton();
		arraypage.codeEditorSendKeys(code);
		arraypage.clickRunBtn();
		String actualMessage = arraypage.getAlertMessageAndAccept();
		logger.info("Verifying that the user sees error message");
		Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Alert message mismatch!");
	}

	@Test(priority = 3, dataProvider = "excelDataValidOptions", dataProviderClass = ExcelDataProvider.class)
	public void testArrayPageWithValidCode(Map<String, String> data) throws InterruptedException {
		logger.info("Running test with data: " + data);
		String optionOnArray = data.get("OptionOnArray");
		String code = data.get("code");
		String expectedMessage = data.get("message");
		arraypage.clickStartBtn();
		Thread.sleep(3000); 
		Assert.assertEquals(arraypage.getTitleArraypage(), "Array", "Not on Array page!");
		arraypage.clickArraySection(optionOnArray);
		arraypage.clickTryMeButton();
		arraypage.codeEditorSendKeys(code);
		arraypage.clickRunBtn();
		arraypage.outputDisplayed();
		String actualMessage = arraypage.getTextoutput();
		logger.info("Verifying that the user sees output message");
		Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Alert message mismatch!");
	}


	@Test(priority = 4, dataProvider = "runArrayData", dataProviderClass = ExcelDataProvider.class)
	public void testpracticeCode(String code, String expectedResult) {
		arraypage.practicQuestionValidScn();
		arraypage.clickSearchArrayLink();
		arraypage.codeEditorSendKeys(code);
		arraypage.clickRunBtn();
	}
	
	@Test(priority = 5) 
	public void testLogoDisplay() {
		String actualLabel = arraypage.getNumpyNinjaLinkText();
		String expectedLabel = PageLinks.NUMPYNINJA_COMMON.getLinkText();
		logger.info("Verifying that the user sees NumpaiNinja logo");
		Assert.assertEquals(actualLabel, expectedLabel, "Label mismatch!");
	}
	
	@Test(priority = 6) 
	public void testDataSrtucureDisplay() {
		String actualLabel = arraypage.getDataStructureLblText();
		//String expectedLabel = "Data Structures";
		String expectedLabel = PageLinks.DATASTRUCTURES_COMMON.getLinkText();
		logger.info("Verifying that the user sees label for  Data Structure dropdown");
		Assert.assertEquals(actualLabel, expectedLabel, "Label mismatch!");
	}
	
	@Test(priority = 7) 
	public void testSignoutBtnDisplay() {
		String actualLabel = arraypage.getSignoutLblText();
		String expectedLabel = PageLinks.SIGNOUT_COMMON.getLinkText();
		logger.info("Verifying that the user sees label for  Data Structure dropdown");
		Assert.assertEquals(actualLabel, expectedLabel, "Label mismatch!");
	}
	
	@Test(priority = 8) 
	public void testUserNameDisplay() {
		String actualLabel = arraypage.getUsernameLblText();
		String expectedLabel =PageLinks.USERNAME.getLinkText();
		logger.info("Verifying that the user sees label for  Data Structure dropdown");
		Assert.assertEquals(actualLabel, expectedLabel, "Label mismatch!");
	}
	

	}

	
	
