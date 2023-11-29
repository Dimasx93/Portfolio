package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sample8Task {
    WebDriver driver;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get("https://kristinek.github.io/site/examples/po");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void styleChecks() throws Exception {
//         TODO:
        WebElement pinkContainer = driver.findElement(By.xpath("//div[@class='w3-container w3-pale-red']"));
        WebElement yellowContainer = driver.findElement(By.xpath("//div[@class='w3-container w3-pale-yellow']"));

        String pinkBackgroundColor = pinkContainer.getCssValue("background-color");
        String yellowBackgroundColor = yellowContainer.getCssValue("background-color");

        String expectedPinkBackgroundColor = "rgba(255, 221, 221, 1)";
        String expectedYellowBackgroundColor = "rgba(255, 255, 204, 1)";

        assertEquals(expectedPinkBackgroundColor, pinkBackgroundColor, "Pink container background color is incorrect");
        assertEquals(expectedYellowBackgroundColor, yellowBackgroundColor, "Yellow container background color is incorrect");

        WebElement header = driver.findElement(By.className("w3-jumbo"));
        String fontSizeValue = header.getCssValue("font-size");
        String expectedFontSize = "64px";
        assertEquals(expectedFontSize, fontSizeValue, "Header font size is incorrect");
    }

}
