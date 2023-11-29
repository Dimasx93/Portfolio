package selenium.sample;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;

public class Sample1Task {

    WebDriver driver;

    @Test
    public void goToHomepage() throws Exception {
//        TODO:
//         define driver
        driver = BootcampUtils.initializeChromeDriver();
//         go to https://kristinek.github.io/site/index2.html
        driver.get("https://kristinek.github.io/site/index2.html");
//         get title of page
        System.out.println(driver.getTitle());
//         get URL of current page
        System.out.println(driver.getCurrentUrl());
//         close browser
        driver.quit();
    }

}
