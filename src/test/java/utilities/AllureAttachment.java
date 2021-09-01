package utilities;

import java.nio.charset.StandardCharsets;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import com.github.underscore.lodash.U;
import java.util.stream.Collectors;
import io.qameta.allure.Attachment;


public class AllureAttachment {
	
	@Attachment(value = "{0}", type = "text/plain", fileExtension = ".txt")
	public static String attachText(String message) {
		return message;
	}

	@Attachment(value = "Html source", type = "text/html", fileExtension = ".html")
	public static byte[] getPageSource(WebDriver driver) {
		return driver.getPageSource().getBytes(StandardCharsets.UTF_8);
	}

	@Attachment(value = "Page Screenshot", type = "image/png", fileExtension = ".png")
	static byte[] attachScreenshot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	@Attachment(value = "Console Logs", type = "text/plain", fileExtension = ".txt")
	public static String attachConsoleLogs(WebDriver driver) {
		String consoleLogs = driver.manage().logs().get(LogType.BROWSER).getAll().stream().map(LogEntry::toString)
				.collect(Collectors.joining(System.lineSeparator()));
		return StringUtils.isBlank(consoleLogs) ? "No Console Logs Found" : consoleLogs;

	}

	@Attachment(value = "Element Screenshot", type = "image/png", fileExtension = ".png")
	public static byte[] attachElementScreenshot(WebElement element) {
		return element.getScreenshotAs(OutputType.BYTES);
	}

	@Attachment(value = "JSON attachment", type = "application/json", fileExtension = ".json")
	public static String attachJson(String json) {
		return U.formatJson(json);
	}

	@Attachment(value = "XML attachment", type = "application/xml", fileExtension = ".xml")
	public static String attachXml(String xml) {
		return U.formatXml(xml);

	}

	@Attachment(value = "CSV attachment", type = "text/csv", fileExtension = ".csv")
	public static String attachCSV(String csv) {
		return csv;
	}

	@Attachment(value = "URL attachment", type = "text/uri-list", fileExtension = ".uri")
	public static String attachURL(String url) {
		return url;
	}

}
