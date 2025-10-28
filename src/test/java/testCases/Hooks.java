package testCases;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import driverfactory.DriverManager;
import utilities.CommonUtils;

public class Hooks {
    private static final Logger LOG = LogManager.getLogger(Hooks.class);

    @BeforeMethod
    @Parameters("browser")
    public void beforeScenario(@Optional("firefox") String browser) {
        try {
          CommonUtils.loadProperties(); 
          DriverManager.launchBrowser(browser); // thread-safe browser launch
            DriverManager.getDriver().manage().window().maximize();
            LOG.info("Browser is launched and maximized: " + browser);
        } catch (Exception e) {
            LOG.error("Failed to launch browser", e);
            throw new RuntimeException("Browser launch failed", e);
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();  // safely quit and remove thread-local WebDriver
        LOG.info("Browser session closed.");
    }
  
}


