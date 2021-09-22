package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
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

public class SanityTest extends BaseTest {

	@Owner("Danny Ambaou")
	@Link("https://www.saucedemo.com/") // Create link to the page
	@Feature("Login")
	@Severity(SeverityLevel.BLOCKER)
	@Test(dataProvider = "sendData", enabled = true, priority = 1, description = "login page")
	@Description("Login using user name and password and get error messages")
	public void tc01_login(String username, String password) {
		LoginPage lp = new LoginPage(driver);
		lp.login(username, password);
		/*
		 * String expected = errorMessage; System.out.println("Expected: " + expected);
		 * // prints expected error message String actual = lp.getErrorMessage();
		 * Assert.assertEquals(actual, expected); driver.navigate().refresh(); //
		 * refresh current page to wipe all data from the fields and get the correct //
		 * error message AllureAttachment.attachText("login button"); while
		 * (errorMessage == null) { System.out.println("Success login"); }
		 */

	}

	@Owner("Danny Ambaou")
	@Epic("INVENTORY")
	@Feature("Product details, add to cart")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2, description = "Pick an item")
	public void tc02_pickAnItem() {
		InventoryPage ip = new InventoryPage(driver);
		String expected = "PRODUCTS";
		System.out.println("Expected:" + expected);
		String actual = ip.getInvPageTitle();
		System.out.println("Actual:" + actual);
		Assert.assertEquals(actual, expected);
		AllureAttachment.attachText("Products page");
		ip.chooseAnItem("Sauce Labs Backpack");

	}

	@Owner("Danny Ambaou")
	@Epic("INVENTORY-ITEM")
	@Feature("Add to cart")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 3, description = "Add an item to your cart")
	public void tc03__addItemsToCart() {
		InventoryItemPage iip = new InventoryItemPage(driver);
		iip.addToCart();
		iip.openCart();
	}

	@Owner("Danny Ambaou")
	@Epic("YOUR CART")
	@Feature("Checkout button")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4, description = " Click on checkout button")
	public void tc04_checkout() {
		CartPage cp = new CartPage(driver);
		AllureAttachment.attachText("Checkout button");
		cp.checkOut();
	}

	@Owner("Danny Ambaou")
	@Epic("CHECKOUT: YOUR INFORMATION")
	@Feature("Checkout Step one")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 5, description = "Insert valid details and click continue")
	public void tc05_insertYourInfo() {
		YourInfoPage yip = new YourInfoPage(driver);
		AllureAttachment.attachText("Checkout-ste-one");
		yip.enterValidDetails("danny", "test", "123456");

	}

	@Owner("Danny Ambaou")
	@Epic("CHECKOUT: OVERVIEW")
	@Feature("Checkout step two")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 6, description = " Insert valid details and click continue")
	public void tc06_overViewPage() {
		OverViewPage ovp = new OverViewPage(driver);
		// Assert.assertTrue(ovp.isOverViewPage("CHECKOUT: OVERVIEW"));

		String expected = "CHECKOUT: OVERVIEW";
		System.out.println("Expected:" + expected);
		String actual = ovp.getPageTitle();
		System.out.println("Actual:" + actual);
		Assert.assertEquals(actual, expected);
		AllureAttachment.attachText("Checkout-ste-two");
		ovp.clickFinishButton();

	}
	
	@Owner("Danny Ambaou")
	@Epic("CHECKOUT: COMPLETE!")
	@Feature("Checkout complete")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 7, description = " Click finish button to complete your order")
	public void tc07_completeOrder() {
		FinishPage fp = new FinishPage(driver);
		String expected = "CHECKOUT: COMPLETE!";
		Assert.assertTrue(fp.isFinishPage(expected)); // Return true if this is the correct page
		System.out.println(fp.getCompleteOrderTitle()); // Prints complete order title
		// fp.returnToProductsPage(); // Click back home button

	}
	
	@DataProvider
	public Object[][] sendData() {
		Object[][] myData = {
				//{ "sard_user", "12345", "Epic sadface: Username and password do not match any user in this service" },
				//{ "standard_user", "", "Epic sadface: Password is required" },
				//{ "", "secret_sauce", "Epic sadface: Username is required" },
				//{ "locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out." },
				{ "standard_user", "secret_sauce"} };
		return myData;
	}

}
