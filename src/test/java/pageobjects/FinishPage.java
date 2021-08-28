package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FinishPage extends BasePage{

	@FindBy(css=".complete-header")
	private WebElement finishMsg;
	@FindBy(css="#finish")
	private WebElement finishBtn;
	@FindBy(css=".header_secondary_container>.title")
	private WebElement pageTitle;
	@FindBy(css="#back-to-products")
	private WebElement backHomeBtn;
	
	public FinishPage(WebDriver driver) {
		super(driver);
	}
	
	
	public String getFinishMsg() {
		return getText(finishMsg);
	}
	
	public void completeOrder() {
		click(finishBtn);
	}
	
	public void returnToProductsPage() {
		click(backHomeBtn);
	}
	
	public boolean isFinishPage() {
		return isCurrentPage(pageTitle, "CHECKOUT: COMPLETE!");
	}
	
}
