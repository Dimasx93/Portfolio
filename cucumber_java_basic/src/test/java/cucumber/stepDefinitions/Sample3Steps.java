package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sample3Steps {
    private WebDriver driver;
    private final WebDriverWait wait;
    public Sample3Steps() {
        this.driver = Hooks.driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("I am on feedback page")
    public void iAmOnFeedbackPage() {
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

    @When("^Enter name: \"([^\"]*)\"$")
    public void enterName(String name) throws Throwable {
        driver.findElement(By.id("fb_name")).clear();
        driver.findElement(By.id("fb_name")).sendKeys(name);
    }

    @And("^Enter age: (\\d+)$")
    public void enterAge(int age) throws Throwable {
        driver.findElement(By.id("fb_age")).sendKeys(String.valueOf(age));
    }

    @And("^Enter language: \"([^\"]*)\"$")
    public void enterLanguage(String language) throws Throwable {
        driver.findElement(By.xpath("//input[@value='" + language + "']")).click();
    }

    @And("^Enter gender: \"([^\"]*)\"$")
    public void enterGender(String gender) throws Throwable {
        driver.findElement(By.xpath("//input[@value='" + gender + "']")).click();
    }

    @And("^Enter option: \"([^\"]*)\"$")
    public void enterOption(String option) {
        WebElement inputOption = driver.findElement(By.xpath("//select[@id='like_us']"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        inputOption.click();
        driver.findElement(By.xpath("//select[@id='like_us']//option[@value='" + option + "']"));
    }

    @And("^Enter comment: \"([^\"]*)\"$")
    public void enterComment(String comment) {
        driver.findElement(By.xpath("//textarea[@name='comment']")).clear();
        driver.findElement(By.xpath("//textarea[@name='comment']")).sendKeys(comment);
    }

    @Then("^Click submit send")
    public void clickSend() throws Throwable {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @When("I select feedback languages")
    public void iSelectFeedbackLanguages(List<String> values) throws Throwable {
        for (String value : values) {
            driver.findElement(By.xpath("//input[@value='" + value + "']")).click();
        }
    }

    @Then("I can see languages {string} in feedback check")
    public void iCanSeeLanguagesInFeedbackCheck(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.xpath("//div[@class='description']//span[@id='language']")).getText());
    }

    @When("Enter values:")
    public void enterValues(Map<String, String> feedback) {
        WebElement nameField = driver.findElement(By.id("fb_name"));
        WebElement ageField = driver.findElement(By.id("fb_age"));
        List<WebElement> genderRadio = driver.findElements(By.name("gender"));
        nameField.sendKeys(feedback.get("name"));
        ageField.sendKeys(feedback.get("age"));

        for (WebElement gender : genderRadio) {
            if (gender.getAttribute("value").equalsIgnoreCase(feedback.get("gender"))) {
                gender.click();
                break;
            }
        }

    }

    @And("I check whether my data is correct")
    public void iCheckWhetherMyDataIsCorrect(Map<String, String> valuesToEnter) {
        WebElement outputName = driver.findElement(By.xpath("//div[@class='description']//span[@id='name']"));
        WebElement outputAge = driver.findElement(By.xpath("//div[@class='description']//span[@id='age']"));
        WebElement outputGender = driver.findElement(By.xpath("//div[@class='description']//span[@id='gender']"));

        assertEquals(valuesToEnter.get("name"), outputName.getText());
        assertEquals(valuesToEnter.get("age"), outputAge.getText());
        assertEquals(valuesToEnter.get("gender").toLowerCase(), outputGender.getText());
    }

    @Then("^I (?:am on|open) check feedback page$")
    public void iAmNavigatedOnCheckFeedbackPage() {
        String expectedUrlPattern = "^https://kristinek.github.io/site/tasks/check_feedback\\.html\\?.*";
        wait.until(ExpectedConditions.urlMatches(expectedUrlPattern));
    }

    @When("I fill in the form with values:")
    public void iEnterValues(Map<String, String> valuesToEnter) throws Throwable {
        for (Map.Entry<String, String> element : valuesToEnter.entrySet()) {
            WebElement temp = driver.findElement(By.name(element.getKey()));
            waitUntilElementIsVisible(temp);
            switch (element.getKey()) {
                case "name":
                case "age":
                case "comment":
                    temp.clear();
                    temp.sendKeys(element.getValue());
                    break;
                case "gender":
                    List<WebElement> genderOptions = driver.findElements(By.name("gender"));
                    for (WebElement option : genderOptions) {
                        if (option.getAttribute("value").equalsIgnoreCase(element.getValue())) {
                            wait.until(ExpectedConditions.elementToBeClickable(option));
                            option.click();
                            break;
                        }
                    }
                    break;
                case "option":
                    Select dropdownHowDoYouLikeUs = new Select(temp);
                    wait.until(ExpectedConditions.elementToBeClickable(temp));
                    dropdownHowDoYouLikeUs.selectByVisibleText(element.getValue());
                    break;
                case "language":
                    List<String> languagesList = Arrays.asList(element.getValue().split(","));
                    List<WebElement> checkboxes = driver.findElements(By.className("w3-check"));
                    for (WebElement checkbox : checkboxes) {
                        wait.until(ExpectedConditions.visibilityOf(checkbox));
                        String checkboxValue = checkbox.getAttribute("value");
                        if (languagesList.contains(checkboxValue)) {
                            wait.until(ExpectedConditions.elementToBeClickable(checkbox));
                            if (!checkbox.isSelected()) {
                                checkbox.click();
                            }
                        }
                    }
                    break;

                default:
                    System.out.println("Unknown key: " + element.getKey());
            }
            System.out.println("key: " + element.getKey());
            System.out.println("value: " + element.getValue());
        }
    }


    @And("I should see name: {string}")
    public void iShouldSeeName(String name) {
        WebElement nameField = driver.findElement(By.id("name"));
        waitUntilElementIsVisible(nameField);
        assertEquals(name, nameField.getText());
    }

    @And("I should see age: (\\d+)$")
    public void iShouldSeeAge(int age) {
        WebElement ageField = driver.findElement(By.id("age"));
        waitUntilElementIsVisible(ageField);
        String actualAgeText = ageField.getText();
        int actualAge = Integer.parseInt(actualAgeText);
        assertEquals(age, actualAge);
    }

    @And("I should see gender: \"([^\"]*)\"$")
    public void iShouldSeeGender(String gender) {
        WebElement genderField = driver.findElement(By.id("gender"));
        waitUntilElementIsVisible(genderField);
        assertEquals(gender, genderField.getText());
    }

    @And("I should see languages: \"([^\"]*)\"$")
    public void iShouldSeeLanguagesLanguages(String languages) {
        WebElement languagesField = driver.findElement(By.id("language"));
        waitUntilElementIsVisible(languagesField);
        assertEquals(languages, languagesField.getText());
    }

    @And("I should see option: \"([^\"]*)\"$")
    public void iShouldSeeOptionOption(String option) {
        WebElement optionField = driver.findElement(By.id("option"));
        waitUntilElementIsVisible(optionField);
        assertEquals(option, optionField.getText());
    }

    @And("I should see comment: \"([^\"]*)\"$")
    public void iShouldSeeCommentComment(String comment) {
        WebElement commentField = driver.findElement(By.id("comment"));
        waitUntilElementIsVisible(commentField);
        assertEquals(comment, commentField.getText());
    }
    private void waitUntilElementIsVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}

