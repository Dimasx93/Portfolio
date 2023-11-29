package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class ExamplesSteps {
    private WebDriver driver;

    public ExamplesSteps() {
        this.driver = Hooks.driver;
    }

    @When("I go to URL {string}")
    public void iGotToURL(String arg0) throws InterruptedException {
        driver.get(arg0);
    }

    @And("I enter name {string} into text field")
    public void iEnterNameIntoTextField(String arg0) {
        WebElement textInput = driver.findElement(By.id("text"));
        textInput.clear();
        textInput.sendKeys(arg0);
    }

    @And("I enter integer {int} into number field")
    public void iEnterNumberIntoAgeField(int arg0) {
        WebElement numberInput = driver.findElement(By.id("number"));
        numberInput.clear();
        numberInput.sendKeys(String.valueOf(arg0));
    }

    @And("I wait {int} seconds")
    public void iWaitSeconds(int arg0) throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(arg0).toMillis());
    }

    @And("I enter double {double} into number field")
    public void iEnterNumberIntoNumberField(double arg0) {
        WebElement numberInput = driver.findElement(By.id("number"));
        numberInput.clear();
        numberInput.sendKeys(String.valueOf(arg0));
    }

    @And("I enter commadouble {double} into number field")
    public void iEnterCommadoubleIntoNumberField(double arg0) {
        WebElement numberInput = driver.findElement(By.id("number"));
        numberInput.clear();
        numberInput.sendKeys(String.valueOf(arg0));
    }

    @And("I click {adj} link")
    public void iClickGreatLink() {
    }

    // Can't escape / character itself: https://github.com/cucumber/cucumber-expressions#escaping
    @And("I enter bad/good/mediocre text")
    public void iEnterBadText() {
    }

    @And("I eat {} banana(s)")
    public void iEatOneBanana(String word) {
    }

    @And("I enter number {int}")
    public void iEnterNumber(int number) {
    }

    @When("I clicked on checkboxes modified:")
    public void iClickedOnCheckboxesModified(List<String> values) throws Throwable {
        for (String value : values) {
            List<WebElement> elements = driver.findElements(By.cssSelector("[value='" + value + "'][type='checkbox']"));
            System.out.println(elements.size());
            if(elements.size() == 1) {
                driver.findElement(By.cssSelector("[value='" + value + "'][type='checkbox']")).click();
            }
        }
    }

    @When("I sum the numbers")
    public void iSumNumbers(List<Double> values) {
        System.out.println("List of doubles we received: " + values);
        double sum = 0;
        for(double i : values) {
            sum += i;
        }
        System.out.println("Sum of all doubles received: " + sum);
    }
}
