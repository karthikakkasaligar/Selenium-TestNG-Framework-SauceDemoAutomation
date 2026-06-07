package karthikakkasaligar.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import karthikakkasaligar.TestComponents.BaseTest;
import karthikakkasaligar.pageobjectmodel.InventoryPage;
import karthikakkasaligar.pageobjectmodel.ProductDetailsPage;

public class Product_Validations extends BaseTest {

	@Test
	public void sortProductsNameAtoZ() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifyAtoZsorting();
	}
	
	@Test
	public void sortProductsNameZtoA() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifyZtoAsorting();
	}

	@Test
	public void verifyProductDetailsPageOpens() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		ProductDetailsPage ProductDetails = inventory.clickonproduct();
		ProductDetails.verifyproductdetailspageurl();
	}

	@Test
	public void VerifyProductDetails() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.VerifyProductdetails();
	}

	@Test
	public void VerifyProductdisplay() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifyProductDisplay();
	}

	@Test
	public void VerifyProductPageload() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifyProductPageLoad();
	}

	@Test
	public void VerifyProductPricescorrectly() {
		String expectedprices[] = { "$29.99", "$9.99", "$15.99", "$49.99", "$7.99", "$15.99" };
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifyproductprices(expectedprices);
	}

	@Test
	public void RemoveProduct() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "This is Not Inventory Page!!");
		inventory.removalofproductfromcart();

	}

}
