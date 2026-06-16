package karthikakkasaligar.pageobjectmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import karthikakkasaligar.ReUseableComponents.ReUseableComponents;

public class LoginPage extends ReUseableComponents {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "login_credentials")
	WebElement usernames;

	@FindBy(className = "login_password")
	WebElement passwords;

	@FindBy(id = "user-name")
	WebElement usernameTxt;

	@FindBy(id = "password")
	WebElement passwordTxt;

	@FindBy(id = "login-button")
	WebElement submitCTA;

	@FindBy(css = ".error-message-container.error")
	WebElement errorMessageTxt;

	@FindBy(css = ".error-button")
	WebElement errorclosecta;

	By errorclosebutton = By.cssSelector(".error-button");
	
	By waitforerrormsg = By.cssSelector(".error-message-container.error");


	public void goToURL() {
		driver.get("https://www.saucedemo.com/");
	}

	public String getusername() {
		return usernames.getText().split("\n")[1];

	}

	public String getpassword() {
		return passwords.getText().split("\n")[1];

	}

	public String getlockeduser() {
		return usernames.getText().split("\n")[2];
	}

	public String getwrongpassword() {
		String wrongpassword = "wronggpassword";
		return wrongpassword;
	}

	public String getwrongusername() {
		String wrongusername = "wronggusername";
		return wrongusername;
	}

	public String getErrorMessage() {
		return errorMessageTxt.getText();
	}

	@SuppressWarnings("deprecation")
	public String getpasswordattribute() {
		return passwordTxt.getAttribute("type");
	}

	public InventoryPage login(String Validusername, String Password) {
		usernameTxt.sendKeys(Validusername);
		passwordTxt.sendKeys(Password);
		submitCTA.click();
		InventoryPage inventory = new InventoryPage(driver);
		return inventory;
	}

	public void wrongPasswordLogin(String Validusername, String WrongPassword) {
		usernameTxt.sendKeys(Validusername);
		passwordTxt.sendKeys(WrongPassword);
		submitCTA.click();
		waitforvisibilityofwebelement(waitforerrormsg);
	}

	public void wrongUsernameLogin(String WrongUsername, String Password) {
		usernameTxt.sendKeys(WrongUsername);
		passwordTxt.sendKeys(Password);
		submitCTA.click();
		waitforvisibilityofwebelement(waitforerrormsg);
	}

	public void EmptyUserName(String Password) {
		passwordTxt.sendKeys(Password);
		submitCTA.click();
		waitforvisibilityofwebelement(waitforerrormsg);
	}

	public void EmptyPassword(String Validusername) {
		usernameTxt.sendKeys(Validusername);
		submitCTA.click();
		waitforvisibilityofwebelement(waitforerrormsg);
	}

	public void lockeduserlogin(String LockedUser, String Password) {
		usernameTxt.sendKeys(LockedUser);
		passwordTxt.sendKeys(Password);
		submitCTA.click();
		waitforvisibilityofwebelement(waitforerrormsg);
	}

	public InventoryPage LoginUsingEnterkey(String Validusername, String Password) {

		usernameTxt.sendKeys(Validusername);
		passwordTxt.sendKeys(Password);
		submitCTA.sendKeys(Keys.ENTER);
		InventoryPage inventory = new InventoryPage(driver);
		return inventory;
	}

	public void verifyerrormessageclosbutton() {
		submitCTA.click();
		waitforelementtobeclickable(errorclosebutton);
		errorclosecta.click();
	}

	public void VerifyblankusernameandpassworD() {
		submitCTA.click();
	}

}
