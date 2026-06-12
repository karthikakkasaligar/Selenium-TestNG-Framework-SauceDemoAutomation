package karthikakkasaligar.TestCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import karthikakkasaligar.TestComponents.BaseTest;
import karthikakkasaligar.pageobjectmodel.CartPage;
import karthikakkasaligar.pageobjectmodel.InventoryPage;

public class CartValidations extends BaseTest {

	@Test
	public void verifyAddedProductsDisplayedInCart() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		String expectedProductname = inventory.verifysingleproductaddedtocart();
		CartPage cart = inventory.clickcarticon();
		cart.verifyAddedProductsDisplayedInCart(expectedProductname);
	}

	@Test
	public void verifyProductNamesInCart() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		String expectedProductname = inventory.verifysingleproductaddedtocart();
		CartPage cart = inventory.clickcarticon();
		cart.VerifyProductnameincart(expectedProductname);
	}

	@Test
	public void verifyProductPricesInCart() {
		String expectedProductname = "Sauce Labs Fleece Jacket";
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		double itemprice = inventory.verifyproductpriceinventorypage(expectedProductname);
		CartPage cart = inventory.clickcarticon();
		double cartitemprice = cart.verifycartitemprice(expectedProductname);
		Assert.assertEquals(itemprice, cartitemprice);
	}

	@Test
	public void removeOneProductFromCart() {
		String expectedProductname = "Sauce Labs Fleece Jacket";
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.addsingleproducttocart(expectedProductname);
		CartPage cart = inventory.clickcarticon();
		cart.removesingleaddedproductincart(expectedProductname);
	}

	@Test
	public void removeallproductsfromcart() {
		String[] productslist = { "Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie",
				"Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Test.allTheThings() T-Shirt (Red)" };
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.addallproductstocart();
		CartPage cart = inventory.clickcarticon();
		cart.removeallproductsfromcart(productslist);
	}

	@Test
	public void VerifyContinueShoppingbutton() {
		String expectedProductname = "Sauce Labs Fleece Jacket";
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.addsingleproducttocart(expectedProductname);
		CartPage cart = inventory.clickcarticon();
		cart.verifyclickoncontinue();
	}

	@Test
	public void VerifyCheckoutbutton() {
		String expectedProductname = "Sauce Labs Fleece Jacket";
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.addsingleproducttocart(expectedProductname);
		CartPage cart = inventory.clickcarticon();
		cart.verifycheckoutbutton();
	}
}
