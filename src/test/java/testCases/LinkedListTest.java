package testCases;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.Background;
import pages.LinkedListPage;
import utilities.enumClass.Module;
import utilities.enumClass.PageLinks;

public class LinkedListTest extends Hooks{
	private LinkedListPage linkedlistpage;
	private Background background;
	private static final Logger logger = LogManager.getLogger(LinkedListTest.class);
	
	@BeforeMethod
	public void setUp() {
		WebDriver driver = null;
	    // driver = Hooks.getDriver();
	    background = new Background(driver);
	    linkedlistpage = new LinkedListPage(driver);
	    background.launchUrl();
	    // background.ClickGetStarted();
	    background.userLoggedin();
	}

	@Test(priority = 1)
	void testPageLinks() {
		linkedlistpage.clicklinkedListGetStarted();
		List<String> expectedLinks = PageLinks.getLinksForModules(Module.COMMON, Module.LINKEDLIST);
		List<String> actualLinks = linkedlistpage.getAllLinkTexts();
		logger.info("StackTest::actualLinks: " + actualLinks.toString());
		List<String> missing = linkedlistpage.verifyAllExpectedLinksArePresent(expectedLinks, actualLinks);
		Assert.assertTrue(missing.isEmpty(),"These expected links were missing: " + missing + "\nActual list: " + actualLinks);
	}
}
