package pageobjects;


import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public abstract class BasePage {

	WebDriver driver;
	JavascriptExecutor js;

	// constructor
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
	}
	
	// fill text method
	public void fillText(WebElement el, String text) {
		el.clear();
		// js.executeScript("arguments[0].setAttribute('style', 'background-color:yellow; border: 1px solid blue;');", el);
		highlightElement(el, "yellow");
		el.sendKeys(text);
	}

	// click method
	public void click(WebElement el) {
		// js.executeScript("arguments[0].setAttribute('style', 'border: 1px solid green;');", el);
		highlightElement(el, "orange");
		el.click();
	}

	// get method
	public String getText(WebElement el) {
		// js.executeScript("arguments[0].setAttribute('style', 'background-color:yellow; border: 1px solid orange;');", el);
		highlightElement(el, "grey");
		return el.getText();
	}

	// wait
	public void sleep(long mills) {
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean isCurrentPage(WebElement title, String text) {
		if(getText(title).equalsIgnoreCase(text))
			return true;
		else
			return false;
	}
	
	
	public void jScriptPopScreen() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('Welcome To My Automation Testing');");
		Alert alt = driver.switchTo().alert();
		System.out.println(alt.getText());
		sleep(1000);
		alt.accept();
	}
	
	public void refreshPage() {
		driver.navigate().refresh();
	}
	
	// close driver
	public void tearDown() {
		driver.close();
	}

	/*
	 * Call this method with your element and a color like (red,green,orange etc...)
	 */
	protected void highlightElement(WebElement element, String color) {
		// keep the old style to change it back
		String originalStyle = element.getAttribute("style");
		String newStyle = "background-color: yellow; border: 1px solid " + color + ";" + originalStyle;
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Change the style
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ newStyle + "');},0);", element);

		// Change the style back after few miliseconds
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ originalStyle + "');},400);", element);

	}
	
	

}
