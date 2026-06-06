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

public class inventoryPage extends ReUseableComponents {

	WebDriver driver;

	public inventoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".inventory_item")
	List<WebElement> products;

	By allproducts = By.cssSelector(".inventory_item");

	@FindBy(css = ".inventory_item_name")
	WebElement inventoryname;

	By productname = By.cssSelector(".inventory_item_name");

	By addtocartcta = By.tagName("button");

	@FindBy(css = ".inventory_item_name")
	List<WebElement> productnames;

	@FindBy(css = ".inventory_item_price")
	WebElement Inventoryprice;

	By allprices = By.cssSelector(".inventory_item_price");

	@FindBy(css = ".inventory_item_price")
	List<WebElement> productprice;

	@FindBy(xpath = "//img[@class='inventory_item_img']")
	WebElement InventoryImg;

	@FindBy(css = ".inventory_item_desc")
	WebElement InventoryDescription;

	@FindBy(css = ".btn_inventory")
	List<WebElement> addtocart;
	
	@FindBy(css = ".btn_inventory")
	WebElement addtocartButton;
	
	By addtocartbutton=By.cssSelector(".btn_inventory");

	@FindBy(className = "shopping_cart_badge")
	WebElement cartnumber;

	@FindBy(id = "shopping_cart_container")
	WebElement carticon;
	
	@FindBy(css=".shopping_cart_badge")
	WebElement CartBadge;
	
	By removecta=By.xpath("//button[text()='Remove']");


	public void verifyProductDisplay() {
		waituntilvisibilityOfAllElementsLocatedBy(allproducts);
		Assert.assertFalse(products.isEmpty(), "No Products Found");
		for (WebElement product : products) {
			Assert.assertTrue(product.isDisplayed(), "Products are not displayed");
		}
	}

	public void verifyProductPageLoad() {
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
	}

	public void VerifyProductdetails() {
		waituntilvisibilityOfAllElementsLocatedBy(allproducts);
		for (WebElement product : products) {
			product.click();

			Assert.assertFalse(inventoryname.getText().isEmpty(), "Name not Displayed!!");

			Assert.assertFalse(Inventoryprice.getText().isEmpty(), "price not Displayed!!");

			Assert.assertTrue(InventoryImg.isDisplayed(), "Image not dispalyed" + product);

			Assert.assertFalse(InventoryDescription.getText().isEmpty(), "discription not Displayed!!");
		}
	}

	public void verifyproductprices(String[] expectedprices) {
		waituntilvisibilityOfAllElementsLocatedBy(allprices);

		for (int i = 0; i < productprice.size(); i++) {
			String actualprice = productprice.get(i).getText();
			Assert.assertEquals(actualprice, expectedprices[i], "price mismatch at " + i);
		}
	}

	public void verifysingleproductaddedtocart(String expectedProductname) {
		for (int i = 0; i < productnames.size(); i++) {
			String actualproductname = productnames.get(i).getText();
			if (actualproductname.equalsIgnoreCase(expectedProductname)) {
				addtocart.get(i).click();
				break;
			}
		}
	}

	public void verifycartnumber() {
		int nuofitemadded = Integer.parseInt(cartnumber.getText());
		Assert.assertEquals(nuofitemadded, 1, " cart count is incoorect");
	}

	public List<WebElement> AddtoCartbuutonslist() {
		return addtocart;
	}

	public void verifyaddtocartbutton() {
		for (WebElement button : addtocart) {
			Assert.assertTrue(button.isDisplayed(), "Add to Cart Button Missing");
		}
	}

	public CartPage verifyaddmutipleproductstocart(String[] productslist) {
		List<String> items = new ArrayList<String>(Arrays.asList(productslist));
		for (WebElement product : products) {
			String ActualProducts = product.findElement(productname).getText().trim();
			if (items.contains(ActualProducts)) {
				product.findElement(addtocartcta).click();
			}

		}
		addtocarticon();
		CartPage cart=new CartPage(driver);
		return cart;

	}

	public void verifycartitems(String[] productslist) {
		List<String> items = new ArrayList<String>(Arrays.asList(productslist));
		carticon.click();
		for (WebElement cartitem : productnames) {
			String itemname = cartitem.getText().trim();
			Assert.assertTrue(items.contains(itemname), itemname + " is not present in the list");
		}
	}
	
	public int addallproductstocart(String[] productslist )
	{
		int itemsaddedtocart=0;
		List<String> itemlist=new ArrayList<String>(Arrays.asList(productslist));
		Assert.assertFalse(products.isEmpty(), "No Products Found");
		for(WebElement product : products)
		{
			String Actualnames=product.findElement(productname).getText().trim();
			if(itemlist.contains(Actualnames))
			{
				product.findElement(addtocartbutton).click();
				itemsaddedtocart++;
			}
		}
		Assert.assertEquals(itemlist.size(), itemsaddedtocart);
		addtocarticon();
		return itemsaddedtocart;
	}
	
	public void removalofproductfromcart()
	{
		addtocartButton.click();
		int cartcount=Integer.parseInt(CartBadge.getText().trim());
		Assert.assertEquals(cartcount, 1);
		waitforelementtobeclickable(addtocartbutton);
		Assert.assertEquals(addtocartButton.getText().trim(), "Remove");
		addtocartButton.click();
		Assert.assertEquals(addtocartButton.getText().trim(), "Add to cart");
	}
	
	public CartPage Addsingleproductandremovefromcart(String producttobeadded)
	{
		for (WebElement product : products) {
			String ActualProductname = product.findElement(productname).getText().trim();
			if (producttobeadded.equalsIgnoreCase(ActualProductname)) {
				product.findElement(addtocartcta).click();
				break;
			}
		}
		addtocarticon();
		CartPage cart = new CartPage(driver);
		return cart;
	}
	
	

}
