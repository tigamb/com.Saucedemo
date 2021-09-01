package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listener extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult result) {
		Object webDriverAttribute = result.getTestContext().getAttribute("WebDriver");
		if (webDriverAttribute instanceof WebDriver) {
			AllureAttachment.attachScreenshot((WebDriver) webDriverAttribute);
			// AllureAttachment.getPageSource((WebDriver) webDriverAttribute);
			// AllureAttachment.attachConsoleLogs((WebDriver) webDriverAttribute);

		}
	}

}
