package karthikakkasaligar.TestCases;

import org.testng.annotations.Test;

import karthikakkasaligar.TestComponents.BaseTest;
import karthikakkasaligar.pageobjectmodel.InventoryPage;
import karthikakkasaligar.pageobjectmodel.ProductDetailsPage;

public class ProductDetails_Validaions extends BaseTest {

	@Test
	public void verifyProductDetailsConsistencyBetweenPages() {

		InventoryPage Inventory = login.login(login.getusername(), login.getpassword());
		String[] details = Inventory.ProductDetails();
		ProductDetailsPage ProductDetails = new ProductDetailsPage(driver);
		ProductDetails.Detailedproductdetails(details[0],details[1],details[2]);		
	}
	
	@Test
	public void verifyBackToProductsButtonFunctionality()
	{
		InventoryPage Inventory = login.login(login.getusername(), login.getpassword());
		ProductDetailsPage ProductDetails =Inventory.clickonproduct();  
		ProductDetails.verifybacktoproductbutton();
		Inventory.verifyProductPageLoad();
	}
	
	
}