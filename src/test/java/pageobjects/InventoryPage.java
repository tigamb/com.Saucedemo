package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage extends BasePage {
	
	@FindBy(css = "#add-to-cart-sauce-labs-backpack")
	private WebElement AddToCartButton;

	@FindBy(css = ".inventory_item_name")
	private List<WebElement> itemsList;

	@FindBy(css = ".header_secondary_container>.title")
	private WebElement pageTitle;

	public InventoryPage(WebDriver driver) {
		super(driver);

	}

	public void chooseAnItem(String itemName) {
		// List<WebElement> itemsList =
		// driver.findElements(By.cssSelector(".inventory_container>.inventory_list"));
		for (WebElement iList : itemsList) {
			if (iList.getText().equalsIgnoreCase(itemName)) {
				click(iList); // sends the element
				break; // to stop searching
			}
		}
	}

	public void addToCart() {
		click(AddToCartButton);
	}
	
	public boolean isProductPage(String str) {
		return isCurrentPage(pageTitle, str);
	}
}
