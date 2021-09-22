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

public class FunctionalityTest extends BaseTest {
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
		driver.navigate().refresh(); // Refresh current page to wipe all data from the fields
		AllureAttachment.attachText("login button");

	}

	@Feature("Add button from inventory item page")
	@Severity(SeverityLevel.NORMAL)
	@Test(enabled = true, priority = 2)
	@Description("Choose items by name, click on them, move to product page and click add button")
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

	@Severity(SeverityLevel.NORMAL)
	@Feature("Remove button from cart page")
	@Description("Remove items by name from the cart")
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

	}

	@Test(dataProvider = "yourInfoData", priority = 4)
	public void tc04_insertDetails(String firstName, String lastName, String zipCode, String errorMsg) {
		yip = new YourInfoPage(driver);
		String expectedInfoPage = "CHECKOUT: YOUR INFORMATION";
		System.out.println("Expected " + expectedInfoPage);
		String actualInfoPage = yip.getInfoPageTitle();
		System.out.println("Actual " + actualInfoPage);
		Assert.assertTrue(yip.isInfoPgae(expectedInfoPage));

		yip.enterValidDetails(firstName, lastName, zipCode);
		// yip.clickOnContinueBtn();
		String expectedErrorMsg = errorMsg;
		System.out.println("Expected Error" + expectedErrorMsg);
		String actualErrMsg = yip.getErrorMessage();
		System.out.println("Actual Error: " + actualErrMsg);
		Assert.assertEquals(actualErrMsg, expectedErrorMsg);
		driver.navigate().refresh();

	}

	@Test(priority = 5)
	public void tc05_getTotalPrice() {
		ovp = new OverViewPage(driver);
		System.out.println("Price" + ovp.getTotalPrice());
		ovp.clickFinishButton();
	}

	@Test(priority = 6)
	public void tc06_getFinishPageTitle() {
		fp = new FinishPage(driver);
		String expectedFinishPage = "CHECKOUT: COMPLETE!";
		System.out.println("Expected " + expectedFinishPage);
		String actualFinishPage = fp.getCompleteOrderTitle();
		System.out.println("Actual " + actualFinishPage);
		Assert.assertTrue(fp.isFinishPage(expectedFinishPage));
		fp.returnToProductsPage();
	}

	@Feature("Add button from InventoryPage")
	@Description("Add all items from products page to cart")
	@Test(priority = 7)
	public void tc07_addItemsFromInventoryPage() {
		ip.addAllItemsToCart(); // add all items to cart
		ip.openCart();
		cp.continueShoppingBtn(); // return to products page
	}

	@Severity(SeverityLevel.NORMAL)
	@Feature("Remove button from InventoryPage")
	@Description("Remove all items from previous test")
	@Test(priority = 8)
	public void tc08_removeItemsFromInventoryPage() {
		ip.removeAllItemsFromCart(); // remove all items from the cart
		ip.openCart();
		cp.checkOut();
		yip.enterValidDetails("for", "test", "only");
		ovp.getTotalPrice();
	}

	@Feature("Total price")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 9)
	@Description("Check total price - if it equals 0.00 it means the cart is empty")
	public void tc09_checkIfCartIsEmpty() {
		if (ovp.getTotalPrice().equalsIgnoreCase("Total: $0.00")) {
			ovp.clickFinishButton();
			fp.getCompleteOrderTitle();
			System.out.println("Complete title: " + fp.getCompleteOrderTitle());
			System.out.println("You have made an order with 0 items");

		} else {
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
		}

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

	@DataProvider
	public Object[][] yourInfoData() {
		Object[][] myData = { { "firstNameOnly", "", "", "Error: Last Name is required" },
				{ "", "lastNameOnly", "", "Error: First Name is required" },
				{ "", "", "zipCodeOnly", "Error: First Name is required" },
				{ "firstName", "lastName", "", "Error: Postal Code is required" },
				{ "firstName", "", "zipCode", "Error: Last Name is required" },
				{ "firstName", "lastName", "zipCode", null } };
		return myData;
	}

}
