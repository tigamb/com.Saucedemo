package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.FinishPage;
import pageobjects.InventoryItemPage;
import pageobjects.InventoryPage;
import pageobjects.LoginPage;
import pageobjects.YourInfoPage;
import utils.Excel;


public class SanityTest extends BaseTest {
	
	@Test(dataProvider="sendData",enabled = true, priority = 1, description = "Invalid user name and password")
	public void tc01_failedLoginNo_1(String username, String password ) {
		LoginPage lg = new LoginPage(driver);
		//lg.login("sard_user", "12345");
		lg.login(username, password);
		String expected = "Epic sadface: Username and password do not match any user in this service";
		String actual = lg.getErrMsg();
		Assert.assertEquals(actual,expected);
		lg.refreshCurrnetPage(); // refresh current page to wipe all data from the fields and get the correct error message
		
	}

	@Test(enabled = false, priority = 2, description = "Try to connect with user name only")
	public void tc01_failedLoginNo_2() {
		LoginPage lg2 = new LoginPage(driver);
		lg2.login("standard_user", ""); // Enter user
		String expected = "Epic sadface: Password is required";
		String actual = lg2.getErrMsg();
		Assert.assertEquals(actual, expected);
		lg2.refreshCurrnetPage(); // refresh current page to wipe all data from the fields and get the correct error message
	}
	
	
	@Test(enabled = false, priority = 3, description = "Try to connect with password only")
	public void tc01_failedLoginNo_3() {
		LoginPage lg3 = new LoginPage(driver);
		lg3.login("", "secret_sauce"); // Enter password
		String expected = "Epic sadface: Username is required";
		String actual = lg3.getErrMsg();
		Assert.assertEquals(actual, expected);
		lg3.refreshCurrnetPage(); // refresh current page to wipe all data from the fields and get the correct error message
	}
	
	@Test(enabled = true, priority = 4, description = "SUCCESS LOGIN")
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
	
	@Test(priority = 6, description = "add an item to your cart")
	public void tc03__addItemsToCart() {
		InventoryItemPage iip = new InventoryItemPage(driver);
		iip.addToCart();
		iip.openCart();
	}

	@Test(priority = 7, description = " Click on checkout button")
	public void tc04_checkout() {
		CartPage cp = new CartPage(driver);
		cp.checkOut();
	}
	
	@Test(priority = 8, description = " Insert valide details and click continue")
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
	
	@DataProvider
	public Object[][] getDataFromExcel(){
			String excelPath = System.getProperty("user.dir") + "/src/test/resources/data/input.xlsx";
			Object[][] table = Excel.getTableArray(excelPath, "Login");
			return table;
	}
	

}
