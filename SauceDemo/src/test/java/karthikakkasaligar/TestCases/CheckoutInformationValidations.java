package karthikakkasaligar.TestCases;

import org.testng.annotations.Test;

import karthikakkasaligar.TestComponents.BaseTest;
import karthikakkasaligar.pageobjectmodel.CartPage;
import karthikakkasaligar.pageobjectmodel.CheckoutInformationPage;
import karthikakkasaligar.pageobjectmodel.InventoryPage;

public class CheckoutInformationValidations extends BaseTest {

	@Test
	public void Checkoutwithvaliddetails() {
		String Name = "Karthik";
		String LastName = "Akkasaligar";
		String PostalCode = "500028";
		String expectedProductname = "Sauce Labs Fleece Jacket";
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.addsingleproducttocart(expectedProductname);
		CartPage cart = inventory.clickcarticon();
		CheckoutInformationPage Checkout = cart.verifycheckoutbutton();
		Checkout.Checkoutwithvaliddetails(Name, LastName, PostalCode);
	}

	@Test
	public void Checkoutwithblankfirstname() {
		String Name = "";
		String LastName = "Akkasaligar";
		String PostalCode = "500028";
		String expectedProductname = "Sauce Labs Fleece Jacket";
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.addsingleproducttocart(expectedProductname);
		CartPage cart = inventory.clickcarticon();
		CheckoutInformationPage Checkout = cart.verifycheckoutbutton();
		Checkout.checkoutwithblankfirstname(Name, LastName, PostalCode);
	}

	@Test
	public void Checkoutwithblanklastname() {
		String Name = "Karthik";
		String LastName = "";
		String PostalCode = "500028";
		String expectedProductname = "Sauce Labs Fleece Jacket";
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.addsingleproducttocart(expectedProductname);
		CartPage cart = inventory.clickcarticon();
		CheckoutInformationPage Checkout = cart.verifycheckoutbutton();
		Checkout.checkoutwithblanklastname(Name, LastName, PostalCode);
	}

	@Test
	public void Checkoutwithblankpostalcode() {
		String Name = "Karthik";
		String LastName = "Akkasaligar";
		String PostalCode = "";
		String expectedProductname = "Sauce Labs Fleece Jacket";
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.addsingleproducttocart(expectedProductname);
		CartPage cart = inventory.clickcarticon();
		CheckoutInformationPage Checkout = cart.verifycheckoutbutton();
		Checkout.checkoutwithblankpostalcode(Name, LastName, PostalCode);
	}
}
