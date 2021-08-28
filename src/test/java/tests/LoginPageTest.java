package tests;

import org.testng.annotations.Test;

import pageobjects.LoginPage;

public class LoginPageTest extends BaseTest{
	
	
	@Test(description="SUCCESS LOGIN")
	public void tco1_successLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.popUpScreen();
		lp.login("standard_user","secret_sauce");
		
		
	}
	
	@Test(description="FAILED LOGIN")
	public void tc02_failedLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.login("standard_use","secret_sauce");
		//lp.fialedLoginMSG();
		
	}

}
