package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.CommonUtils;
import utilities.ExcelReader;



public class LoginPage {
	private WebDriver driver;
	
	@FindBy(xpath = "//div[@id='navbarCollapse']//ul//a[contains(text(), 'Sign in')]")
	private WebElement signinLink;
	@FindBy(name = "username")
	private WebElement usernametextbox;
	@FindBy(name = "password")
	private WebElement passwordtextbox;
	@FindBy(xpath = "//form//input[@type='submit' and @value='Login']")
	private WebElement loginButton;
	@FindBy(xpath = "//div[@role='alert']")
	WebElement errorMessage;
	@FindBy(css = ".alert.alert-primary")
	private WebElement successMessage;
	@FindBy(xpath = "//input[@name='username']/following-sibling::div[contains(@class, 'invalid-feedback')")
	WebElement popupErrorMessage;
	
	@FindBy(xpath = "//a[@class='navbar-brand']")
	private WebElement numpyNinjaLink;
	
	@FindBy(xpath = "//a[text()='Data Structures']")
	private WebElement dataStructuresDropdown;
	
	@FindBy(xpath = "//div[@id='navbarCollapse']//a[@href='/register']")
	private WebElement registerLink;
	
	@FindBy(xpath="//*[@id=\"navbarCollapse\"]/div[2]/ul/a[3]")
	private WebElement Signout;
	
	@FindBy(xpath="/html/body/div[2]")
	private WebElement logoutMessage;

	@FindBy(xpath = "//a[@href and normalize-space(text()) != '']")
	private List<WebElement> navigationLinks; //= new ArrayList<>();
	
	private static final Logger logger = LogManager.getLogger(LoginPage.class);
	
	public LoginPage(WebDriver driver) {
		System.out.println(">> LoginPage constructor.");		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	

	public String getErrorMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(errorMessage));
		String ErrMsg = errorMessage.getText();
		return ErrMsg;
	}
	public String getNumpyNinjaLinkText() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(numpyNinjaLink));
		return numpyNinjaLink.getText();
	}
	
	public String getDataStructureDropdownText() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(dataStructuresDropdown));
		return dataStructuresDropdown.getText();
	}
	
	public String getRegisterLinkText() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(registerLink));
		return registerLink.getText();
	}
		
	public String getSigninLinkText() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(signinLink));
		return signinLink.getText();
	}
	public void clickLoginLink() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(signinLink));
		signinLink.click();
	}
	
	public void fillTextBox(WebElement Textbox, String FieldName, String InputValue) 
	{
		if (InputValue != null) {
			Textbox.sendKeys(InputValue);
	    } else {
	        throw new IllegalArgumentException(FieldName + " value is null. Check your test data.");
	    }
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(Textbox));
		Textbox.clear();
		Textbox.sendKeys(InputValue);		
	}
	
	public void enterUsername(String username) {
	    if (username == null) {
	        throw new IllegalArgumentException("Username value is null. Check your test data.");
	    }

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(usernametextbox));

	    usernametextbox.clear();
	    usernametextbox.sendKeys(username);
	}
	public void enterPassword(String password) {
		if (password == null) {
	        throw new IllegalArgumentException("Password value is null. Check your test data.");
	    }
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(passwordtextbox));
		passwordtextbox.clear();
		passwordtextbox.sendKeys(password);
	}
	
	public void login(String username, String password) {
		fillTextBox(usernametextbox, "Username", username);
		fillTextBox(passwordtextbox, "Password", password);
		submitForm();
    }
	
	
	public void validateLogin(String SheetName) {
		List<Map<String, String>> users = ExcelReader.readMultiRowData(CommonUtils.EXCELREADER, SheetName);
		validateLogin(users);
	}
	
	
	public void validateLogin(List<Map<String, String>> users) {
	    System.out.println("DEBUG: Number of data rows read from Excel: " + users.size());

	    for (Map<String, String> user : users) {
	        String username = Optional.ofNullable(user.get("username")).orElse("").trim();
	        String password = Optional.ofNullable(user.get("password")).orElse("").trim();
	        String isDataValid = Optional.ofNullable(user.get("isdatavalid")).orElse("").trim();

	        // Check for empty fields
	        boolean isUsernameEmpty = username.isEmpty();
	        boolean isPasswordEmpty = password.isEmpty();

	        System.out.println("usernameEmpty: " + isUsernameEmpty + ", passwordEmpty: " + isPasswordEmpty);
	        System.out.println("username: " + username + ", password: " + password);

	        // Attempt login
	        login(username, password);
	         // Check login failure for invalid data
	        if (!isUsernameEmpty && !isPasswordEmpty && "N".equalsIgnoreCase(isDataValid)) {
	        	// Get error message (if any)
		        String errorMessage = getErrorMessage();
		        // Expect error message for invalid credentials
	            Assert.assertTrue(errorMessage.contains("Invalid Username and Password"),
	                "Expected error message for invalid credentials not found." );
	        }

	        // Validate browser validation message for missing username
	        if (isUsernameEmpty) {
	            String actualUsernameMessage = getUsernameValidationMessage();
	            System.out.println("actualUsernameMessage: " + actualUsernameMessage);
	            String expectedUsernameMessage = "Please fill out this field.";
	            Assert.assertTrue(
	                actualUsernameMessage.contains(expectedUsernameMessage),
	                "Username validation message mismatch."
	            );
	        }

	        // Validate browser validation message for missing password
	        if (isPasswordEmpty) {
	            String actualPasswordMessage = getPasswordValidationMessage();
	            System.out.println("actualPasswordMessage: " + actualPasswordMessage);
	            String expectedPasswordMessage = "Please fill out this field.";
	            Assert.assertTrue(
	                actualPasswordMessage.contains(expectedPasswordMessage),
	                "Password validation message mismatch." );
	        }
	    }
	}


	
	public void logout () {
		Signout.click();
	}
	public void submitForm() {
		loginButton.click();
	}
	
	public String getSuccessMessage() {
        return successMessage.getText().trim();
    }
	
	public String getLogoutMessage() {
        return logoutMessage.getText().trim();
    }
	
	public String getPasswordValidationMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(passwordtextbox));
		String validationMessage = passwordtextbox.getAttribute("validationMessage");
		return validationMessage;
	}
	public String getUsernameValidationMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(usernametextbox));
		String Uservalidationmessage = usernametextbox.getAttribute("validationMessage");
		return Uservalidationmessage;
	}
	
	public List<String> getAllLinkTexts() {
		return navigationLinks.stream()
                .map(link -> link.getText().trim()) 
                .collect(Collectors.toList());
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

