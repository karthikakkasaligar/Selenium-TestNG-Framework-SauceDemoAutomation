package karthikakkasaligar.pageobjectmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import karthikakkasaligar.ReUseableComponents.ReUseableComponents;

public class ProductDetailsPage extends ReUseableComponents {

	WebDriver driver;
	public ProductDetailsPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".inventory_details_desc_container")
	WebElement ProductDetails;
	
	@FindBy(id="back-to-products")
	WebElement Back2productscta;
	
	By productname = By.cssSelector("[data-test='inventory-item-name']");
	
	By Description = By.cssSelector("[data-test='inventory-item-desc']");
	
	By Price = By.cssSelector("[data-test='inventory-item-price']");
	
	public void verifyproductdetailspageurl() {
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory-item"), "Products details pag is not open!!");
	}
	
	 public void Detailedproductdetails(String expectedName,String expectedDescription,String expectedPrice) {
      Assert.assertTrue(driver.getCurrentUrl().contains("inventory-item"),"Products details page is not open!!");
      Assert.assertEquals(ProductDetails.findElement(productname).getText().trim(), expectedName, "Product name mismatch");
      Assert.assertEquals(ProductDetails.findElement(Description).getText().trim(), expectedDescription, "Product description mismatch");
      Assert.assertEquals(ProductDetails.findElement(Price).getText().trim(), expectedPrice, "Product price mismatch");
     }

	 
	 public void verifybacktoproductbutton() {
		    Assert.assertTrue(driver.getCurrentUrl().contains("inventory-item.html"), "This is Not Product Detais Page!!");
			Back2productscta.click();
	 }
	 
	 
	 
	 
}   
