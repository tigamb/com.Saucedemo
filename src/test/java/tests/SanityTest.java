package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import pageobjects.CartPage;
import pageobjects.FinishPage;
import pageobjects.InventoryItemPage;
import pageobjects.InventoryPage;
import pageobjects.LoginPage;
import pageobjects.YourInfoPage;
import utilities.AllureAttachment;
import utilities.Excel;


public class SanityTest extends BaseTest {
	
	@Owner("Danny Ambaou")
	@Link("https://www.saucedemo.com/") // Create link to the page
	@TmsLink("") // link to Jira / Trello etc..
	@Severity(SeverityLevel.NORMAL)
	@Issue("1")
	@Story("Should return error when a user tries to enter with invalid details")
	@Test(dataProvider="sendData",enabled = true, priority = 1, description = "Invalid login")
	@Description("Login with invalid username and password - expect error")
	public void tc01_failedLoginNo_1(String username, String password ) {
		LoginPage lp = new LoginPage(driver);
		//lg.login("sard_user", "12345");
		lp.login(username, password);
		String expected = "Epic sadface: Username and password do not match user in this service";
		String actual = lp.getErrMsg();
		Assert.assertEquals(actual,expected);
		AllureAttachment.attachText("TEST TEST");
		lp.refreshCurrnetPage(); // refresh current page to wipe all data from the fields and get the correct error message
		
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(enabled = true, priority = 2, description = "failed login")
	@Description("Login without inserting password - expect error")
	public void tc01_failedLoginNo_2() {
		LoginPage lp2 = new LoginPage(driver);
		lp2.login("standard_user", ""); // Enter user
		String expected = "Epic sadface: Password is required";
		String actual = lp2.getErrMsg();
		Assert.assertEquals(actual, expected);
		lp2.refreshCurrnetPage(); // refresh current page to wipe all data from the fields and get the correct error message
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(enabled = true, priority = 3, description = "failed login")
	@Description("Login without inserting username - expect error")
	public void tc01_failedLoginNo_3() {
		LoginPage lp3 = new LoginPage(driver);
		lp3.login("", "secret_sauce"); // Enter password
		String expected = "Epic sadface: Username i required";
		String actual = lp3.getErrMsg();
		Assert.assertEquals(actual, expected);
		lp3.refreshCurrnetPage(); // refresh current page to wipe all data from the fields and get the correct error message
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(enabled = true, priority = 4, description = "Login with valid details")
	@Description("Login with valid username and password - success login")
	public void tco1_successLogin() {
		LoginPage lp = new LoginPage(driver);
		//lp.isLoginPage();
		lp.login("standard_user", "secret_sauce"); // Enter valid user name and password to login
		InventoryPage ip = new InventoryPage(driver);
		Assert.assertTrue(ip.isProductPage("Products"));
		
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
	
	@Test(priority = 9, description = " Click finish button to complete your order")
	public void tc06_completeOrder() {
		FinishPage fp = new FinishPage(driver);
		fp.completeOrder(); // Click to finish order
		Assert.assertTrue(fp.isFinishPage()); // return true if this is the correct page
		System.out.println(fp.getFinishMsg()); // prints finish message
		fp.returnToProductsPage(); // Click back home button
	
	}
	
	@Test(enabled=true, priority=10, description = "Verify you are on products page and buy few items")
	@Description("Add more than 2 items to the cart")
	public void tc07_buyAnotherItem() {
		InventoryPage ip = new InventoryPage(driver); // Create InventoryPage object 
		Assert.assertTrue(ip.isProductPage("Products")); // Verify you are on products page
		ip.chooseAnItem("Sauce Labs Bike Light");
		InventoryItemPage iip = new InventoryItemPage(driver);
		iip.addToCart();
		iip.backToProductsButton();
		ip.chooseAnItem("Sauce Labs Fleece Jacket");
		iip.addToCart();
		iip.backToProductsButton();
		ip.chooseAnItem("Sauce Labs Onesie");
		iip.addToCart();
		iip.openCart();
	}
	
	@DataProvider
	public Object[][] sendData(){
			Object[][] myData= {
					{"sard_user","12345"}, // 			  Expected: Epic sadface: Username and password do not match any user in this service
				//	{"standard_user",""}, //              Expected: Epic sadface: Password is required
					//{"","secret_sauce"}, // 			  Expected: Epic sadface: Username is required
					//{"locked_out_user","secret_sauce"},// Expected: Epic sadface: Sorry, this user has been locked out.
					//{"standard_user","secret_sauce"} //   Success login
			};
			return myData;
	}
	
	@DataProvider // CHEKC FOR MORE DETAILS ONLINE
	public Object[][] getDataFromExcel(){
			String excelPath = System.getProperty("user.dir") + "\\src\\test\\resources\\data\\input.xlsx";
			Object[][] table = Excel.getTableArray(excelPath, "Login");
			return table;
	}
	
	

}
