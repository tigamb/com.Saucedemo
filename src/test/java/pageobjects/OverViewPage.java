package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.AllureAttachment;

public class OverViewPage extends Header {

	@FindBy(css = "#finish")
	private WebElement finishBtn;
	@FindBy(css = ".header_secondary_container>.title")
	private WebElement overViewPageTitle;
	@FindBy(css=".summary_total_label")
	private WebElement totalPrice;
	@FindBy(css="#cancel")
	private WebElement cancelButton;
	

	public OverViewPage(WebDriver driver) {
		super(driver);
	}

	
	public void clickFinishButton() {
		sleep(500);
		AllureAttachment.attachElementScreenshot(finishBtn);
		click(finishBtn);
	}
	
	public String getPageTitle() {
		return getText(overViewPageTitle);
	}
	
	
	public boolean isOverViewPage(String str) {
		return isCurrentPage(overViewPageTitle, str);
	}
	
	
	public String getTotalPrice() {
		return getText(totalPrice);
	}
	
	public void clickOnCancelBtn() {
		click(cancelButton);
	}
	
}
