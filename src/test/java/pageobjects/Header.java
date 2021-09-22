package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends BasePage{

	@FindBy(css="#react-burger-menu-btn")
	private WebElement sideMenu;
	@FindBy(css=".shopping_cart_link")
	private WebElement cartButton;
	
	public Header(WebDriver driver) {
		super(driver);
	}

	
	public void openCart() {
		click(cartButton);
	}
	
	
	public void openBurgerMenu() {
		click(sideMenu);
	}
	
	
}
