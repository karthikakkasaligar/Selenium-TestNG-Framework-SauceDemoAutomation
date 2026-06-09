package karthikakkasaligar.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import karthikakkasaligar.TestComponents.BaseTest;
import karthikakkasaligar.pageobjectmodel.InventoryPage;
import karthikakkasaligar.pageobjectmodel.ProductDetailsPage;

public class InventoryPageValidations extends BaseTest {

	@Test
	public void VerifyallProductsdisplay() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifyallProductDisplay();
	}

	@Test(groups = { "ProductVerification"})
	public void Verifyproductnamedisplayed() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifyproductnamedisplay();
	}

	@Test(groups = "ProductVerification")
	public void VerifyProductPricescorrectly() {
		String expectedprices[] = { "$29.99", "$9.99", "$15.99", "$49.99", "$7.99", "$15.99" };
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifyproductprices(expectedprices);
	}

	@Test(groups = "ProductVerification")
	public void verifyproductimagedisplayed() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifyproductimagedisplay();
	}

	@Test
	public void VerifyAddtoCartBuutonDisplaY() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		Assert.assertFalse(inventory.AddtoCartbuutonslist().isEmpty(), "No Add to cart Buutons found");
		inventory.verifyaddtocartbutton();
	}

	@Test
	public void AddSingleProducttocart() {

		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifysingleproductaddedtocart();
		inventory.verifycartnumber();
	}

	@Test
	public void AddMultipleProducttoCart() throws InterruptedException {

		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifyaddmutipleproducts();
	}

	@Test
	public void Addallproductstocart() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.addallproductstocart();
	}

	@Test
	public void VerifyAddandremoveproduct() {
		String producttobeadded = "Sauce Labs Bolt T-Shirt";
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.Addandremoveproduct(producttobeadded);
	}

	@Test
	public void removeAllProductsFromInventory() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.addallproductstocart();
		inventory.removeallproductstocart();
	}

	@Test
	public void AddRemoveAddSameProduct() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.AddRemoveAddSameProduct();
	}

	@Test
	public void verifyCartBadgeDisappearsWhenEmpty() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifyCartBadgeDisappearsWhenEmpty();
	}

	@Test
	public void verifyopenProductDetailsPage() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		ProductDetailsPage ProductDetails = inventory.clickonproduct();
		ProductDetails.verifyproductdetailspageurl();
	}

	@Test
	public void verifyProductDetailsMatchInventoryPage() {

		InventoryPage Inventory = login.login(login.getusername(), login.getpassword());
		String[] details = Inventory.ProductDetails();
		ProductDetailsPage ProductDetails = new ProductDetailsPage(driver);
		ProductDetails.Detailedproductdetails(details[0], details[1], details[2]);
	}

	@Test
	public void verifyBackToProductsButtonFunctionality() {
		InventoryPage Inventory = login.login(login.getusername(), login.getpassword());
		ProductDetailsPage ProductDetails = Inventory.clickonproduct();
		ProductDetails.verifybacktoproductbutton();
		Inventory.verifyProductPageLoad();
	}

}
