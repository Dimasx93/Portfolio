package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.*;

public class Sample4Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void enterNumber() throws Exception {

//        enter a number under "Number"
        String number = "10";
        WebElement numberInputField = driver.findElement(By.id("number"));
        numberInputField.clear();
        numberInputField.sendKeys(number);

//        check that button is not clickable "Clear Result"
        WebElement clearResultsButton = driver.findElement(By.id("clear_result_button_number"));
        assertFalse(clearResultsButton.isEnabled());

//        check that text is not displayed
        WebElement resultText = driver.findElement(By.id("result_number"));
        assertFalse(resultText.isDisplayed());

//        click on "Result" button
        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        resultButton.click();

//        check that text is displayed
        assertTrue(resultText.isDisplayed());

//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        String expectedText = "You entered number: " + "\"" + number + "\"";
        assertEquals(expectedText, resultText.getText());

//        check that the button "Clear Result" is clickable now
        assertTrue(clearResultsButton.isEnabled());

//        click on "Clear Result"
        clearResultsButton.click();

//        check that the text is now (""), but it is not displayed
        assertFalse(resultText.isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());
//        click on "This is a link to Homepage"
        WebElement linkToHomepage = driver.findElement(By.id("homepage_link"));
        linkToHomepage.click();
//        check that current url is not base_url
        assertNotEquals(base_url, driver.getCurrentUrl());
//        verify that current url is homepage
        String homepageUrl = "https://kristinek.github.io/site/";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(homepageUrl, actualUrl, "Current URL is not the homepage");
    }

}
