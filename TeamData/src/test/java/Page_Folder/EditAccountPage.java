package Page_Folder;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditAccountPage {
    String registerUrl= "https://www.demoshop24.com/index.php?route=account/edit";

    @FindBy(how = How.XPATH, using = "//input[@name='email']")
    private WebElement emailField;

    @FindBy(how = How.XPATH, using = "//input[@name='telephone']")
    private WebElement phoneField;
    @FindBy(how = How.XPATH, using = "//input[@name='firstname']")
    private WebElement nameField;
    @FindBy(how = How.XPATH, using = "//input[@name='lastname']")
    private WebElement lastNameField;


    public void fillFirstNameField(String name){
        nameField.clear();
        nameField.sendKeys(name);
        String getName = nameField.getAttribute("value");
        assertEquals(name, getName);
    }

    public void fillLastNameField(String surname){
        lastNameField.clear();
        lastNameField.sendKeys(surname);
        String getSurname = lastNameField.getAttribute("value");
        assertEquals(surname,getSurname);
    }

    public void fillEmailField(String email){
        emailField.clear();
        emailField.sendKeys(email);
        String getEmail = emailField.getAttribute("value");
        assertEquals(email, getEmail);
    }

    public void fillTelephoneField(String phonenumber){
        phoneField.clear();
        phoneField.sendKeys(phonenumber);
        String getPhone = phoneField.getAttribute("value");
        assertEquals(phonenumber, getPhone);
    }

}

