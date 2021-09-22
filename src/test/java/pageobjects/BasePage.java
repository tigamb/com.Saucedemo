package pageobjects;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public abstract class BasePage {

	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	// Constructor
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
	}

	// fill text method
	@Step()
	public void fillText(WebElement el, String text) {
		el.clear();
		highlightElement(el, "yellow");
		el.sendKeys(text);
		/*
		 * if (el.isDisplayed()) { el.clear(); el.sendKeys(text); }
		 */

	}

	// click method
	@Step()
	public void click(WebElement el) {
		highlightElement(el, "orange");
		el.click();
		/*
		 * if (el.isDisplayed() || el.isEnabled()) el.click();
		 */

	}

	// get method
	@Step()
	public String getText(WebElement text) {
		highlightElement(text, "grey");
		return text.getText();

	}

	// wait
	@Step()
	public void sleep(long mills) {
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void waitForVisibleOfElement(WebElement el) {
		wait.until(ExpectedConditions.visibilityOf(el));
	}

	protected void waitForInVisibleOfElement(WebElement el) {
		wait.until(ExpectedConditions.invisibilityOf(el));
	}

	
	public boolean isListExist(List<WebElement> list) {
		if (list.size() != 0) {
			return true;
		}
		else
			return false;
	}

	
	@Step()
	public boolean isCurrentPage(WebElement title, String text) {
		if (getText(title).equalsIgnoreCase(text))
			return true;
		else
			return false;
	}

	@Step()
	public void jScriptPopScreen() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('Welcome To My Automation Testing');");
		Alert alt = driver.switchTo().alert();
		System.out.println(alt.getText());
		sleep(1000);
		alt.accept();
	}

	@Step()
	public void refreshPage() {
		driver.navigate().refresh();
	}

	// close driver
	@Step()
	public void tearDown() {
		driver.close();
	}
	
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
