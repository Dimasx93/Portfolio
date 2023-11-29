package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Sample9Task {
    WebDriver driver;

    private static WebDriverWait wait;

    @BeforeEach
    public void openPage() {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class);

        // load web page
        driver.get("https://kristinek.github.io/site/examples/loading_color");
    }

    @AfterEach
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        WebElement startLoadingGreenButton = driver.findElement(By.xpath("//button[@id='start_green']"));
        startLoadingGreenButton.click();
//         * 2) check that button does not appear,
        assertFalse(startLoadingGreenButton.isDisplayed());
//         * but loading text is seen instead   "Loading green..."
        WebElement loadingGreen = driver.findElement(By.xpath("//div[@id='loading_green']"));
        assertTrue(loadingGreen.isDisplayed());
//         * 3) check that both button
        assertFalse(startLoadingGreenButton.isDisplayed());
//         * and loading text is not seen,
        assertTrue(loadingGreen.isDisplayed());
//         * success is seen instead "Green Loaded"
        Thread.sleep(5000);
        WebElement greenLoaded = driver.findElement(By.xpath("//h2[@id='finish_green']"));
        assertTrue(greenLoaded.isDisplayed());
    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         * 1) click on start loading green button
        WebElement startLoadingGreenButton = driver.findElement(By.xpath("//button[@id='start_green']"));
        startLoadingGreenButton.click();
//         * 2) check that button does not appear,
        assertFalse(startLoadingGreenButton.isDisplayed());
//         * but loading text is seen instead   "Loading green..."
        WebElement loadingGreen = driver.findElement(By.xpath("//div[@id='loading_green']"));
        assertTrue(loadingGreen.isDisplayed());
//         * 3) check that both button
        assertFalse(startLoadingGreenButton.isDisplayed());
//         * and loading text is not seen,
        assertTrue(loadingGreen.isDisplayed());
//         * success is seen instead "Green Loaded"
        WebElement greenLoaded = driver.findElement(By.xpath("//h2[@id='finish_green']"));
        assertTrue(greenLoaded.isDisplayed());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        WebElement startLoadingGreenButton = driver.findElement(By.xpath("//button[@id='start_green']"));
        wait.until(ExpectedConditions.elementToBeClickable(startLoadingGreenButton));
        startLoadingGreenButton.click();
//         * 2) check that button does not appear,
        wait.until(ExpectedConditions.invisibilityOf(startLoadingGreenButton));
        assertFalse(startLoadingGreenButton.isDisplayed());
//         * but loading text is seen instead   "Loading green..."
        WebElement loadingGreen = driver.findElement(By.xpath("//div[@id='loading_green']"));
        wait.until(ExpectedConditions.visibilityOf(loadingGreen));
        assertTrue(loadingGreen.isDisplayed());
//         * 3) check that both button
        wait.until(ExpectedConditions.invisibilityOf(startLoadingGreenButton));
        assertFalse(startLoadingGreenButton.isDisplayed());
//         * and loading text is not seen,
        wait.until(ExpectedConditions.invisibilityOf(loadingGreen));
        assertFalse(loadingGreen.isDisplayed());
//         * success is seen instead "Green Loaded"
        WebElement greenLoaded = driver.findElement(By.xpath("//h2[@id='finish_green']"));
        wait.until(ExpectedConditions.visibilityOf(greenLoaded));
        assertTrue(greenLoaded.isDisplayed());
    }

    @Test
    public void loadGreenAndBlueBonus() throws InterruptedException {
//         TODO:
//         * 0) wait until button to load green and blue appears
        WebElement startLoadingGreenAndBlueButton = driver.findElement(By.xpath("//button[@id='start_green_and_blue']"));
        wait.until(ExpectedConditions.visibilityOf(startLoadingGreenAndBlueButton));
//         * 1) click on start loading green and blue button
        startLoadingGreenAndBlueButton.click();
//         * 2) check that button does not appear, but loading text is seen instead for green
        wait.until(ExpectedConditions.invisibilityOf(startLoadingGreenAndBlueButton));
        assertFalse(startLoadingGreenAndBlueButton.isDisplayed());
        WebElement loadingGreenWithoutBlue = driver.findElement(By.xpath("//div[@id='loading_green_without_blue']"));
        wait.until(ExpectedConditions.visibilityOf(loadingGreenWithoutBlue));
        assertTrue(loadingGreenWithoutBlue.isDisplayed());
//         * 3) check that button does not appear, but loading text is seen instead for green and blue
        assertFalse(startLoadingGreenAndBlueButton.isDisplayed());
        WebElement loadingGreenWithBlue;
        try {
            loadingGreenWithBlue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_with_blue")));
        } catch (StaleElementReferenceException e) {
            loadingGreenWithBlue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_with_blue")));
        }
        assertTrue(loadingGreenWithBlue.isDisplayed());
//         * 4) check that button and loading green does not appear,
        assertFalse(startLoadingGreenAndBlueButton.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(loadingGreenWithoutBlue));
        assertFalse(loadingGreenWithoutBlue.isDisplayed());
//         * 		but loading text is seen instead for blue and success for green is seen
        wait.until(ExpectedConditions.visibilityOf(loadingGreenWithBlue));
        assertTrue(loadingGreenWithBlue.isDisplayed());
        WebElement loadingBlueWithoutGreen = driver.findElement(By.id("loading_blue_without_green"));
        assertTrue(loadingBlueWithoutGreen.isDisplayed());
//         * 5) check that both button and loading text is not seen, success is seen instead
        assertFalse(startLoadingGreenAndBlueButton.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(loadingGreenWithBlue));
        assertFalse(loadingGreenWithBlue.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(loadingBlueWithoutGreen));
        assertFalse(loadingBlueWithoutGreen.isDisplayed());
        WebElement successMessage = driver.findElement(By.id("finish_green_and_blue"));
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        assertTrue(successMessage.isDisplayed());
    }

}
