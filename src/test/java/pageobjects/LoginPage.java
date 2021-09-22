package pageobjects;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.AllureAttachment;

public class LoginPage extends BasePage {

	@FindBy(css = "#user-name")
	private WebElement userName;
	@FindBy(css = "#password")
	private WebElement password;
	@FindBy(css = "#login-button")
	private WebElement loginBtn;
	@FindBy(css = "[data-test=\"error\"]")
	private WebElement errorMsg;
	@FindBy(css="#login_credentials")
	private WebElement botLogo;
	@FindBy(css=".error-button")
	private WebElement closeErrorMessage;

	public LoginPage(WebDriver driver) {
		super(driver);

	}
	
	public void login(String user, String pass) {
		fillText(userName, user);
		fillText(password, pass);
		AllureAttachment.attachElementScreenshot(loginBtn);
		click(loginBtn);
		sleep(2000);
		
	}
	
	public void popUpScreen() {
		jScriptPopScreen();
		
	}

	public void returnBtn() {
		driver.navigate().back();
	}
	
	public String getErrorMessage() {
		return getText(errorMsg);
	}
	
	
	public void refreshCurrnetPage() {
		driver.navigate().refresh();
	}
	
}
