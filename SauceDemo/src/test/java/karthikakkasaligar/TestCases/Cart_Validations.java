package karthikakkasaligar.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import karthikakkasaligar.TestComponents.BaseTest;
import karthikakkasaligar.pageobjectmodel.CartPage;
import karthikakkasaligar.pageobjectmodel.inventoryPage;

public class Cart_Validations extends BaseTest {

	@Test
	public void VerifyAddtoCartBuutonDisplaY() {
		inventoryPage inventory = login.login(login.getusername(), login.getpassword());
		Assert.assertFalse(inventory.AddtoCartbuutonslist().isEmpty(), "No Add to cart Buutons found");
		inventory.verifyaddtocartbutton();
	}

	@Test
	public void AddSingleProducttocart() {
		String expectedProductname = "Sauce Labs Fleece Jacket";
		inventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifysingleproductaddedtocart(expectedProductname);
		inventory.verifycartnumber();
	}

	@Test
	public void AddMultipleProducttoCart() throws InterruptedException {
		String[] productslist = { "Sauce Labs Fleece Jacket", "Sauce Labs Onesie" };
		inventoryPage inventory = login.login(login.getusername(), login.getpassword());
		CartPage cart = inventory.verifyaddmutipleproductstocart(productslist);
		cart.verifycartitems(productslist);
	}

	@Test
	public void Addallproductstocart() {
		String[] productslist = { "Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie",
				"Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Test.allTheThings() T-Shirt (Red)" };
		inventoryPage inventory = login.login(login.getusername(), login.getpassword());
		int productsaddtocart = inventory.addallproductstocart(productslist);
		CartPage cart = new CartPage(driver);
		cart.verifycartitems(productslist, productsaddtocart);
	}

	@Test
	public void Addsingleproductandremovefromcart() {
		String producttobeadded = "Sauce Labs Bolt T-Shirt";
		inventoryPage inventory = login.login(login.getusername(), login.getpassword());
		CartPage cart = inventory.Addsingleproductandremovefromcart(producttobeadded);
		cart.Removeproductfromcart(producttobeadded);
	}

}
