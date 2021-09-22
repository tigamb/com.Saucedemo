package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.AllureAttachment;

public class CartPage extends Header {

	@FindBy(css = "#checkout")
	private WebElement checkOutButton;
	@FindBy(css = "#continue-shopping")
	private WebElement continueBtn;
	@FindBy(css = ".inventory_item_name")
	private List<WebElement> itemName;
	@FindBy(css=".shopping_cart_badge")
	private WebElement numOfItems;
	@FindBy(css=".btn_small.cart_button")
	private List<WebElement> removeBtn;
	@FindBy(css=".title")
	private WebElement cartPageTitle;
	

	
	public CartPage(WebDriver driver) {
		super(driver);
	}

	
	public String getCartPageTitle() {
		return getText(cartPageTitle);
	}
	
	
	public boolean isCartPage(String str) {
		return isCurrentPage(cartPageTitle, str);
	}
	
	
	public void checkOut() {
		sleep(500);
		AllureAttachment.attachElementScreenshot(checkOutButton);
		click(checkOutButton);
	}

	
	public void continueShoppingBtn() {
		sleep(500);
		click(continueBtn);
	}
	
	
	public int getNumOfItems() {
		String str = getText(numOfItems);
		int num = Integer.parseInt(str);
		return num;
	}
	
	
	
	public void removeItemsFromCart(String name) {
		sleep(500);
		int counter = 0;
		for (WebElement el : itemName) {
			if (el.getText().equalsIgnoreCase(name)) {
				break;
			}
			counter++;
		}
		click(removeBtn.get(counter));
		sleep(500);
	}
	
	
}
