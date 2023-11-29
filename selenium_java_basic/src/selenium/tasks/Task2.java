package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;
import java.time.Duration;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {
        driver = BootcampUtils.initializeChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

    @AfterEach
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
        //Check that all field are empty and no ticks are clicked
        WebElement inputName = driver.findElement(By.id("fb_name"));
        inputName.clear();

        WebElement inputAge = driver.findElement(By.id("fb_age"));
        inputAge.clear();

        // Check that all checkboxes are not selected
        List<WebElement> listOfAllBoxes = new ArrayList<>();
        listOfAllBoxes.add(driver.findElement(By.xpath("//input[@value='English']")));
        listOfAllBoxes.add(driver.findElement(By.xpath("//input[@value='French']")));
        listOfAllBoxes.add(driver.findElement(By.xpath("//input[@value='Spanish']")));
        listOfAllBoxes.add(driver.findElement(By.xpath("//input[@value='Chinese']")));

        for (WebElement boxes : listOfAllBoxes) {
            String checkBoxValue = boxes.getAttribute("value");
            if (!boxes.isSelected()) {
                System.out.println("Checkbox with value '" + checkBoxValue + "' is not selected");
            } else {
                System.out.println("Checkbox with value '" + checkBoxValue + "' is selected");
            }
        }

        // Check 'Don't know' radio button
        WebElement genderDontKnow = driver.findElement(By.xpath("//input[@class='w3-radio' and @type='radio' and @name='gender' and @disabled='']"));
        if (!genderDontKnow.isSelected()) {
            System.out.println("Gender 'Don't know' radio button is not selected");
        } else {
            System.out.println("Gender 'Don't know' radio button is selected");
        }

        // Check 'Choose your option' dropdown
        WebElement chooseYourOption = driver.findElement(By.xpath("//select[@id='like_us']/option[text()='Choose your option']"));
        if (chooseYourOption.isDisplayed()) {
            System.out.println("'Choose your option' in 'How do you like us?' is displayed");
        } else {
            System.out.println("'Choose your option' in 'How do you like us?' is not displayed");
        }
        //Check that the button send is blue with white letters
        WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
        String backgroundColor = submit.getCssValue("background-color");
        String textColor = submit.getCssValue("color");
        assertEquals("rgba(33, 150, 243, 1)", backgroundColor, "Background color is not rgb(33, 150, 243)");
        assertEquals("rgba(255, 255, 255, 1)", textColor, "Text color is not rgb(255, 255, 255)");
    }



    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
        submit.click();
//         check fields are empty or "null"
        List<WebElement> listOfAllFields = new ArrayList<>();
        listOfAllFields.add(driver.findElement(By.xpath("//div[@class='description']//span[@id='name']")));
        listOfAllFields.add(driver.findElement(By.xpath("//div[@class='description']//span[@id='age']")));
        listOfAllFields.add(driver.findElement(By.xpath("//div[@class='description']//span[@id='language']")));
        listOfAllFields.add(driver.findElement(By.xpath("//div[@class='description']//span[@id='gender']")));
        listOfAllFields.add(driver.findElement(By.xpath("//div[@class='description']//span[@id='option']")));
        listOfAllFields.add(driver.findElement(By.xpath("//div[@class='description']//span[@id='comment']")));
        for (WebElement span : listOfAllFields) {
            String spanText = span.getText().trim();
            if (spanText.isEmpty() || spanText.equalsIgnoreCase("null")) {
                System.out.println("Span with ID '" + span.getAttribute("id") + "' is empty or contains 'null'");
            } else {
                System.out.println("Span with ID '" + span.getAttribute("id") + "' has text: " + spanText);
            }
        }
//         check button colors
//         (green with white letter and red with white letters)
        WebElement yesButton = driver.findElement(By.xpath("//button[@class='w3-btn w3-green w3-xlarge']"));
        WebElement noButton = driver.findElement(By.xpath("//button[@class='w3-btn w3-red w3-xlarge']"));
        String backgroundColorGreen = yesButton.getCssValue("background-color");
        String textColorGreen = yesButton.getCssValue("color");
        String backgroundColorRed = noButton.getCssValue("background-color");
        String textColorRed = noButton.getCssValue("color");
        assertEquals("rgba(76, 175, 80, 1)", backgroundColorGreen, "Background color is not rgb(76, 175, 80, 1)");
        assertEquals("rgba(255, 255, 255, 1)", textColorGreen, "Text color is not rgb(255, 255, 255)");
        assertEquals("rgba(244, 67, 54, 1)", backgroundColorRed, "Background color is not rgb(244, 67, 54, 1)");
        assertEquals("rgba(255, 255, 255, 1)", textColorRed, "Text color is not rgb(255, 255, 255)");
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        WebElement inputName = driver.findElement(By.xpath("//input[@id='fb_name']"));
        inputName.clear();
        inputName.sendKeys("Stefano");
        WebElement inputAge = driver.findElement(By.xpath("//input[@id='fb_age']"));
        inputAge.clear();
        inputAge.sendKeys("29");
        WebElement inputLanguage = driver.findElement(By.xpath("//input[@value='English']"));
        inputLanguage.click();
        WebElement inputGenre = driver.findElement(By.xpath("//input[@value='male']"));
        inputGenre.click();
        WebElement inputOption = driver.findElement(By.xpath("//select[@id='like_us']"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        inputOption.click();
        WebElement optionGood = driver.findElement(By.xpath("//select[@id='like_us']//option[@value='Good']"));
        optionGood.click();
        assertEquals("Good", optionGood.getText());
        WebElement inputComment = driver.findElement(By.xpath("//textarea[@name='comment']"));
        inputComment.clear();
        inputComment.sendKeys("ciao.");
        WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
        submit.click();

//         check fields are filled correctly
        WebElement subName = driver.findElement(By.xpath("//div[@class='description']//span[@id='name']"));
        assertEquals("Stefano", subName.getText());
        WebElement subAge = driver.findElement(By.xpath("//div[@class='description']//span[@id='age']"));
        assertEquals("29", subAge.getText());
        WebElement subLanguage = driver.findElement(By.xpath("//div[@class='description']//span[@id='language']"));
        assertEquals("English", subLanguage.getText());
        WebElement subGenre = driver.findElement(By.xpath("//div[@class='description']//span[@id='gender']"));
        assertEquals("male", subGenre.getText());
        WebElement subOption = driver.findElement(By.xpath("//div[@class='description']//span[@id='option']"));
        assertEquals("Good", subOption.getText());
        WebElement subComment = driver.findElement(By.xpath("//div[@class='description']//span[@id='comment']"));
        assertEquals("ciao.", subComment.getText());

//         check button colors
//         (green with white letter and red with white letters)
        WebElement yesButton = driver.findElement(By.xpath("//button[@class='w3-btn w3-green w3-xlarge']"));
        WebElement noButton = driver.findElement(By.xpath("//button[@class='w3-btn w3-red w3-xlarge']"));
        String backgroundColorGreen = yesButton.getCssValue("background-color");
        String textColorGreen = yesButton.getCssValue("color");
        String backgroundColorRed = noButton.getCssValue("background-color");
        String textColorRed = noButton.getCssValue("color");
        assertEquals("rgba(76, 175, 80, 1)", backgroundColorGreen, "Background color is not rgb(76, 175, 80, 1)");
        assertEquals("rgba(255, 255, 255, 1)", textColorGreen, "Text color is not rgb(255, 255, 255)");
        assertEquals("rgba(244, 67, 54, 1)", backgroundColorRed, "Background color is not rgb(244, 67, 54, 1)");
        assertEquals("rgba(255, 255, 255, 1)", textColorRed, "Text color is not rgb(255, 255, 255)");
        System.out.println("Actual test color: " + textColorGreen);
        System.out.println("Actual background color: " + backgroundColorGreen);
        System.out.println("Actual test color: " + textColorRed);
        System.out.println("Actual background color: " + backgroundColorRed);
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        WebElement inputName = driver.findElement(By.xpath("//input[@id='fb_name']"));
        inputName.clear();
        inputName.sendKeys("Stefano");
//         click "Send"
        WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
        submit.click();
//         click "Yes"
        WebElement yesButton = driver.findElement(By.xpath("//button[@class='w3-btn w3-green w3-xlarge']"));
        yesButton.click();
//         check message text: "Thank you, NAME, for your feedback!"
        WebElement greenMessage = driver.findElement(By.xpath("//div[@class='w3-panel w3-green']"));
        assertEquals("Thank you, Stefano, for your feedback!", greenMessage.getText());
//         color of text is white with green on the background
        String backgroundColor = greenMessage.getCssValue("background-color");
        String textColor = greenMessage.getCssValue("color");
        assertEquals("rgba(76, 175, 80, 1)", backgroundColor, "Background color is not rgb(76, 175, 80, 1)");
        assertEquals("rgba(255, 255, 255, 1)", textColor, "Text color is not rgb(255, 255, 255)");
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
        submit.click();
//         click "Yes"
        WebElement yesButton = driver.findElement(By.xpath("//button[@class='w3-btn w3-green w3-xlarge']"));
        yesButton.click();
//         check message text: "Thank you for your feedback!"
        WebElement greenMessage = driver.findElement(By.xpath("//div[@class='w3-panel w3-green']"));
        assertEquals("Thank you for your feedback!", greenMessage.getText());
//         color of text is white with green on the background
        String backgroundColor = greenMessage.getCssValue("background-color");
        String textColor = greenMessage.getCssValue("color");
        assertEquals("rgba(76, 175, 80, 1)", backgroundColor, "Background color is not rgb(76, 175, 80, 1)");
        assertEquals("rgba(255, 255, 255, 1)", textColor, "Text color is not rgb(255, 255, 255)");
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        WebElement inputName = driver.findElement(By.xpath("//input[@id='fb_name']"));
        inputName.clear();
        inputName.sendKeys("Stefano");
        WebElement inputAge = driver.findElement(By.xpath("//input[@id='fb_age']"));
        inputAge.clear();
        inputAge.sendKeys("29");
        WebElement inputLanguage = driver.findElement(By.xpath("//input[@value='English']"));
        inputLanguage.click();
        WebElement inputGenre = driver.findElement(By.xpath("//input[@value='male']"));
        inputGenre.click();
        WebElement inputOption = driver.findElement(By.xpath("//select[@id='like_us']"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        inputOption.click();
        WebElement optionGood = driver.findElement(By.xpath("//select[@id='like_us']//option[@value='Good']"));
        optionGood.click();
        assertEquals("Good", optionGood.getText());
        WebElement inputComment = driver.findElement(By.xpath("//textarea[@name='comment']"));
        inputComment.clear();
        inputComment.sendKeys("ciao.");
//         click "Send"
        WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
        submit.click();
//         click "No"
        WebElement noButton = driver.findElement(By.xpath("//button[@class='w3-btn w3-red w3-xlarge']"));
        noButton.click();
//         check fields are filled correctly
        assertEquals("Stefano", inputName.getAttribute("value"));
        assertEquals("29", inputAge.getAttribute("value"));
        assertEquals("English", inputLanguage.getAttribute("value"));
        assertEquals("male", inputGenre.getAttribute("value"));
        assertEquals("Good", inputOption.getAttribute("value"));
        assertEquals("ciao.", inputComment.getAttribute("value"));
    }
}
