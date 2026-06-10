package karthikakkasaligar.TestCases;

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
}
