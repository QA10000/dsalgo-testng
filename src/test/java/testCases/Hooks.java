package testCases;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import driverfactory.DriverManager;
import utilities.CommonUtils;

public class Hooks {
	public static WebDriver driver;
	private static final Logger LOG=LogManager.getLogger(Hooks.class);
	
	@BeforeMethod
    public void beforeScenario() {
        try {
            CommonUtils.loadProperties();
            driver = DriverManager.launchBrowser();
            driver.manage().window().maximize();
            LOG.info("Browser is launched and maximized.");
        } catch (Exception e) {
            LOG.error("Failed to launch browser", e);
            throw new RuntimeException("Browser launch failed", e);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            LOG.info("Browser session closed.");
            driver = null;  // reset driver after quitting
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
