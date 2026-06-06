package karthikakkasaligar.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import karthikakkasaligar.TestComponents.BaseTest;
import karthikakkasaligar.pageobjectmodel.inventoryPage;

public class Product_Validations extends BaseTest {

	@Test
	public void VerifyProductDetails() {
		inventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.VerifyProductdetails();
	}

	@Test
	public void VerifyProductdisplay() {
		inventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifyProductDisplay();
	}

	@Test
	public void VerifyProductPageload() {
		inventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifyProductPageLoad();
	}

	@Test
	public void VerifyProductPricescorrectly() {
		String expectedprices[] = { "$29.99", "$9.99", "$15.99", "$49.99", "$7.99", "$15.99" };
		inventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifyproductprices(expectedprices);
	}

	@Test
	public void RemoveProduct() {
		inventoryPage inventory = login.login(login.getusername(), login.getpassword());
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "This is Not Inventory Page!!");
		inventory.removalofproductfromcart();

	}

}
