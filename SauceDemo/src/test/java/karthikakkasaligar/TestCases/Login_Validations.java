package karthikakkasaligar.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import karthikakkasaligar.TestComponents.BaseTest;
import karthikakkasaligar.pageobjectmodel.InventoryPage;

public class Login_Validations extends BaseTest {

	@Test
	public void loginValidation() throws IOException {
		login.login(login.getusername(), login.getpassword());
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login Failed");
	}

	@Test
	public void Blankpassword() {
		login.EmptyPassword(login.getusername());
		Assert.assertEquals(login.getErrorMessage(), "Epic sadface: Password is required");
	}

	@Test
	public void BlankUserName() {
		login.EmptyUserName(login.getpassword());
		Assert.assertEquals(login.getErrorMessage(), "Epic sadface: Username is required");
	}

	@Test
	public void LockedUserlogin() {
		login.lockeduserlogin(login.getlockeduser(), login.getpassword());
		Assert.assertEquals(login.getErrorMessage(), "Epic sadface: Sorry, this user has been locked out.");
	}

	@Test
	public void verifyLoginUsingEnterkey() {
		InventoryPage inventory = login.LoginUsingEnterkey(login.getusername(), login.getpassword());
		inventory.verifyProductPageLoad();
	}

	@Test
	public void verifyPasswordMasking() {
		String Attribute = login.getpasswordattribute();
		Assert.assertEquals(Attribute, "password", "Password is not masked");
	}

	@Test
	public void wrongPasswordValidation() throws IOException {
		login.wrongPasswordLogin(login.getusername(), login.getwrongpassword());
		Assert.assertEquals(login.getErrorMessage(),
				"Epic sadface: Username and password do not match any user in this service");
	}

	@Test
	public void WrongUsernamevalidation() throws IOException {
		login.wrongUsernameLogin(login.getwrongusername(), login.getpassword());
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

}
