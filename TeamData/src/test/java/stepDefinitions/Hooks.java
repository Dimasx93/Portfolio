package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.net.MalformedURLException;
import java.time.Duration;

public class Hooks { // like the before and after steps
    public static WebDriver driver;
    static String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator; // path to the drivers, for the sake of consistency - kept the folder structure

    @Before
    public void openBrowser() throws MalformedURLException {
        if (System.getProperty("os.name").contains("Mac") || System.getProperty("os.name").contains("mac")) // For Macs
            System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver");
        else
            System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe"); // Windows needs to know the extension of the exe file
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies(); //driver.manage().VARIOUSOPTIONS ( windows sizes and other things)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // adding a default wait
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                scenario.log("Current Page URL is " + driver.getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screenshot");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
        driver.quit();
    }
}
