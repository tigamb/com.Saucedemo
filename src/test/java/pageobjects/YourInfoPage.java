package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.AllureAttachment;

public class YourInfoPage extends Header{
	
	@FindBy(css="#first-name")
	private WebElement firstNameField;
	@FindBy(css="#last-name")
	private WebElement lastNameField;
	@FindBy(css="#postal-code")
	private WebElement zipCodeField;
	@FindBy(css="#cancel")
	private WebElement cancelBtn;
	@FindBy(css="#continue")
	private WebElement continuteBtn;
	@FindBy(css=".title")
	private WebElement yourInfoPageTitle;
	@FindBy(css=".error-message-container.error")
	private WebElement error;
	
	public YourInfoPage(WebDriver driver) {
		super(driver);
	}
	
	
	public boolean isInfoPgae(String title) {
		return isCurrentPage(yourInfoPageTitle, title);
	}
	
	
	public String getErrorMessage() {
		return getText(error);
	}
	
	public String getInfoPageTitle() {
		return getText(yourInfoPageTitle);
	}
	
	public void enterValidDetails(String fName, String lName, String zCode) {
		fillText(firstNameField,fName);
		sleep(500);
		fillText(lastNameField, lName);
		sleep(500);
		fillText(zipCodeField, zCode);
		sleep(500);
		AllureAttachment.attachElementScreenshot(continuteBtn); 
		click(continuteBtn);
		
	}
	
	public void clickOnContinueBtn() {
		AllureAttachment.attachElementScreenshot(continuteBtn); 
		click(continuteBtn);
	}
	
	public void refreshCurrentPage() {
		driver.navigate().refresh();
	}
	
	
	

}
