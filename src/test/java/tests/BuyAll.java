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
		driver.navigate().refresh(); // Refresh current page to wipe all data from the fields
		AllureAttachment.attachText("login button");

	}

	@Feature("Add button from InventoryPage")
	@Description("Add all items from products page to cart")
	@Test(priority = 2)
	public void tc02_addItemsFromInventoryPage() {
		ip = new InventoryPage(driver);
		cp = new CartPage(driver);
		yip = new YourInfoPage(driver);
		ovp = new OverViewPage(driver);
		ip.addAllItemsToCart(); // add all items to cart
		ip.openCart();
		cp.checkOut();
		yip.enterValidDetails("4trr", "gdfg", "kjkg");
		ovp.getTotalPrice();
	}

	@Feature("Total price")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	@Description("Check total price - if it equals 0.00 it means the cart is empty")
	public void tc03_checkIfCartIsEmpty() {
		fp = new FinishPage(driver);
		if (ovp.getTotalPrice().equalsIgnoreCase("Total: $0.00")) {
			ovp.clickFinishButton();
			fp.getCompleteOrderTitle();
			System.out.println("Complete title: " + fp.getCompleteOrderTitle());
			System.out.println("You have made an order with 0 items");

		} else {
			System.out.println("Old Price: " + ovp.getTotalPrice());
			ovp.clickFinishButton();
			fp.getCompleteOrderTitle();
			System.out.println("Complete title: " + fp.getCompleteOrderTitle());
		}

	}

	@DataProvider
	public Object[][] loginDetails() {
		Object[][] myData = {
				{ "standard_user", "secret_sauce", null } };
		return myData;
	}

	
}
