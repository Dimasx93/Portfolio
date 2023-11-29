package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.util.List;

public class Sample2Task {
    WebDriver driver;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get("https://kristinek.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void findElementByID() throws Exception {
//         TODO:
//         get text "Heading 2 text" using id
        WebElement header2 = driver.findElement(By.id("heading_2"));
        System.out.println(header2.getText());
    }

    @Test
    public void findElementByName() throws Exception {
//         TODO:
//         get attribute "id" and "value" of button "This is also a button" using name
        WebElement thisIsAlsoAButton = driver.findElement(By.name("randomButton2"));
        System.out.println(thisIsAlsoAButton.getAttribute("id"));
        System.out.println(thisIsAlsoAButton.getAttribute("value"));
    }

    @Test
    public void findElementByClassFirst() throws Exception {
//         TODO:
//         get first text of class "test" (should be "Test Text 1")
        List<WebElement> allElementsWithClassTest = driver.findElements(By.className("test"));
        System.out.println(allElementsWithClassTest.get(0).getText());
    }

    @Test
    public void findElementByClassAll() throws Exception {
//         TODO:
//         get size text of class "test" (should be 5)
        List<WebElement> allElementsWithClassTest = driver.findElements(By.className("test"));
        System.out.println("Size text of class 'test': " + allElementsWithClassTest.size());
//         get text of class "test"
        for (WebElement element : allElementsWithClassTest) {
            String text = element.getText();
            System.out.println("Text in class 'test': " + text);
        }
//         get third text of class "test" (should be "Test Text 4")
        String thirdText = allElementsWithClassTest.get(2).getText();
        System.out.println("Third text in class 'test': " + thirdText);
    }

}
