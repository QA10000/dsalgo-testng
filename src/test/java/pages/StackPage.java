package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StackPage {

	private WebDriver driver;
		
	@FindBy(xpath = "//div[@class='card-body d-flex flex-column'][h5[text()='Stack']]//a[contains(@href, 'stack')]")
	private WebElement stackGetStarted;
	
	@FindBy(xpath = "//a[@href='/tryEditor']")
	private WebElement tryHereButton;
	
	@FindBy(xpath = "//a[@href and normalize-space(text()) != '']")
    private List<WebElement> navigationLinks;
	
	
	
	private static final Logger logger = LogManager.getLogger(StackPage.class);

	public StackPage(WebDriver driver) {
		System.out.println(">> StackPage constructor.");		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickStackGetStarted() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(stackGetStarted));
		stackGetStarted.click();
	}
	
	public void clickStackOption(String optionName) {
        driver.findElement(By.linkText(optionName)).click();
    }
	
	public void clickOperationsinStackLink() {
        driver.findElement(By.linkText("Operations in Stack")).click();
    }

	public void clickImplementationLink() {
        driver.findElement(By.linkText("Implementation")).click();
    }

	public void clickApplicationsLink() {
        driver.findElement(By.linkText("Applications")).click();
    }

	public List<String> getAllLinkTexts() {
        return navigationLinks.stream()
                .map(link -> link.getText().trim()) 
                .collect(Collectors.toList());
    }
	
	public boolean areAllExpectedLinksPresent(List<String> expectedLinks) {
	    List<String> actualLinks = getAllLinkTexts();
	    return actualLinks.containsAll(expectedLinks);
	}
	
	public void clickTryHere() {
		tryHereButton.click();
	}	
	
	public List<String> verifyAllExpectedLinksArePresent(List<String> expectedLinks, List<String> actualLinks) {
		logger.info("actualLinks : " + actualLinks.toString());
		List<String> missing = new ArrayList<>();
		for (String expected : expectedLinks) {
			if (!actualLinks.contains(expected)) {
				missing.add(expected);
			}
		}
		return missing;
	}
}

