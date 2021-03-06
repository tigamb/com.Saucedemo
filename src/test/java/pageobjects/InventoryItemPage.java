package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.AllureAttachment;

public class InventoryItemPage extends Header{

	@FindBy(css=".btn_inventory")
	private WebElement addOrRemoveButton;
	@FindBy(css=".inventory_details_back_button")
	private WebElement backButton;
	@FindBy(css = ".shopping_cart_link")
	private WebElement shoppingCart;
	
	
	
	public InventoryItemPage(WebDriver driver) {
		super(driver);
	}
	
	
	public void addToCart() {
		sleep(500);
		AllureAttachment.attachElementScreenshot(addOrRemoveButton);
		click(addOrRemoveButton);
	}
	
	
	
	public void backToProductsButton() {
		AllureAttachment.attachElementScreenshot(backButton);
		click(backButton);
	}
	
	
	/*
	public void openCart() {
		AllureAttachment.attachElementScreenshot(shoppingCart);
		click(shoppingCart);
	}
	*/
	
	public String getPageTitle() {
		return getText(backButton);
	}
	
	
	/*
	 * public boolean isInventoryItemPage() { return isCurrentPage(addToCartButton,
	 * "BACK TO PRODUCTS"); }
	 */

}
