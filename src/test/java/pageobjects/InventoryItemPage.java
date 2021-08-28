package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryItemPage extends BasePage{

	@FindBy(css=".btn_inventory")
	private WebElement addToCartButton;
	@FindBy(css=".inventory_details_back_button")
	private WebElement backButton;
	@FindBy(css = ".shopping_cart_link")
	private WebElement shoppingCart;
	
	
	public InventoryItemPage(WebDriver driver) {
		super(driver);
	}
	
	
	public void addToCart() {
		click(addToCartButton);
	}
	
	public void backToProductsButton() {
		click(backButton);
	}
	
	
	public void openCart() {
		click(shoppingCart);
	}

}
