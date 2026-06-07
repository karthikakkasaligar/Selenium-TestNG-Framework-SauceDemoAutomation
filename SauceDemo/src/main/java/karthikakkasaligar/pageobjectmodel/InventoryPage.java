package karthikakkasaligar.pageobjectmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import karthikakkasaligar.ReUseableComponents.ReUseableComponents;

public class InventoryPage extends ReUseableComponents {

	WebDriver driver;

	public InventoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".inventory_item")
	List<WebElement> products;
	
	@FindBy(css = ".inventory_item_name")
	WebElement inventoryname;

	

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

	By addtocartbutton = By.cssSelector(".btn_inventory");

	@FindBy(className = "shopping_cart_badge")
	WebElement cartnumber;

	@FindBy(id = "shopping_cart_container")
	WebElement carticon;

	@FindBy(css = ".shopping_cart_badge")
	WebElement CartBadge;

	@FindBy(css = ".shopping_cart_badge")
	List<WebElement> cartbadges;
	
	@FindBy(css=".product_sort_container")
	WebElement filter;

	By removecta = By.xpath("//button[text()='Remove']");
	
	By allproducts = By.cssSelector(".inventory_item");

	By productname = By.cssSelector(".inventory_item_name");
	
	By Description = By.cssSelector(".inventory_item_desc");
	
	By addtocartcta = By.tagName("button");

	
	public void verifypricehightolow() {
		filter.click();
		Select s=new Select(filter);
		s.selectByIndex(3);
		for(int i=0;i<productprice.size()-1;i++){
			Double currentprice=Double.parseDouble(productprice.get(i).getText().trim().replace("$", ""));
			Double nextprice=Double.parseDouble(productprice.get(i+1).getText().trim().replace("$", ""));
			Assert.assertTrue(currentprice>=nextprice, "Prices are not sorted in High to Low");
		}}
			
	public void verifypricelowtohigh() {
		filter.click();
		Select s=new Select(filter);
		s.selectByIndex(2);
		for(int i=0; i<productprice.size()-1;i++) {
			Double currentprice=Double.parseDouble(productprice.get(i).getText().trim().replace("$", ""));
			Double nextprice=Double.parseDouble(productprice.get(i+1).getText().trim().replace("$", ""));
			Assert.assertTrue(currentprice<=nextprice, "Price are not sorted from low to high");
		}}
		
	
	public void verifyZtoAsorting() {
		filter.click();
		Select s=new Select(filter);
		s.selectByIndex(1);
		for(int i=0; i<products.size()-1;i++){
			String cuurentproduct=products.get(i).getText().trim();
			String nextproduct=products.get(i+1).getText().trim();
			Assert.assertTrue(cuurentproduct.compareToIgnoreCase(nextproduct)>=0, "Products are not Sorted in Z->A Order");
		}}
	
	public void verifyAtoZsorting(){
		for(int i=0; i<products.size()-1;i++){
			String currentproduct=products.get(i).getText().trim();
			String nextproduct=products.get(i+1).getText().trim();
			Assert.assertTrue(currentproduct.compareToIgnoreCase(nextproduct)<=0, "Products are not sorted in A->Z Order");
		}}
	
	public ProductDetailsPage clickonproduct()
	{
		inventoryname.click();
		ProductDetailsPage ProductDetails = new ProductDetailsPage(driver);
		return ProductDetails;
	}
	
	public void verifyallProductDisplay() {
		waituntilvisibilityOfAllElementsLocatedBy(allproducts);
		Assert.assertFalse(products.isEmpty(), "No Products Found");
		for (WebElement product : products) {
			Assert.assertTrue(product.isDisplayed(), "Products are not displayed");
		}
	}

	public void verifyProductPageLoad() {
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
	}

		
	public void verifyproductprices(String[] expectedprices) {
		waituntilvisibilityOfAllElementsLocatedBy(allprices);
		for (int i = 0; i < productprice.size(); i++) {
			String actualprice = productprice.get(i).getText();
			Assert.assertEquals(actualprice, expectedprices[i], "price mismatch at " + i);

		}}
	

	public void verifysingleproductaddedtocart(String expectedProductname) {
		for (int i = 0; i < productnames.size(); i++) {
			String actualproductname = productnames.get(i).getText();
			if (actualproductname.equalsIgnoreCase(expectedProductname)) {
				addtocart.get(i).click();
				break;
			}}}
	
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
		}}
	
	public void verifyaddmutipleproducts(String[] productslist) {
		List<String> items = new ArrayList<String>(Arrays.asList(productslist));
		for (WebElement product : products) {
			String ActualProducts = product.findElement(productname).getText().trim();
			if (items.contains(ActualProducts)) {
				product.findElement(addtocartcta).click();
			}}
		
	}

	public void verifycartitems(String[] productslist) {
		List<String> items = new ArrayList<String>(Arrays.asList(productslist));
		carticon.click();
		for (WebElement cartitem : productnames) {
			String itemname = cartitem.getText().trim();
			Assert.assertTrue(items.contains(itemname), itemname + " is not present in the list");
		}}

	public void addallproductstocart(String[] productslist) {
		int itemsaddedtocart = 0;
		List<String> itemlist = new ArrayList<String>(Arrays.asList(productslist));
		Assert.assertFalse(products.isEmpty(), "No Products Found");
		for (WebElement product : products) {
			String Actualnames = product.findElement(productname).getText().trim();
			if (itemlist.contains(Actualnames)) {
				product.findElement(addtocartbutton).click();
				itemsaddedtocart++;
			}}
		Assert.assertEquals(itemlist.size(), itemsaddedtocart);
		
	}


	public void Addsingleproductandremove(String producttobeadded) {
		for (WebElement product : products) {
			String ActualProductname = product.findElement(productname).getText().trim();
			if (producttobeadded.equalsIgnoreCase(ActualProductname)) {
				product.findElement(addtocartcta).click();
				break;
			}}
	
	}

	public void verifyCartBadgeDisappearsWhenEmpty() {
		addtocartButton.click();
		Assert.assertTrue(cartbadges.size() > 0, "Shopping Badge not Displayed after adding product to cart");
		addtocartButton.click();
		Assert.assertTrue(cartbadges.isEmpty(), "Shopping Badge Displayed after removing product from cart");
	}
	
	public String[] ProductDetails() {

		String name = "";
		String description = "";
		String price = "";
		
		for (WebElement item : products) {
			name = item.findElement(productname).getText().trim();
			description = item.findElement(Description).getText().trim();
			price = item.findElement(allprices).getText().trim();
			if (name.equalsIgnoreCase("Sauce Labs Bolt T-Shirt")) {
				item.findElement(productname).click();
				break;
			}}
		
		return new String[] { name, description, price };
	}

}
