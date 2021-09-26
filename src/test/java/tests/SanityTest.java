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
	@Test(dataProvider = "sendLoginData", enabled = true, priority = 1)
	@Description("Login using user name and password and get error messages")
	public void tc01_login(String username, String password) {
		LoginPage lp = new LoginPage(driver);
		String expectedLoginPage = "Password for all users:";
		System.out.println("Expected: " +expectedLoginPage);
		String actualLoginPage = lp.checkLoginPage();
		System.out.println("Actual: " +actualLoginPage + "\n");
		Assert.assertEquals(actualLoginPage, expectedLoginPage);
		lp.login(username, password);

	}

	@Epic("INVENTORY")
	@Feature("Product details, add to cart")
	@Severity(SeverityLevel.NORMAL)
	@Description("Pick an item")
	@Test(priority = 2)
	public void tc02_pickAnItem() {
		InventoryPage ip = new InventoryPage(driver);
		String expected = "PRODUCTS";
		System.out.println("Expected: " + expected);
		String actual = ip.getInvPageTitle();
		System.out.println("Actual: " + actual +"\n");
		Assert.assertEquals(actual, expected);
		AllureAttachment.attachText("Products page");
		ip.chooseAnItem("Sauce Labs Backpack");

	}

	@Epic("INVENTORY-ITEM")
	@Feature("Add to cart")
	@Severity(SeverityLevel.NORMAL)
	@Description("Add an item to your cart")
	@Test(priority = 3)
	public void tc03__addItemsToCart() {
		InventoryItemPage iip = new InventoryItemPage(driver);
		String expectedIip = "BACK TO PRODUCTS";
		System.out.println("Expected: " +expectedIip);
		String actualIip = iip.getPageTitle();
		System.out.println("Actual: " +actualIip + "\n");
		Assert.assertEquals(actualIip, expectedIip);
		iip.addToCart();
		iip.openCart();
	}

	@Epic("YOUR CART")
	@Feature("Checkout button")
	@Severity(SeverityLevel.NORMAL)
	@Description("Click on checkout button")
	@Test(priority = 4)
	public void tc04_checkout() {
		CartPage cp = new CartPage(driver);
		String expectedCartPage = "YOUR CART";
		System.out.println("Expected: " +expectedCartPage);
		String actualCartPage = cp.getCartPageTitle();
		System.out.println("Actual: " +actualCartPage + "\n");
		Assert.assertEquals(actualCartPage, expectedCartPage);
		cp.checkOut();
	}

	@Epic("CHECKOUT: YOUR INFORMATION")
	@Feature("Checkout Step one")
	@Severity(SeverityLevel.NORMAL)
	@Description("Insert valid details and click continue")
	@Test(priority = 5)
	public void tc05_insertYourInfo() {
		YourInfoPage yip = new YourInfoPage(driver);
		String expectedYourInfoPage = "CHECKOUT: YOUR INFORMATION";
		System.out.println("Actual: " +expectedYourInfoPage);
		String actualYourInfoPage = yip.getInfoPageTitle();
		System.out.println("Actual: " +actualYourInfoPage + "\n");
		Assert.assertEquals(actualYourInfoPage, expectedYourInfoPage);
		yip.enterValidDetails("danny", "test", "123456");

	}

	@Epic("CHECKOUT: OVERVIEW")
	@Feature("Checkout step two")
	@Severity(SeverityLevel.NORMAL)
	@Description("Insert valid details and click continue")
	@Test(priority = 6)
	public void tc06_overViewPage() {
		OverViewPage ovp = new OverViewPage(driver);
		String expected = "CHECKOUT: OVERVIEW";
		System.out.println("Expected:" + expected);
		String actual = ovp.getPageTitle();
		System.out.println("Actual:" + actual +"\n");
		Assert.assertEquals(actual, expected);
		ovp.clickFinishButton();

	}

	@Epic("CHECKOUT: COMPLETE!")
	@Feature("Checkout complete")
	@Severity(SeverityLevel.NORMAL)
	@Description("Click finish button to complete your order")
	@Test(priority = 7)
	public void tc07_completeOrder() {
		FinishPage fp = new FinishPage(driver);
		String expected = "CHECKOUT: COMPLETE!";
		Assert.assertTrue(fp.isFinishPage(expected));
		System.out.println(fp.getCompleteOrderTitle());

	}
	
	@DataProvider
	public Object[][] sendLoginData() {
		Object[][] myData = { { "standard_user", "secret_sauce" } };
		return myData;
	}

}
