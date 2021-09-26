package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage extends Header {

	@FindBy(css = ".btn_small.btn_inventory") // .btn_small.btn_inventory
	private List<WebElement> AddOrRemoveButton;
	@FindBy(css = ".inventory_item_name") // .inventory_item_name
	private List<WebElement> itemsList;
	@FindBy(css = ".header_secondary_container>.title")
	private WebElement InvPageTitle;
	@FindBy(css = ".shopping_cart_link")
	private WebElement cartBtn;
	@FindBy(css = ".product_sort_container")
	private WebElement sorter;

	public InventoryPage(WebDriver driver) {
		super(driver);

	}

	public void chooseAnItem(String itemName) {
		// List<WebElement> itemsList =
		// driver.findElements(By.cssSelector(".inventory_container>.inventory_list"));
		for (WebElement iList : itemsList) {
			if (iList.getText().equalsIgnoreCase(itemName)) {
				click(iList);
				break;
			}
		}
	}

	public void addAllItemsToCart() {
		for (WebElement addBtn : AddOrRemoveButton) {
			click(addBtn);
			sleep(500);
			// break;
		}
	}

	public void removeAllItemsFromCart() {
		for (WebElement addBtn : AddOrRemoveButton) {
			click(addBtn);
			sleep(500);
			// break;
		}
	}

	public void removeItemsFromCart(String itemToRemvoe) {
		List<WebElement> removeItems = driver.findElements(By.cssSelector(".btn_small.btn_inventory"));
		for (WebElement iList : removeItems) {
			if (iList.getText().equalsIgnoreCase(itemToRemvoe)) {
				click(iList);
				break;
			}
		}

	}

	public boolean isInventoryPage(String inventoryTitle) {
		return isCurrentPage(InvPageTitle, inventoryTitle);
	}

	public String getInvPageTitle() {
		return getText(InvPageTitle);
	}

	public void sortProdcutsByValue() {
		click(sorter);
		sleep(500);
		Select sort = new Select(sorter);
		sort.selectByValue("Name (Z to A)");
	}

	public void sortProdcutsByIndex() {
		click(sorter);
		sleep(500);
		Select sort = new Select(sorter);
		sort.selectByIndex(1);
	}

}
