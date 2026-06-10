package karthikakkasaligar.ReUseableComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import karthikakkasaligar.pageobjectmodel.CartPage;

public class ReUseableComponents {

	WebDriver driver;
	public ReUseableComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "shopping_cart_container")
	WebElement carticon;

	public void waitforvisibilityofwebelement(By visibilityofelement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(visibilityofelement));
	}

	public void waituntilvisibilityOfAllElementsLocatedBy(By visibilityofallelements) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(visibilityofallelements));
	}

	public void waitforelementtobeclickable(By elementtobeclickable) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(elementtobeclickable));
	}

	public CartPage clickcarticon() {
		carticon.click();
		CartPage cart= new CartPage(driver);
		return cart;
		
	}
	
	

}
