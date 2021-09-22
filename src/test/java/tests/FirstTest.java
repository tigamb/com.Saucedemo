package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageobjects.LoginPage;
import utilities.AllureAttachment;
import utilities.Excel;

public class FirstTest extends BaseTest {

	
	@Owner("Danny Ambaou")
	@Link("https://www.saucedemo.com/") // Create link to the page
	@Severity(SeverityLevel.NORMAL)
	@Issue("1")
	@Test(dataProvider = "sendData", enabled = true, priority = 1, description = "login page")
	@Description("Login using user name and password and get error messages")
	public void tc01_Login(String username, String password, String errorMsg) {
		LoginPage lp = new LoginPage(driver);
		lp.login(username, password);

		while (errorMsg == null) {
			System.out.println(" Success login");
		}
		String expected = errorMsg; 
		System.out.println("Expected:" + expected);
		String actual = lp.getErrorMessage(); 
		Assert.assertEquals(actual, expected);
		driver.navigate().refresh(); // refresh current page to wipe all data from the fields and get the correct error message
		AllureAttachment.attachText("login button");

	}
	
	
	
	@DataProvider
	public Object[][] sendData() {
		Object[][] myData = {
				{ "sard_user", "12345", "Epic sadface: Username and password do not match any user in this service" },
				{ "standard_user", "", "Epic sadface: Password is required" },
				{ "", "secret_sauce", "Epic sadface: Username is required" },
				{ "locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out." },
				{ "standard_user", "secret_sauce", null } };
		return myData;
	}
	
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
	
	
	@DataProvider // CHEKC FOR MORE DETAILS ONLINE
	public Object[][] getDataFromExcel() {
		String excelPath = System.getProperty("user.dir") + "\\src\\test\\resources\\data\\input.xlsx";
		Object[][] table = Excel.getTableArray(excelPath, "Login");
		return table;
	}
}
