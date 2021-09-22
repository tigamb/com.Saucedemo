package tests;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.google.common.collect.ImmutableMap;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.Utils;

public class BaseTest {
	WebDriver driver;
	WebDriverWait wait;
	String BrowserVersion;
	String BrowserName;
	DesiredCapabilities capabilities;


	@BeforeClass
	public void setup(ITestContext testContext) {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-blink-features=AutomationControlled");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		testContext.setAttribute("WebDriver", this.driver);
		driver.get(Utils.readProperty("url"));

	}
	
	
	// Other Option
	@BeforeClass(enabled=false)
	@Parameters({ "browser", "baseUrl" })
	@SuppressWarnings("deprecation")
	protected void setup1(ITestContext testContext, String browser, String baseUrl) throws MalformedURLException {
		if ("true".equals(System.getProperty("debug"))) {
			ChromeOptions cOptions = new ChromeOptions();
			cOptions.addArguments("diable-infobars");
			driver = new ChromeDriver(cOptions);
			BrowserVersion = ((RemoteWebDriver) driver).getCapabilities().getVersion();
			BrowserName = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
		} else if (browser.equals("firefox")) {
			capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("firefox");
			capabilities.setVersion("63.0");
			capabilities.setCapability("enableVNC", true);
			capabilities.setCapability("screenResolution", "1280x1024x24");
			driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);
			BrowserVersion = capabilities.getVersion();
			BrowserName = capabilities.getBrowserName();
		}
		testContext.setAttribute("WebDriver", this.driver);
		allureEnvironmentWriter(ImmutableMap.<String, String>builder().put("Browser", BrowserName)
				.put("Browser.Version", BrowserVersion).put("URL", baseUrl).build());
		driver.get(baseUrl);

	}

	@AfterClass(enabled = true)
	public void tearDown() {
		driver.quit();
	}

	
	@AfterMethod
	public void failedTest(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File srcFile = ts.getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(srcFile, new File("./ScreenShots/" + result.getName() + ".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

}
