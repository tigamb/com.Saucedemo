package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Menu extends BasePage{

	@FindBy(css = ".btn_primary.btn_inventory")
	private WebElement addToCartButton;
	@FindBy(css="#react-burger-menu-btn")
	private WebElement burgerMenu;
	@FindBy(css=".bm-item-list")
	private List<WebElement> menuList;
	
	
	public Menu(WebDriver driver) {
		super(driver);
	}
	
	public void addToCart() {
		click(addToCartButton);
	}
	
	public void openBurgerMenu(String value) {
		click(burgerMenu);
		sleep(500);
		
		
	}
	
	
}
