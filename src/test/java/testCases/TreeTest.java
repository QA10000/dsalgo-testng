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
import pages.TreePage;

public class TreeTest extends Hooks {
	private Background background;
	private RegisterPage registerpage;
	private ArrayPage arraypage;
	private TreePage treepage;
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
		registerpage = new RegisterPage(driver);
		arraypage = new ArrayPage(driver);
		treepage = new TreePage(driver);

		background.launchUrl();
		background.clickGetStarted();
		String actualTitle = registerpage.getTitle();
		String expectedTitle = "NumpyNinja";
		logger.info("Verifying that the user lands on the Registration page");
		Assert.assertNotNull(actualTitle, "Title was null - possible page load failure?");
		Assert.assertTrue(actualTitle.contains(expectedTitle), "Failure message");
		logger.info("user navigated to home page");
		arraypage.userLoggedin();
	}

	@Test(priority = 1)
	public void TestUserNavigateToTreePage() {
		//arraypage.clickStartBtn();
		treepage.clickTreeStrtBtn();
		String actualTitle = treepage.getTreePageTitle();
		String expectedTitle = "Tree";
		logger.info("Verifying that the user lands on the Registration page");
		Assert.assertNotNull(actualTitle, "Title was null - possible page load failure?");
		Assert.assertTrue(actualTitle.contains(expectedTitle), "Failure message");
		logger.info("user navigated to Tree page");
	}

	@Test(priority = 2, dataProvider = "treeDataInValidOptions", dataProviderClass = ExcelDataProvider.class)
	public void testTreePageWithInvalidCode(Map<String, String> data) throws InterruptedException {
		logger.info("Running test with data: " + data);
		String optionOnTree = data.get("OptionOnTree");
		String code = data.get("code");
		String expectedMessage = data.get("errorMessage");
		//arraypage.clickStartBtn();
		treepage.clickTreeStrtBtn();
		Thread.sleep(3000); 
		//arraypage.clickArraySection(optionOnArray);
		treepage.clickTreeOptiions(optionOnTree);
		treepage.clickTryMeButton();
		treepage.codeEditorSendKeys(code);
		treepage.clickRunBtn();
		String actualMessage = treepage.getAlertMessageAndAccept();
		logger.info("Verifying that the user sees error message");
		Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Alert message mismatch!");
	}

	@Test(priority = 3, dataProvider = "treeDataValidOptions", dataProviderClass = ExcelDataProvider.class)
	public void testArrayPageWithValidCode(Map<String, String> data) throws InterruptedException {
		logger.info("Running test with data: " + data);
		String optionOnTree= data.get("OptionOnTree");
		String code = data.get("code");
		String expectedMessage = data.get("message");
		treepage.clickTreeStrtBtn();
		Thread.sleep(3000); 
		Assert.assertEquals(treepage.getTreePageTitle(), "Tree", "Not on Array page!");
		treepage.clickTreeOptiions(optionOnTree);
		treepage.clickTryMeButton();
		treepage.codeEditorSendKeys(code);
		treepage.clickRunBtn();
		treepage.outputDisplayed();
		String actualMessage = treepage.getTreeTextoutput();
		logger.info("Verifying that the user sees output message");
		Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Alert message mismatch!");
	}

	
	@Test(priority = 5) 
	public void testLogoDisplay() {
		String actualLabel = treepage.getNumpyNinjaLinkTextTreePg();
		String expectedLabel = "NumpyNinja";
		logger.info("Verifying that the user sees NumpaiNinja logo");
		Assert.assertEquals(actualLabel, expectedLabel, "Label mismatch!");
	}
	
	@Test(priority = 6) 
	public void testDataSrtucureDisplay() {
		String actualLabel = treepage.getDataStructureLblText();
		String expectedLabel = "Data Structures";
		logger.info("Verifying that the user sees label for  Data Structure dropdown");
		Assert.assertEquals(actualLabel, expectedLabel, "Label mismatch!");
	}
	
	@Test(priority = 7) 
	public void testSignoutBtnDisplay() {
		String actualLabel = treepage.getSignoutLblText();
		String expectedLabel = "Sign out";
		logger.info("Verifying that the user sees label for  Data Structure dropdown");
		Assert.assertEquals(actualLabel, expectedLabel, "Label mismatch!");
	}
	
	@Test(priority = 8) 
	public void testUserNameDisplay() {
		String actualLabel = treepage.getUsernameLblText();
		String expectedLabel = "Qatitans1";
		logger.info("Verifying that the user sees label for  Data Structure dropdown");
		Assert.assertEquals(actualLabel, expectedLabel, "Label mismatch!");
	}
	

	}

	
	
