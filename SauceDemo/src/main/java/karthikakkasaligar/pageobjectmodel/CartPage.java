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

	@FindBy(css = ".cart_quantity")
	List<WebElement> cartquantity;

	@FindBy(css = ".inventory_item_name")
	List<WebElement> productnames;

	@FindBy(css = ".cart_item")
	List<WebElement> itemincart;

	@FindBy(css = ".shopping_cart_badge")
	List<WebElement> Badgecount;
	
	@FindBy(css = ".shopping_cart_badge")
	WebElement badgecount;

	@FindBy(css = ".inventory_item_name")
	WebElement Productname;

	@FindBy(id = "continue-shopping")
	WebElement continueshopping;

	@FindBy(id = "checkout")
	WebElement checkoutcta;

	By productname = By.cssSelector(".inventory_item_name");

	By addtocartcta = By.tagName("button");

	By PPrice = By.cssSelector(".inventory_item_price");

	By Cartquantity = By.cssSelector(".cart_quantity");

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

	public void verifyAddedProductsDisplayedInCart(String product) {
		Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"), " its not cart page");
		Assert.assertTrue(Productname.isDisplayed(), "Added Product is not same as product in Cart");
	}

	public void VerifyProductnameincart(String expectedProductname) {
		Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"), " its not cart page");
		Assert.assertEquals(Productname.getText().trim(), expectedProductname);
	}

	public Double verifycartitemprice(String expectedProductname) {
		Double cartPrice = null;
		for (WebElement cartitem : itemincart) {
			String cartitemname = cartitem.findElement(productname).getText().trim();
			if (cartitemname.equals(expectedProductname)) {
				String price = cartitem.findElement(PPrice).getText().trim().replace("$", "");
				cartPrice = Double.parseDouble(price);
				break;
			}
		}
		return cartPrice;
	}

	public void removesingleaddedproductincart(String expectedProductname) {
		Assert.assertEquals(itemincart.size(), 1);
		for (WebElement item : itemincart) {
			String name = item.findElement(productname).getText().trim();
			Assert.assertEquals(expectedProductname, name);
			item.findElement(addtocartcta).click();
			break;
		}
		Assert.assertTrue(Badgecount.isEmpty(), "Item is Not Removed from Cart");
	}

	public void removeallproductsfromcart(String[] productslist) {
		int productsremoved = 0;
		List<String> items = new ArrayList<String>(Arrays.asList(productslist));
		Assert.assertEquals(itemincart.size(), 6);
		for (WebElement cartitem : itemincart) {
			String cartitemname = cartitem.findElement(productname).getText().trim();
			if (items.contains(cartitemname)) {
				cartitem.findElement(addtocartcta).click();
				productsremoved--;
				if (productsremoved == 0) {
					break;
				}

			}
		}
		Assert.assertTrue(Badgecount.isEmpty(), "all products are not removed");
	}

	public void verifyclickoncontinue() {
		continueshopping.click();
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
				"continue-shopping button is not functional");
	}

	public void verifycheckoutbutton() {
		checkoutcta.click();
		Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step"), "checkout button is not working");
	}

	public void refreshcartitem(String expectedProductname) {

		getcartitem(expectedProductname);
		driver.navigate().refresh();
		getcartitem(expectedProductname);
	}

	public void getcartitem(String expectedProductname) {
		for (WebElement cartitem : itemincart) {
			String name = cartitem.findElement(By.cssSelector(".inventory_item_name")).getText().trim();
			Assert.assertEquals(expectedProductname, name);
		}
	}

	public void Verifyquantitydisplayed(String[] productslist) {
		List<String> items = new ArrayList<String>(Arrays.asList(productslist));
		for (WebElement cartitem : itemincart) {
			String name = cartitem.findElement(productname).getText().trim();
			String quantity = cartitem.findElement(Cartquantity).getText().trim();
			Assert.assertTrue(items.contains(name), "Unexpected Product Found" + name);
			Assert.assertEquals(quantity, "1", "Product count Mismatch" + quantity);
		}

	}
	
	public void verifycartcount() {
		String cartcount=badgecount.getText().trim();
	    Integer Badgecount=Integer.parseInt(cartcount);
		List<WebElement> cartitems=driver.findElements(By.cssSelector(".cart_item"));
		Assert.assertEquals(cartitems.size(),Badgecount);
	}

}
