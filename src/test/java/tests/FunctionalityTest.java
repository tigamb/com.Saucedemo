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
import utilities.Excel;

public class FunctionalityTest extends BaseTest {

	@Owner("Danny Ambaou")
	@Link("https://www.saucedemo.com/") // Create link to the page
	@Feature("Login")
	@Severity(SeverityLevel.BLOCKER)
	@Test(dataProvider = "loginDetails", enabled = true, priority = 1, description = "login page")
	@Description("Login using user name and password and get error messages")
	public void tc01_login(String username, String password, String errorMessage) {
		LoginPage lp = new LoginPage(driver);
		lp.login(username, password);
		String expected = errorMessage;
		System.out.println("Expected: " + expected); // prints expected error message
		String actual = lp.getErrorMessage();
		Assert.assertEquals(actual, expected);
		driver.navigate().refresh(); // refresh current page to wipe all data from the fields and get the correct //
		AllureAttachment.attachText("login button");
		while (errorMessage == null) {
			System.out.println("Success login");
		}

	}

	@Test(priority = 5, description = "Pick an item")
	public void tc02_pickAnItem() {
		InventoryPage ip = new InventoryPage(driver);
		ip.chooseAnItem("Sauce Labs Backpack");
		
	}

	@Epic("Products")
	@Feature("Add to cart")
	@Test(priority = 6, description = "add an item to your cart")
	public void tc03__addItemsToCart() {
		InventoryItemPage iip = new InventoryItemPage(driver);
		iip.addToCart();
		iip.openCart();
	}

	@Epic("Cart")
	@Feature("Checkout")
	@Test(priority = 7, description = " Click on checkout button")
	public void tc04_checkout() {
		CartPage cp = new CartPage(driver);
		cp.checkOut();

	}

	@Test(priority = 8, description = " Insert valid details and click continue")
	public void tc05_insertYourInfo() {
		YourInfoPage yip = new YourInfoPage(driver);
		yip.enterValidDetails("danny", "test", "123456");

	}

	@Owner("Danny Ambaou")
	@Epic("CHECKOUT: OVERVIEW")
	@Feature("Checkout step two")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 9, description = " Insert valid details and click continue")
	public void tc06_overViewPage() {
		OverViewPage ovp = new OverViewPage(driver);
		Assert.assertTrue(ovp.isOverViewPage("CHECKOUT: OVERVIEW"));
		ovp.clickFinishButton();

	}

	@Owner("Danny Ambaou")
	@Epic("CHECKOUT: COMPLETE!")
	@Feature("Checkout complete")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 10, description = " Click finish button to complete your order")
	public void tc07_completeOrder() {
		FinishPage fp = new FinishPage(driver);
		Assert.assertTrue(fp.isFinishPage("")); // return true if this is the correct page
		System.out.println(fp.getCompleteOrderTitle()); // prints finish message
		fp.returnToProductsPage(); // Click back home button

	}

	@Test(enabled = false, priority = 11, description = "Verify you are on products page and buy few items")
	@Description("Add all items to the cart")
	public void tc08_addAllItemsToCart() {
		InventoryPage ip = new InventoryPage(driver); // Create an inventoryPage object
		// Assert.assertTrue(ip.isProductPage("Products")); // Verify products page
		InventoryItemPage iip = new InventoryItemPage(driver);
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
		iip.openCart();
		
		/*
		 * CartPage cp = new CartPage(driver); int numOfItemsBeforeRemoving =
		 * cp.getNumOfItems(); cp.removeItems("Sauce Labs Bike Light");
		 * cp.removeItems("Sauce Labs Fleece Jacket");
		 * cp.removeItems("Sauce Labs Onesie"); int numOfItemsAfterRemoving =
		 * cp.getNumOfItems(); Assert.assertEquals(numOfItemsBeforeRemoving,
		 * numOfItemsAfterRemoving);
		 */

	}

	/*
	 * public void tc09_delete_item_from_cart() { CartPage cp = new
	 * CartPage(driver);
	 * 
	 * int numOfItemsBeforeRemoving = cp.getNumOfItems();
	 * cp.removeItems("Sauce Labs Bolt T-Shirt");
	 * cp.removeItems("Sauce Labs Fleece Jacket");
	 * cp.removeItems("Sauce Labs Onesie"); int numOfItemsAfterRemoving =
	 * cp.getNumOfItems(); Assert.assertEquals(numOfItemsBeforeRemoving,
	 * numOfItemsAfterRemoving);
	 * 
	 * 
	 * }
	 */

	@DataProvider
	public Object[][] itemsList() {
		Object[][] myData = { { "Sauce Labs Backpack" }, 
				{ "Sauce Labs Bolt T-Shirt" }, 
				{ "Sauce Labs Onesie" },
				{ "Sauce Labs Bike Light" }, 
				{ "Sauce Labs Fleece Jacket" }, 
				{ "Test.allTheThings() T-Shirt (Red)" } };

		return myData;
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

	@DataProvider // CHEKC FOR MORE DETAILS ONLINE
	public Object[][] getDataFromExcel() {
		String excelPath = System.getProperty("user.dir") + "\\src\\test\\resources\\data\\input.xlsx";
		Object[][] table = Excel.getTableArray(excelPath, "Login");
		return table;
	}

}
