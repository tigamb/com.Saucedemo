package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YourInfoPage extends BasePage{
	
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
	
	public YourInfoPage(WebDriver driver) {
		super(driver);
	}
	
	
	public void enterValidDetails(String fName, String lName, String zCode) {
		fillText(firstNameField,fName);
		sleep(500);
		fillText(lastNameField, lName);
		sleep(500);
		fillText(zipCodeField, zCode);
		sleep(500);
		click(continuteBtn);
	}
	
	public void refreshCurrentPage() {
		driver.navigate().refresh();
	}
	
	
	

}
