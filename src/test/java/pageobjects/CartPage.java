package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

	@FindBy(css = "#checkout")
	private WebElement checkOutButton;
	@FindBy(css = "#continue-shopping")
	private WebElement continueBtn;
	@FindBy(css = ".btn_small.cart_button")
	private WebElement removeBtn;

	public CartPage(WebDriver driver) {
		super(driver);
	}

	
	public void checkOut() {
		click(checkOutButton);
	}

	
	public void continueShoppingBtn() {
		click(continueBtn);
	}
	
	public void removeItemBtn() {
		click(removeBtn);
	}
	
}
