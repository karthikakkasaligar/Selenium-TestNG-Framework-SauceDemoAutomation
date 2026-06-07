package karthikakkasaligar.pageobjectmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import karthikakkasaligar.ReUseableComponents.ReUseableComponents;

public class CartPage extends ReUseableComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cart_list")
	List<WebElement> cartitems;

	@FindBy(css = ".inventory_item_name")
	List<WebElement> productnames;

	@FindBy(css = ".cart_item")
	List<WebElement> itemincart;

	By productname = By.cssSelector(".inventory_item_name");

	By addtocartcta = By.tagName("button");

	public void verifycartitems(String[] productslist) {
		List<String> items = new ArrayList<String>(Arrays.asList(productslist));
		for (WebElement cartitem : productnames) {
			String itemname = cartitem.getText().trim();
			Assert.assertTrue(items.contains(itemname), itemname + " is not present in the list");
		}
	}

	public void verifycartitems(String[] productslist, int productsaddtocart) {
		Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"), " its not cart page");
		Assert.assertFalse(cartitems.isEmpty(), "No Items Found in Cart");
		Assert.assertEquals(productslist.length, productsaddtocart);
		List<String> cartitemslist = new ArrayList<String>(Arrays.asList(productslist));
		for (WebElement cartitem : cartitems) {
			String actualcartitem = cartitem.findElement(productname).getText().trim();
			Assert.assertTrue(cartitemslist.contains(actualcartitem), cartitemslist + " is not Present in List");
		}
	}

	

}
