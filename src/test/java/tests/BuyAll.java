package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageobjects.CartPage;
import pageobjects.FinishPage;
import pageobjects.InventoryItemPage;
import pageobjects.InventoryPage;
import pageobjects.LoginPage;
import pageobjects.OverViewPage;
import pageobjects.YourInfoPage;
import utilities.AllureAttachment;

public class BuyAll extends BaseTest {
	InventoryPage ip;
	InventoryItemPage iip;
	CartPage cp;
	YourInfoPage yip;
	OverViewPage ovp;
	FinishPage fp;

	@Owner("Danny Ambaou")
	@Link("https://www.saucedemo.com/")
	@Feature("Login")
	@Severity(SeverityLevel.BLOCKER)
	@Test(dataProvider = "loginDetails", enabled = true, priority = 1)
	@Description("Login using user name and password and get error messages")
	public void tc01_login(String username, String password, String errorMessage) {
		LoginPage lp = new LoginPage(driver);
		lp.login(username, password);
		String expected = errorMessage;
		System.out.println("Expected: " + expected);
		String actual = lp.getErrorMessage();
		Assert.assertEquals(actual, expected);
		driver.navigate().refresh(); // Refresh current page to wipe all data from the fields and get the error message
		AllureAttachment.attachText("login button");
	
	}

	@Feature("Check add button from inventory item page")
	@Severity(SeverityLevel.NORMAL)
	@Test(enabled = true, priority = 2)
	@Description("Add all items to the cart and remove few of them by name")
	public void tc02_addItemsToCartByName() {
		ip = new InventoryPage(driver); 
		Assert.assertTrue(ip.isInventoryPage("PRODUCTS")); 
		iip = new InventoryItemPage(driver);
		ip.chooseAnItem("Sauce Labs Backpack");
		iip.addToCart();
		iip.backToProductsButton();
		ip.chooseAnItem("Sauce Labs Bolt T-Shirt");
		iip.addToCart();
		iip.backToProductsButton();
		ip.chooseAnItem("Sauce Labs Onesie");
		iip.addToCart();
		iip.backToProductsButton();
		ip.chooseAnItem("Sauce Labs Bike Light");
		iip.addToCart();
		iip.backToProductsButton();
		ip.chooseAnItem("Sauce Labs Fleece Jacket");
		iip.addToCart();
		iip.backToProductsButton();
		ip.chooseAnItem("Test.allTheThings() T-Shirt (Red)");
		iip.addToCart();
		iip.openCart();
	}
	
	@Feature("Check remove button from cart page")
	@Test(priority = 3)
	public void tc03_removeItemsFromCartByName() {
		cp = new CartPage(driver);
		String expectedCartPage = "YOUR CART";
		System.out.println("Expected:" + expectedCartPage);
		String actualCartPage = cp.getCartPageTitle();
		Assert.assertTrue(cp.isCartPage(expectedCartPage));
		System.out.println("Actual: " + actualCartPage);
		int numOfItemsBeforeRemoving = cp.getNumOfItems();
		System.out.println("Items before deleted: " + cp.getNumOfItems());
		if (cp.getNumOfItems() <= 6) {
			cp.removeItemsFromCart("Sauce Labs Bike Light");
			cp.removeItemsFromCart("Sauce Labs Fleece Jacket");
			cp.removeItemsFromCart("Sauce Labs Onesie");
			cp.removeItemsFromCart("Test.allTheThings() T-Shirt (Red)");
		} else if (cp.getNumOfItems() == 0)
			System.out.println("Cart is empty!");
		else
			System.out.println("You have more than 6 items in your cart");
		int numOfItemsAfterRemoving = cp.getNumOfItems();
		System.out.println("Items after deleted: " + cp.getNumOfItems());
		Assert.assertNotSame(numOfItemsBeforeRemoving, numOfItemsAfterRemoving);
		cp.checkOut();

		yip = new YourInfoPage(driver);

		String expectedInfoPage = "CHECKOUT: YOUR INFORMATION";
		System.out.println("Expected " + expectedInfoPage);
		String actualInfoPage = yip.getInfoPageTitle();
		System.out.println("Actual " + actualInfoPage);
		Assert.assertTrue(yip.isInfoPgae(expectedInfoPage));
		yip.enterValidDetails("sds", "sdada", "dfdf");

		ovp = new OverViewPage(driver);
		System.out.println("Price" + ovp.getTotalPrice());
		ovp.clickFinishButton();

		fp = new FinishPage(driver);
		String expectedFinishPage = "CHECKOUT: COMPLETE!";
		System.out.println("Expected " + expectedFinishPage);
		String actualFinishPage = fp.getCompleteOrderTitle();
		System.out.println("Actual " + actualFinishPage);
		Assert.assertTrue(fp.isFinishPage(expectedFinishPage));
		fp.returnToProductsPage();
	}
	
	@Feature("Check Add button from InventoryPage")
	@Test(priority = 4)
	public void tc04_addItemsFromInventoryPage() {
		ip.addAllItemsToCart(); // add all items to cart
		ip.openCart();
		cp.continueShoppingBtn(); // return to products page
	}

	@Feature("Remove button from InventoryPage")
	@Test(priority = 5)
	public void tc05_removeItemsFromInventoryPage() {
		ip.removeAllItemsFromCart(); // remove all items from the cart
		ip.openCart();
		cp.checkOut();
		yip.enterValidDetails("ASd", "grr", "gdfgd");
		ovp.getTotalPrice();
	}
	
	
	@Test(priority = 6)
	public void tc06_checkIfCartIsEmpty() {
		if (ovp.getTotalPrice().equalsIgnoreCase("Total: $0.00"))

		{
			System.out.println("Old Price: " + ovp.getTotalPrice());
			ovp.clickOnCancelBtn();
			ip.chooseAnItem("Sauce Labs Backpack");
			iip.addToCart();
			iip.openCart();
			cp.checkOut();
			yip.enterValidDetails("one", "item", "only");
			ovp.getTotalPrice();
			System.out.println("New price " + ovp.getTotalPrice());
			ovp.clickFinishButton();
			fp.getCompleteOrderTitle();
			System.out.println("Complete title: " + fp.getCompleteOrderTitle());

		} else
			System.out.println("You have made an order with 0 items");
	}
	
	@DataProvider
	public Object[][] loginDetails() {
		Object[][] myData = {
				{ "sard_user", "12345", "Epic sadface: Username and password do not match any user in this service" },
				{ "standard_user", "", "Epic sadface: Password is required" },
				{ "", "secret_sauce", "Epic sadface: Username is required" },
				{ "locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out." },
				{ "standard_user", "secret_sauce", null } };
		return myData;
	}
	

}
