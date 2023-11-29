package cucumber.pages_sample.task2pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonPage {

    @FindBy(xpath = "//div[@class='w3-xlarge ']")
    private List<WebElement> displayedPersonList;
    @FindBy(xpath = "//*[@id=\"person1\"]/span[2]/i")
    private WebElement editButton2;
    @FindBy(xpath = "(//button[@id='addPersonBtn'])[2]")
    private WebElement resetButton;
    @FindBy(xpath = "//*[@id=\"person0\"]/span[1]")
    private WebElement removeButton;
    @FindBy(xpath = "(//button[@id='addPersonBtn'])[1]")
    private WebElement addButton;
    @FindBy(xpath = "//*[@id=\"person0\"]/span[2]/i")
    private WebElement editButton;
    @FindBy(xpath = "//*[@id=\"person1\"]/span[1]")
    private WebElement removeButton2;

    public String getUrl() {
        return "https://kristinek.github.io/site/tasks/list_of_people.html";
    }

    public void edit(String name, String surname) {
        editButton.click();
    }

    public void resetAll() {
        resetButton.click();
    }

    public void removing(String name, String surname) {
        removeButton.click();
        removeButton2.click();
    }

    public void clickAddButton() {
        addButton.click();
    }

    public Map<String, String> generateInitialPersonListState() {
        Map<String, String> personListState = new HashMap<>();
        personListState.put("person0", "Employee");
        personListState.put("person1", "Intern");
        personListState.put("person2", "Contractor");
        personListState.put("Mike", "UX specialist");
        personListState.put("Jill", "Software Engineer");
        personListState.remove("Mike Kid");
        personListState.remove("Jill Watson");
        return personListState;
    }

    public List<WebElement> getDisplayedPersonList() {
        return displayedPersonList;
    }

}
