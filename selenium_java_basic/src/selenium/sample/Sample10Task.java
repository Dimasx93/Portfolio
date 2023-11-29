package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.ColorSamplePage;
import selenium.utility.BootcampUtils;

public class Sample10Task {
    static WebDriver driver;
    static ColorSamplePage colorPage;

    @BeforeEach
    public void openPage() {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // Set timeout and open page
        driver.get("https://kristinek.github.io/site/examples/loading_color");
        colorPage = PageFactory.initElements(driver, ColorSamplePage.class);
    }

    @AfterEach
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//         Use page object ColorSamplePage
//         * 1) click on start loading green button
        colorPage.clickStartLoadingGreen();
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        colorPage.verifyThatStartLoadingGreenButtonIsNotDisplayed();
        colorPage.verifyThatLoadingGreenIsDisplayed();
//         * 3) check that both button
        colorPage.verifyThatStartLoadingGreenButtonIsNotDisplayed();
//         * and loading text is not seen,
        colorPage.verifyThatLoadingGreenIsNotDisplayed();
//         * success is seen instead "Green Loaded"
        colorPage.verifyThatGreenLoadedIsDisplayed();
    }

}
