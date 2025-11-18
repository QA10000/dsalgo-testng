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
import pages.LinkedListPage;

import pages.TryEditorPage;
import utilities.enumClass.Module;
import utilities.enumClass.PageLinks;

public class LinkedListTest extends Hooks{
	private LinkedListPage linkedlistpage;
	private Background background;
	private TryEditorPage tryEditorPage;
	private static final Logger logger = LogManager.getLogger(LinkedListTest.class);
	
	@BeforeMethod
	public void setUp() {
		WebDriver driver = null;
		try {
			driver = DriverManager.getDriver();
		} catch (Exception e) {
			e.printStackTrace();
		}

	    background = new Background(driver);
	    linkedlistpage = new LinkedListPage(driver);
	    tryEditorPage = new TryEditorPage(driver);
	    background.launchUrl();
	    background.clickGetStarted();
	    background.userLoggedin();
	    linkedlistpage.clicklinkedListGetStarted();
	}

	@Test(priority = 1)
	void testPageLinks() {
		//linkedlistpage.clicklinkedListGetStarted();
		List<String> expectedLinks = PageLinks.getLinksForModules(Module.COMMON, Module.LINKEDLIST);
		List<String> actualLinks = linkedlistpage.getAllLinkTexts();
		logger.info("LinkedList::actualLinks: " + actualLinks.toString());
		List<String> missing = linkedlistpage.verifyAllExpectedLinksArePresent(expectedLinks, actualLinks);
		Assert.assertTrue(missing.isEmpty(),"These expected links were missing: " + missing + "\nActual list: " + actualLinks);
	}
	
	/*@Test(priority = 4)
	void testPageHeaderLinks() {
		//linkedlistpage.clicklinkedListGetStarted();
		List<String> expectedLinks = PageLinks.getAllPageHeaderTexts();
		List<String> actualLinks = linkedlistpage.getAllLinkTexts();
		logger.info("StackTest::actualLinks: " + actualLinks.toString());
		List<String> missing = linkedlistpage.verifyAllExpectedLinksArePresent(expectedLinks, actualLinks);
		Assert.assertTrue(missing.isEmpty(),"These expected links were missing: " + missing + "\nActual list: " + actualLinks);
	}*/
	
	@Test(priority = 2, dataProvider = "excelDataInValidLinkedListOptions", dataProviderClass = ExcelDataProvider.class)
	public void testInvalidCodeinTryEditor(Map<String, String> data) {
		String OptionOnLinkedList = data.get("OptionOnLinkedList");
	    String code = data.get("code");
	    String expectedMessage = data.get("errorMessage");
	    
		linkedlistpage.clickLinkedListOption(OptionOnLinkedList);
		linkedlistpage.clickTryHere();
		tryEditorPage.codeEditorSendKeys(code);
		tryEditorPage.clickRunBtn();
		String actualMessage = tryEditorPage.getAlertMessageAndAccept();
	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Alert message mismatch");
	}
	
	@Test(priority = 3, dataProvider = "excelDataValidLinkedListOptions", dataProviderClass = ExcelDataProvider.class)
	public void testValidCodeinTryEditor(Map<String, String> data) {
		String OptionOnLinkedList = data.get("OptionOnLinkedList");
	    String code = data.get("code");
	    String expectedMessage = data.get("errorMessage");
	    
		linkedlistpage.clickLinkedListOption(OptionOnLinkedList);
		linkedlistpage.clickTryHere();
		tryEditorPage.codeEditorSendKeys(code);
		tryEditorPage.clickRunBtn();
		String actualMessage = tryEditorPage.getTryHereOutput();
	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Alert message mismatch");
	}

}
