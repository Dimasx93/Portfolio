package cucumber.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.*;

public class Task1Steps {
    private WebDriver driver;

    public Task1Steps() {
        this.driver = Hooks.driver;
    }

    @Given("I am on enter a number page")
    public void iAmOnEnteraNumberPage() {
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @When("I enter {string}")
    public void iEnter(String input) {
        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.clear();
        inputField.sendKeys(input);
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='button']"));
        submitButton.click();
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        assertNotNull(errorMessage);
        assertTrue(errorMessage.isDisplayed());
    }

    @Given("I am on the Enter a Number page")
    public void iAmOnTheEnterANumberPage() {
    }

    @Then("I should see a success message")
    public void iShouldSeeASuccessMessage() {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        assertNotNull(alertText);
        alert.accept();
    }
}