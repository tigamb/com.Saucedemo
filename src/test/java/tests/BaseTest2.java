
package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


public class BaseTest2 {
    WebDriver driver;

    @Parameters({"browser"})
    @BeforeClass
    public void setup(@Optional("Chrome") String browser)
    {
        switch (browser)
        {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "explorer":
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions capabilities = new InternetExplorerOptions();
                capabilities.ignoreZoomSettings();
                driver = new InternetExplorerDriver(capabilities);
                break;
            default: throw new IllegalArgumentException("NO such browser " + browser);
        }
    }
}