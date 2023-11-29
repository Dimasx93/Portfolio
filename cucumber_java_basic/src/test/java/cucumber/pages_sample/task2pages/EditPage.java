package cucumber.pages_sample.task2pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class EditPage {

    @FindBy(xpath = "//*[@id=\"modal_button\"]")
    private WebElement editButton;
    @FindBy(id = "status")
    private WebElement statusSelect;
    @FindBy(id = "job")
    private WebElement jobInputField;

    public void selectStatus(String status) {
        Select select = new Select(statusSelect);
        select.selectByVisibleText(status);
    }

    public void clickEditButton() {
        editButton.click();
    }

    public void editPerson(String job, String status) {
        enterJob(job);
        selectStatus(status);
    }

    public void enterJob(String job) {
        jobInputField.clear();
        jobInputField.sendKeys(job);
    }

}
