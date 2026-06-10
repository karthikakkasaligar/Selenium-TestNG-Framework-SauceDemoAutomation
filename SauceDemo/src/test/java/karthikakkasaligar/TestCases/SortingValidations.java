package karthikakkasaligar.TestCases;

import org.testng.annotations.Test;

import karthikakkasaligar.TestComponents.BaseTest;
import karthikakkasaligar.pageobjectmodel.InventoryPage;

public class SortingValidations extends BaseTest {

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
	public void sortProductsPriceHightoLow() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifypricehightolow();

	}

	@Test
	public void sortProductsPriceLowToHigh() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifypricelowtohigh();
	}
	
	@Test
	public void verifydefaultSortingAfterPageRefresh() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.deafultsortingafterrefresh();
	}
	
	@Test
	public void Verifysortdropdownvalues() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifydropdownvalues();	
	}
	
	@Test
	public void Verifydefaultsortingoption() {
		InventoryPage inventory = login.login(login.getusername(), login.getpassword());
		inventory.verifydefaultfilter();
	}

}
