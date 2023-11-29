package cucumber.stepDefinitions.task2steps;

import cucumber.pages_sample.task2pages.AddPage;
import cucumber.pages_sample.task2pages.EditPage;
import cucumber.pages_sample.task2pages.PersonPage;
import cucumber.stepDefinitions.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Task2Steps {
    private WebDriver driver;
    static PersonPage personPage;
    static AddPage addPage;
    static EditPage editPage;

    private Map<String, String> initialListState;

    public Task2Steps() {
        this.driver = Hooks.driver;
        personPage = PageFactory.initElements(Hooks.driver, PersonPage.class);
        addPage = PageFactory.initElements(Hooks.driver, AddPage.class);
        editPage = PageFactory.initElements(Hooks.driver, EditPage.class);
        initialListState = new HashMap<>();
    }

    @Given("^I (?:am on|open) List of people page$")
    public void iAmOnListOfPeoplePage() {
        driver.get(personPage.getUrl());
        initialListState = personPage.generateInitialPersonListState();
    }

    @When("^I click Add button$")
    public void iClickAddButton() {
        personPage.clickAddButton();
    }

    @Then("^I click another add button$")
    public void iClickAnotherAddButton() {
        addPage.clickAddButton();
    }

    @Then("^I enter values for: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void iEnterDifferentValues(String name, String surname, String status) {
        addPage.addPerson(name, surname, status);
    }

    @When("^I click Remove Button for \"([^\"]*)\", \"([^\"]*)\"$")
    public void iClickRemoveButtonFor(String name, String surname) {
        personPage.removing(name, surname);
    }

    @Then("^I click Clear all fields button$")
    public void iClickClearAllFieldsButton() {
        addPage.clearAllFields();
    }

    @When("^I fill all fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void iFillAllFields(String name, String surname, String job, String dateOfBirth) {
        addPage.fillTheFields(name, surname, job, dateOfBirth);
    }

    @Then("^I click Edit button$")
    public void iClickEditButton() {
        editPage.clickEditButton();
    }

    @Then("^I editing \"([^\"]*)\", \"([^\"]*)\"$")
    public void iEditing(String job, String status) {
        editPage.editPerson(job, status);
    }

    @When("^I click Edit button for \"([^\"]*)\", \"([^\"]*)\"$")
    public void iClickEditButtonFor(String name, String surname) {
        personPage.edit(name, surname);
    }

    @Then("^I verify that all the fields are cleared$")
    public void iVerifyThatAllTheFieldsAreCleared() {
        addPage.allClear();
    }

    @Then("^I select gender$")
    public void iSelectGender() {
        addPage.selectGender();
    }

    @Then("^User list should be reset$")
    public void userListShouldBeReset() {
        Map<String, String> currentListState = personPage.generateInitialPersonListState();
        assertEquals(initialListState, currentListState);
    }

    @And("^I verify that status and language are default$")
    public void iVerifyThatStatusAndLanguageAreDefault() {
        addPage.defaultFieldsCheck();
    }

    @Then("^Removed persons should not be displayed for \"([^\"]*)\", \"([^\"]*)\"$")
    public void removedPersonsShouldNotBeDisplayed(String name, String surname) {
        String personIdentifier = name + " " + surname;
        List<WebElement> allPersons = personPage.getDisplayedPersonList();
        assertFalse("Removed persons should not be displayed: " + personIdentifier,
                allPersons.stream()
                        .anyMatch(person -> person.getText().contains(personIdentifier)));
    }

    @When("^I click Reset list button$")
    public void iClickResetListButton() {
        personPage.resetAll();
    }

}
