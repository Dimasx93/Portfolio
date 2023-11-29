package cucumber.pages_sample.task2pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.*;

public class AddPage {
    @FindBy(id = "name")
    private WebElement nameInput;
    @FindBy(id = "surname")
    private WebElement surnameInput;
    @FindBy(id = "job")
    private WebElement jobInput;
    @FindBy(id = "dob")
    private WebElement dateOfBirthInput;
    @FindBy(id = "status")
    private WebElement statusSelect;
    @FindBy(id = "modal_button")
    private WebElement anotherAddButton;
    @FindBy(name = "gender")
    private WebElement radioButtonFemale;
    @FindBy(id = "addPersonBtn")
    private WebElement clearAllButton;
    @FindBy(id = "english")
    private WebElement languageEnglish;

    public void enterName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void enterJob(String job) {
        jobInput.clear();
        jobInput.sendKeys(job);
    }

    public void enterSurname(String surname) {
        surnameInput.clear();
        surnameInput.sendKeys(surname);
    }

    public void enterDoB(String birth) {
        dateOfBirthInput.clear();
        dateOfBirthInput.sendKeys(birth);
    }

    public void selectStatus(String status) {
        Select select = new Select(statusSelect);
        select.selectByVisibleText(status);
    }

    public void addPerson(String name, String surname, String status) {
        enterName(name);
        enterSurname(surname);
        selectStatus(status);
    }

    public void selectGender() {
        radioButtonFemale.isSelected();
    }

    public void clickAddButton() {
        anotherAddButton.click();
    }

    public void clearAllFields() {
        clearAllButton.click();
    }

    public void fillTheFields(String name, String surname, String job, String birth) {
        enterName(name);
        enterSurname(surname);
        enterJob(job);
        enterDoB(birth);
    }

    public void allClear() {
        assertEquals("", nameInput.getText());
        assertEquals("", surnameInput.getText());
        assertEquals("", jobInput.getText());
        assertEquals("", dateOfBirthInput.getText());
        assertFalse(radioButtonFemale.isSelected());
    }

    public void defaultFieldsCheck() {
        Select select = new Select(statusSelect);
        select.selectByIndex(0);
        String selectedOption = select.getFirstSelectedOption().getText();
        assertEquals("Employee", selectedOption);
        assertTrue(languageEnglish.isSelected());
    }

}
