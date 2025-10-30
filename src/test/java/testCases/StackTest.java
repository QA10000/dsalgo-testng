package testCases;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.Background;
import pages.StackPage;
import utilities.enumClass.PageLinks;
import utilities.enumClass.Module;



public class StackTest extends Hooks{
	private StackPage stackpage;
	private Background background;
	private static final Logger logger = LogManager.getLogger(StackTest.class);
	
	@BeforeMethod
	public void setUp() {
	    driver = Hooks.getDriver();
	    background = new Background(driver);
	    stackpage = new StackPage(driver);
	    background.launchUrl();
	    background.ClickGetStarted();
	    background.userLoggedin();
	}
	
	@Test(priority = 1)
	void testAllLinksInStack() {
		stackpage.clickStackGetStarted();
		List<String> expectedLinks = PageLinks.getLinksForModules(Module.COMMON, Module.STACK);
		List<String> actualLinks = stackpage.getAllLinkTexts();
		logger.info("StackTest::actualLinks: " + actualLinks.toString());
		List<String> missing = stackpage.verifyAllExpectedLinksArePresent(expectedLinks, actualLinks);
		Assert.assertTrue(missing.isEmpty(),"These expected links were missing: " + missing + "\nActual list: " + actualLinks);
	}
	
	@Test(priority = 2)
	public void testTheOperationsLinks() {
		stackpage.clickStackGetStarted();
		stackpage.clickOperationsinStackLink();
		List<String> expectedLinks = PageLinks.getLinksForModules(Module.COMMON, Module.STACK, Module.ALLSUBMODULES);
		List<String> actualLinks = stackpage.getAllLinkTexts();
		logger.info("StackTest::actualLinks: " + actualLinks.toString());
		List<String> missing = stackpage.verifyAllExpectedLinksArePresent(expectedLinks, actualLinks);
		Assert.assertTrue(missing.isEmpty(),"These expected links were missing: " + missing + "\nActual list: " + actualLinks);
	}
	
	@Test(priority = 3)
	public void testTheImplementationLinks() {
		stackpage.clickStackGetStarted();
		stackpage.clickImplementationLink();
		List<String> expectedLinks = PageLinks.getLinksForModules(Module.COMMON, Module.STACK, Module.ALLSUBMODULES);
		List<String> actualLinks = stackpage.getAllLinkTexts();
		logger.info("StackTest::actualLinks: " + actualLinks.toString());
		List<String> missing = stackpage.verifyAllExpectedLinksArePresent(expectedLinks, actualLinks);
		Assert.assertTrue(missing.isEmpty(),"These expected links were missing: " + missing + "\nActual list: " + actualLinks);
	}
	
	public void testApplicationsLinks() {
		stackpage.clickStackGetStarted();
		stackpage.clickApplicationsLink();
		List<String> expectedLinks = PageLinks.getLinksForModules(Module.COMMON, Module.STACK, Module.ALLSUBMODULES);
		List<String> actualLinks = stackpage.getAllLinkTexts();
		logger.info("StackTest::actualLinks: " + actualLinks.toString());
		List<String> missing = stackpage.verifyAllExpectedLinksArePresent(expectedLinks, actualLinks);
		Assert.assertTrue(missing.isEmpty(),"These expected links were missing: " + missing + "\nActual list: " + actualLinks);
	}
}