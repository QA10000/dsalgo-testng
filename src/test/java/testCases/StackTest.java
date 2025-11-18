package testCases;

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
import pages.StackPage;
import pages.TryEditorPage;
import utilities.PageLinks;
import utilities.Module;



public class StackTest extends Hooks{
	private StackPage stackpage;
	private Background background;
	private TryEditorPage tryEditorPage;
	private static final Logger logger = LogManager.getLogger(StackTest.class);
	
	@BeforeMethod
	public void setUp() {
		WebDriver driver = null;
		try {
			driver = DriverManager.getDriver();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    background = new Background(driver);
	    stackpage = new StackPage(driver);
	    tryEditorPage = new TryEditorPage(driver);
	    background.launchUrl();
	    background.clickGetStarted();
	    background.userLoggedin();
	    stackpage.clickStackGetStarted();
	}
	
	@Test(priority = 1)
	void testAllLinksInStack() {
		List<String> expectedLinks = PageLinks.getLinksForModules(Module.COMMON, Module.STACK);
		List<String> actualLinks = stackpage.getAllLinkTexts();
		logger.info("StackTest::actualLinks: " + actualLinks.toString());
		List<String> missing = stackpage.verifyAllExpectedLinksArePresent(expectedLinks, actualLinks);
		Assert.assertTrue(missing.isEmpty(),"These expected links were missing: " + missing + "\nActual list: " + actualLinks);
	}
	
	@Test(priority = 2)
	public void testTheOperationsLinks() {
		stackpage.clickOperationsinStackLink();
		List<String> expectedLinks = PageLinks.getLinksForModules(Module.COMMON, Module.STACK, Module.ALLSUBMODULES);    
		List<String> actualLinks = stackpage.getAllLinkTexts();
		logger.info("StackTest::actualLinks: " + actualLinks.toString());
		List<String> missing = stackpage.verifyAllExpectedLinksArePresent(expectedLinks, actualLinks);
		Assert.assertTrue(missing.isEmpty(),"These expected links were missing: " + missing + "\nActual list: " + actualLinks);
	}
	
	@Test(priority = 3)

	public void testTheImplementationLinks() {
		stackpage.clickImplementationLink();
		List<String> expectedLinks = PageLinks.getLinksForModules(Module.COMMON, Module.STACK, Module.ALLSUBMODULES);
		List<String> actualLinks = stackpage.getAllLinkTexts();
		logger.info("StackTest::actualLinks: " + actualLinks.toString());
		List<String> missing = stackpage.verifyAllExpectedLinksArePresent(expectedLinks, actualLinks);
		Assert.assertTrue(missing.isEmpty(),"These expected links were missing: " + missing + "\nActual list: " + actualLinks);
	}
	@Test(priority = 4)
	public void testApplicationsLinks() {
		stackpage.clickApplicationsLink();
		List<String> expectedLinks = PageLinks.getLinksForModules(Module.COMMON, Module.STACK, Module.ALLSUBMODULES);
		List<String> actualLinks = stackpage.getAllLinkTexts();
		logger.info("StackTest::actualLinks: " + actualLinks.toString());
		List<String> missing = stackpage.verifyAllExpectedLinksArePresent(expectedLinks, actualLinks);
		Assert.assertTrue(missing.isEmpty(),"These expected links were missing: " + missing + "\nActual list: " + actualLinks);
	}
	
	@Test(priority = 5, dataProvider = "excelDataValidStackOptions", dataProviderClass = ExcelDataProvider.class)
	public void testValidCodeinTryEditor(Map<String, String> data) {
		String OptionOnStack = data.get("OptionOnStack");
	    String code = data.get("code");
	    String expectedMessage = data.get("message");
	    
		stackpage.clickStackOption(OptionOnStack);
		stackpage.clickTryHere();
		tryEditorPage.codeEditorSendKeys(code);
		tryEditorPage.clickRunBtn();
		String actualMessage = tryEditorPage.getTryHereOutput();
	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Alert message mismatch");
	}
	
	@Test(priority = 6, dataProvider = "excelDataInValidStackOptions", dataProviderClass = ExcelDataProvider.class)
	public void testInValidCodeinTryEditor(Map<String, String> data) {
		String OptionOnStack = data.get("OptionOnStack");
	    String code = data.get("code");
	    String expectedMessage = data.get("errorMessage");
	    
		stackpage.clickStackOption(OptionOnStack);
		stackpage.clickTryHere();
		tryEditorPage.codeEditorSendKeys(code);
		tryEditorPage.clickRunBtn();
		String actualMessage = tryEditorPage.getAlertMessageAndAccept();
	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Alert message mismatch");
	}
}