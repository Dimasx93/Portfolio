package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import selenium.utility.BootcampUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Sample7Task {
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
    public void selectCheckBox() throws Exception {
//         TODO:
//        check that none of the checkboxes are ticked
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());
        }
        // Tick "Option 2"
        WebElement option2 = checkBoxes.get(1);
        option2.click();
        // Check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(checkBoxes.get(0).isSelected());
        assertTrue(option2.isSelected());
        assertFalse(checkBoxes.get(2).isSelected());
        // Tick "Option 3"
        WebElement option3 = checkBoxes.get(2);
        option3.click();
        // Click the result button
        WebElement resultButton = driver.findElement(By.id("result_button_checkbox"));
        resultButton.click();
        // Check that the text 'You selected value(s): Option 2, Option 3' is being displayed
        WebElement resultText = driver.findElement(By.id("result_checkbox"));
        assertEquals("You selected value(s): Option 2, Option 3", resultText.getText());
    }

    @Test
    public void selectRadioButton() throws Exception {
        // Check that none of the radio buttons are selected
        List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@type='radio']"));
        for (WebElement radioButton : radioButtons) {
            assertFalse(radioButton.isSelected());
        }
        // Select "Option 3"
        WebElement option3 = radioButtons.get(2);
        option3.click();
        // Check that "Option 1" and "Option 2" are not selected, but "Option 3" is selected
        assertFalse(radioButtons.get(0).isSelected());
        assertFalse(radioButtons.get(1).isSelected());
        assertTrue(option3.isSelected());
        // Select "Option 1"
        WebElement option1 = radioButtons.get(0);
        option1.click();
        // Check that "Option 2" and "Option 3" are not selected, but "Option 1" is selected
        assertFalse(radioButtons.get(1).isSelected());
        assertFalse(radioButtons.get(2).isSelected());
        assertTrue(option1.isSelected());
        // Click the result button
        WebElement resultButton = driver.findElement(By.id("result_button_ratio"));
        resultButton.click();
        // Check that the text 'You selected option: Option 1' is being displayed
        WebElement resultText = driver.findElement(By.id("result_radio"));
        assertEquals("You selected option: Option 1", resultText.getText());
    }

    @Test
    public void selectOption() throws Exception {
        Select dropdown = new Select(driver.findElement(By.xpath("//select[@name='vfb-12']")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
//        select "Option 3" in Select
        dropdown.selectByValue("value3");
//        check that selected option is "Option 3"
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());
//        select "Option 2" in Select
        dropdown.selectByIndex(2);
//        check that selected option is "Option 2"
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
//        click result
        WebElement resultButton = driver.findElement(By.xpath("//button[@id='result_button_select']"));
        resultButton.click();
//        check that 'You selected option: Option 2' text is being displayed
        String expected = "You selected option: Option 2";
        assertEquals(expected, driver.findElement(By.xpath("//p[@id='result_select']")).getText());
    }

//    @Test
//    public void chooseDateViaCalendarBonus() throws Exception {
//        // Click the input field to open the calendar widget
//        WebElement dateInputField = driver.findElement(By.id("vfb-8"));
//        dateInputField.click();
//
//        // Select the desired date '7th April 2007'
//        selectDateFromCalendar();
//
//        // Check that the correct date is added to the input field
//        assertEquals("07/04/2007", dateInputField.getAttribute("value"));
//    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
        String dateToEnter = "05/02/1959";

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        dateBox.clear();
        dateBox.sendKeys(dateToEnter);
//        check that correct date is added
        assertEquals(dateToEnter, dateBox.getAttribute("value"));
    }

//    private void clickNextUntilDesiredDate(String desiredYear, String desiredMonth) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        while (true) {
//            WebElement yearElement = driver.findElement(By.className("ui-datepicker-year"));
//            WebElement monthElement = driver.findElement(By.className("ui-datepicker-month"));
//
//            if (yearElement.getText().equals(desiredYear) && monthElement.getText().equals(desiredMonth)) {
//                break;  // Exit the loop if the desired date is reached
//            }
//
//            // Click the button to navigate to the next month
//            WebElement nextButton = driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-w']"));
//            nextButton.click();
//
//            // Wait for the year and month elements to update
//            wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(By.className("ui-datepicker-year"), yearElement.getText())));
//            wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(By.className("ui-datepicker-month"), monthElement.getText())));
//        }
//    }

//    private void selectDateFromCalendar() {
//        WebElement dateInput = driver.findElement(By.id("datepicker"));
//        dateInput.click();
//        String desiredYear = "2007";
//        String desiredMonth = "April";
//        String targetDay = "7";
//        clickNextUntilDesiredDate(desiredYear, desiredMonth);
//        WebElement dayElement = driver.findElement(By.xpath("//a[text()='" + targetDay + "']"));
//        dayElement.click();
//    }

}
