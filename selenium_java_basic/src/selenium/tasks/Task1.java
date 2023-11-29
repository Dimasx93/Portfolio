package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {

        driver = BootcampUtils.initializeChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @AfterEach
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void errorOnText() {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.clear();
        inputField.sendKeys("Twenty");

        WebElement submitButton = driver.findElement(By.xpath("//button[@type='button']"));
        submitButton.click();

        WebElement errMessage = driver.findElement(By.id("ch1_error"));
        assertTrue(errMessage.isDisplayed());

        String expectedMsg = "Please enter a number";
        String actualMsg = errMessage.getText();
        assertEquals(expectedMsg, actualMsg, "Messages are not equal.");

    }

    @Test
    public void errorOnNumberTooSmall() {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen
        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.clear();
        inputField.sendKeys("45");

        WebElement submitButton = driver.findElement(By.xpath("//button[@type='button']"));
        submitButton.click();

        WebElement errMessage = driver.findElement(By.id("ch1_error"));
        assertTrue(errMessage.isDisplayed());

        String expectedMsg = "Number is too small";
        String actualMsg = errMessage.getText();
        assertEquals(expectedMsg, actualMsg, "Messages are not equal.");
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.clear();
        inputField.sendKeys("101");

        WebElement submitButton = driver.findElement(By.xpath("//button[@type='button']"));
        submitButton.click();

        WebElement errMessage = driver.findElement(By.id("ch1_error"));
        assertTrue(errMessage.isDisplayed());

        String expectedMsg = "Number is too big";
        String actualMsg = errMessage.getText();
        assertEquals(expectedMsg, actualMsg, "Messages are not equal.");
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code
        String toCalculate = "64";
        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.clear();
        inputField.sendKeys(toCalculate);

        WebElement submitButton = driver.findElement(By.xpath("//button[@type='button']"));
        submitButton.click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        double decimalResult = Double.parseDouble(toCalculate);
        double calculateSquareRoot = Math.sqrt(decimalResult);

        DecimalFormat decimalFormat = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        String resultSquareRoot = decimalFormat.format(calculateSquareRoot);

        String result = "Square root of " + toCalculate + " is " + resultSquareRoot;

        assertEquals(result, alertText);

        alert.accept();
    }
}
