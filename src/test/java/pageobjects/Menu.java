package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Menu extends BasePage{

	@FindBy(css = ".btn_primary.btn_inventory")
	private WebElement addToCartButton;
	
	public Menu(WebDriver driver) {
		super(driver);
	}
	
	public void addToCart() {
		click(addToCartButton);
	}
	
	
}
