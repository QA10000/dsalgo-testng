package pages;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CommonUtils;
import utilities.ExcelReader;


public class RegisterPage {
	WebDriver driver;

	@FindBy(xpath = "//div[@id='navbarCollapse']//a[@href='/register']")
	private WebElement registerLink;
	@FindBy(name = "username")
	private WebElement usernametextbox;
	@FindBy(name = "password1")
	private WebElement passwordtextbox;
	@FindBy(name = "password2")
	private WebElement confirmedPswdtextbox;
	@FindBy(xpath = "//form//input[@type='submit' and @value='Register']")
	private WebElement registerButton;
	@FindBy(xpath = "//div[contains(normalize-space(.), 'password_mismatch')]")
	private WebElement mismatchpassword;
	@FindBy(css = ".alert.alert-primary")
	private WebElement successMessage;
	@FindBy(xpath = "//a[@class='navbar-brand']")
	private WebElement numpyNinjaLink;
	@FindBy(xpath = "//a[text()='Data Structures']")
	private WebElement dataStructuresDropdown;
	@FindBy(xpath = "//div[@id='navbarCollapse']//ul//a[contains(text(), 'Sign in')]")
	private WebElement signinLink;
	
	String title;
	
	//ScenarioContext scenariocontext;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void setScenarioContext(ScenarioContext scenariocontext) {
	//	this.scenariocontext = scenariocontext;
		
	}

	public String getTitle() {
	    return driver.getTitle();
	}

	public void clickRegisterLink() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(registerLink));
		registerLink.click();
		title = driver.getTitle();
	}

	public void enterUsername(String username) {
		WebElement textbox = new WebDriverWait(driver, Duration.ofSeconds(10))
		.until(ExpectedConditions.elementToBeClickable(By.name("username")));
		textbox.clear();
		if (username != null && !username.isEmpty()) {
			textbox.sendKeys(username);
		}
	}

	public void enterPassword(String password1) {
		WebElement textbox = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(By.name("password1")));

		textbox.clear();
		if (password1 != null && !password1.isEmpty()) {
			textbox.sendKeys(password1);
		}
	}

	public void enterConfirmPassword(String password2) {
		WebElement textbox = new WebDriverWait(driver, Duration.ofSeconds(10))
		.until(ExpectedConditions.elementToBeClickable(By.name("password2")));
		textbox.clear();
		if (password2 != null && !password2.isEmpty()) {
			textbox.sendKeys(password2);
		}
	}

	public void submitForm() {
		registerButton.click();
	}

	public void enterValidDatafrmSheet(String file) {
		String filePath = CommonUtils.EXCELREADER;
		Map<String, String> testData = ExcelReader.readData(filePath, file);
		String username = testData.get("username");// getting input from datasheet
		String password = testData.get("password");
		String confirmedPassword = testData.get("confirmpassword");
		enterUsername(username);
		enterPassword(password);
		enterConfirmPassword(confirmedPassword);
		submitForm();
		System.out.println("username" + username);
	//	System.out.println("Setting username in context: " + username + " | Context: " + scenariocontext.hashCode());
		//scenariocontext.set("registeredUsername", username);
	}

	public void enterInvalidData() {// 
		//List<Map<String, String>> users = dataTable.asMaps(String.class, String.class); // are taking data from
		// datatable in feature file
		// then putting it in map then
		// we are taking from map and
		// then putting into form
		/*for (Map<String, String> user : users) {// put hardcoded value for username
			String username = user.get("username");
			String password = user.get("password");
			String confirmpassword = user.get("confirmpassword");
			enterUsername(username);
			enterPassword(password);
			enterConfirmPassword(confirmpassword);
			submitForm();
		}*/
	}
	
	public void enterEmptyField(String username, String password, String confirmpassword) {
		enterUsername(username);
		enterPassword(password);
		enterConfirmPassword(confirmpassword);
		submitForm();
	}
	
	public String displayEmptyfieldMsg() {
		if(isUserNameEmpty()) {
			return  getUsernameValidationMessage();
		} else if (isPasswordEmpty()) {
			return getPasswordValidationMessage();	
		} else if (isConfirmPasswordEmpty()) {
			return getConfirmPasswordValidationMessage();
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement freshElement = wait.until(ExpectedConditions.visibilityOf(mismatchpassword));
		return freshElement.getText().trim();		
		
	}
	
	public boolean isUserNameEmpty() {
		return(usernametextbox.getAttribute("value").trim().isEmpty());
	}
	
	public boolean isPasswordEmpty() {
		return(passwordtextbox.getAttribute("value").trim().isEmpty());
	}
	
	public boolean isConfirmPasswordEmpty() {
		return(confirmedPswdtextbox.getAttribute("value").trim().isEmpty());
	}
	
	

	public String getMismatchMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Refresh the reference to avoid stale element
		WebElement freshElement = wait
				.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(mismatchpassword)));
		return freshElement.getText().trim();
	}
	
	

	/*public String getSuccessMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-primary")));
		return successMessage.getText().trim();
	}*/
	
	public String getAlertMessage() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement alert = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-primary"))
	    );
	    return alert.getText().trim();
	}


	
	public String getUsernameValidationMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(usernametextbox));
		String Uservalidationmessage = usernametextbox.getAttribute("validationMessage");
		return Uservalidationmessage;
	}

	public String getPasswordValidationMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(passwordtextbox));
		String validationMessage = passwordtextbox.getAttribute("validationMessage");
		return validationMessage;
	}
	
	public String getConfirmPasswordValidationMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(confirmedPswdtextbox));
		String Uservalidationmessage = confirmedPswdtextbox.getAttribute("validationMessage");
		return Uservalidationmessage;
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

	public String getDataStructureLblText() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(dataStructuresDropdown));
		return dataStructuresDropdown.getText();
	}

	public String getNumpyNinjaLinkText() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(numpyNinjaLink));
		return numpyNinjaLink.getText();
	}
	
	
}
