package karthikakkasaligar.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import karthikakkasaligar.ReUseableComponents.ReUseableComponents;

public class CheckoutInformationPage extends ReUseableComponents {

	WebDriver driver;

	public CheckoutInformationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "first-name")
	WebElement FirstName;

	@FindBy(id = "last-name")
	WebElement Lastname;

	@FindBy(id = "postal-code")
	WebElement Postalcode;

	@FindBy(id = "continue")
	WebElement ContinueCTA;

	@FindBy(css = ".error-message-container.error")
	WebElement errorMessage;

	public void Checkoutwithvaliddetails(String Name, String LastName, String PostalCode) {
		FirstName.sendKeys(Name);
		Lastname.sendKeys(LastName);
		Postalcode.sendKeys(PostalCode);
		ContinueCTA.click();
		Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two"), "Continue CTA is not functional");
	}

	public void checkoutwithblankfirstname(String Name, String LastName, String PostalCode) {
		FirstName.sendKeys(Name);
		Lastname.sendKeys(LastName);
		Postalcode.sendKeys(PostalCode);
		ContinueCTA.click();
		String ErrorMessage = errorMessage.getText().trim();
		Assert.assertEquals(ErrorMessage, "Error: First Name is required");
	}
	
	public void checkoutwithblanklastname(String Name, String LastName, String PostalCode) {
		FirstName.sendKeys(Name);
		Lastname.sendKeys(LastName);
		Postalcode.sendKeys(PostalCode);
		ContinueCTA.click();
		String ErrorMessage = errorMessage.getText().trim();
		Assert.assertEquals(ErrorMessage, "Error: Last Name is required");
	}
	
	public void checkoutwithblankpostalcode(String Name, String LastName, String PostalCode) {
		FirstName.sendKeys(Name);
		Lastname.sendKeys(LastName);
		Postalcode.sendKeys(PostalCode);
		ContinueCTA.click();
		String ErrorMessage = errorMessage.getText().trim();
		Assert.assertEquals(ErrorMessage, "Error: Postal Code is required");
	}
	
	
	
	
}
