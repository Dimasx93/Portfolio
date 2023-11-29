package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;

public class Sample6Task {
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
        driver.close();
    }

    @Test
    public void findElementByXPath() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text":
        System.out.println(driver.findElement(
                By.xpath("//h2[@id='heading_2']")).getText());
        System.out.println(driver.findElement(
                By.xpath("//*[@id='heading_2']")).getText());
//        1-2 ways to find text: "Test Text 1"
        System.out.println(driver.findElement(
                By.xpath("(//p[@class='test'])[1]")).getText());
        System.out.println(driver.findElement(
                By.xpath("//p[@class='test' and text()='Test Text 1']")).getText());
//        1-2 ways to find text: "Test Text 2"
        System.out.println(driver.findElement(
                By.xpath("//p[@class='twoTest']")).getText());
        System.out.println(driver.findElement(
                By.xpath("//p[@class='twoTest' and text()='Test Text 2']")).getText());
//        1-2 ways to find text: "Test Text 3"
        System.out.println(driver.findElement(
                By.xpath("(//p[@class='test'])[2]")).getText());
        System.out.println(driver.findElement(
                By.xpath("//p[@class='test' and text()='Test Text 3']")).getText());
//        1-2 ways to find text: "Test Text 4"
        System.out.println(driver.findElement(
                By.xpath("(//p[@class='test'])[3]")).getText());
        System.out.println(driver.findElement(
                By.xpath("//p[@class='test' and text()='Test Text 4']")).getText());
        //cssSelector
        System.out.println(driver.findElement(
                By.cssSelector("p.test:nth-of-type(2)")).getText());
//        1-2 ways to find text: "Test Text 5"
        System.out.println(driver.findElement(
                By.xpath("//p[@class='Test']")).getText());
        System.out.println(driver.findElement(
                        By.xpath("//p[@class='Test' and text()='Test Text 5']"))
                .getText());
        //cssSelector
        System.out.println(driver.findElement(
                By.cssSelector("#test2 > p.Test")).getText());
//        1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(
                By.xpath("//input[@name='randomButton2']")).getAttribute("value"));
        System.out.println(driver.findElement(
                        By.xpath("//input[@type='button' and @id='buttonId' and @name='randomButton2']"))
                .getAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
        System.out.println(driver.findElement(By.cssSelector("h2#heading_2")).getText());
//        1-2 ways to find text: "Test Text 1"
        System.out.println(driver.findElement(By.cssSelector("p.test:nth-of-type(1)")).getText());
//        1-2 ways to find text: "Test Text 2"
        System.out.println(driver.findElement(By.cssSelector("p.twoTest")).getText());
//        1-2 ways to find text: "Test Text 3"
        System.out.println(driver.findElement(By.cssSelector("#test3 > p:nth-child(1)")).getText());
//        1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.cssSelector("input[name='randomButton1']")).getAttribute("value"));
    }
}
