package karthikakkasaligar.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import karthikakkasaligar.Data.DataReader;
import karthikakkasaligar.TestComponents.BaseTest;
import karthikakkasaligar.pageobjectmodel.InventoryPage;

public class LoginValidations extends BaseTest {

	@Test(dataProvider = "getdata")
	public void loginValidation(HashMap<String, String> input) throws IOException {
		login.login(input.get("Validusername"), input.get("Password"));
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login Failed");
	}

	@Test(dataProvider = "getdata")
	public void Blankpassword(HashMap<String, String> input) {
		login.EmptyPassword(input.get("Validusername"));
		Assert.assertEquals(login.getErrorMessage(), "Epic sadface: Password is required");
	}

	@Test(dataProvider = "getdata")
	public void BlankUserNam(HashMap<String, String> input) {
		login.EmptyUserName(input.get("Password"));
		Assert.assertEquals(login.getErrorMessage(), "Epic sadface: Username is required");
	}

	@Test(dataProvider = "getdata")
	public void LockedUserlogin(HashMap<String, String> input) {
		login.lockeduserlogin(input.get("LockedUser"), input.get("Password"));
		Assert.assertEquals(login.getErrorMessage(), "Epic sadface: Sorry, this user has been locked out.");
	}

	@Test(dataProvider = "getdata")
	public void verifyLoginUsingEnterkey(HashMap<String, String> input) {
		InventoryPage inventory = login.LoginUsingEnterkey(input.get("Validusername"), input.get("Password"));
		inventory.verifyProductPageLoad();
	}

	@Test
	public void verifyPasswordMasking() {
		String Attribute = login.getpasswordattribute();
		Assert.assertEquals(Attribute, "password", "Password is not masked");
	}

	@Test(dataProvider = "getdata")
	public void wrongPasswordValidation(HashMap<String, String> input) throws IOException {
		login.wrongPasswordLogin(input.get("Validusername"), input.get("WrongPassword"));
		Assert.assertEquals(login.getErrorMessage(),
				"Epic sadface: Username and password do not match any user in this service");
	}

	@Test(dataProvider = "getdata")
	public void WrongUsernamevalidation(HashMap<String, String> input) throws IOException {
		login.wrongUsernameLogin(input.get("WrongUsername"), input.get("Password"));
		Assert.assertEquals(login.getErrorMessage(),
				"Epic sadface: Username and password do not match any user in this service");
	}

	@Test
	public void Errormessageclosebutton() {
		login.verifyerrormessageclosbutton();
	}

	@Test
	public void Verifyblankusernameandpassword() {
		login.VerifyblankusernameandpassworD();
		Assert.assertEquals(login.getErrorMessage(), "Epic sadface: Username is required");
	}

	@DataProvider
	public Object[][] getdata() throws IOException {
		DataReader datareader = new DataReader();
		List<HashMap<String, String>> data = datareader.getJsondataToHashmap(System.getProperty("user.dir") + "\\src\\test\\java\\karthikakkasaligar\\Data\\LoginValidations.json");
		return new Object[][] { { data.get(0) } };
	}

}
