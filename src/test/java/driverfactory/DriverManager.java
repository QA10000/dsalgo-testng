package driverfactory;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {

    private static final ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();
    private static final Logger LOG = LogManager.getLogger(DriverManager.class);

       public static WebDriver launchBrowser(String br) {
           System.out.println(br + "Value of Br");

    	switch (br.toLowerCase()) {
    	case "chrome":
    	ChromeOptions optionsChrome = new ChromeOptions();
    	optionsChrome.addArguments("--headless=new");
    	tldriver.set(new ChromeDriver());
    	break;
    	case "edge":
    	EdgeOptions optionsEdge = new EdgeOptions();
    	optionsEdge.addArguments("--headless=new");
    	tldriver.set(new EdgeDriver());
    	break;
    	case "firefox":
    	FirefoxOptions optionsFirefox = new FirefoxOptions();
    	optionsFirefox.addArguments("--headless");
    	tldriver.set(new FirefoxDriver());
    	break;
    	default:
    	tldriver.set(new ChromeDriver());
    	break;
    	}
    	getDriver().manage().deleteAllCookies();
    	getDriver().manage().window().maximize();
    	getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
    	return getDriver();
    	}

    public static WebDriver getDriver() {
        return tldriver.get();
    }
    
   
    public static void quitDriver() {
        if (tldriver.get() != null) {
            tldriver.get().quit();
            tldriver.remove(); // prevent memory leak
        }
    }
}
