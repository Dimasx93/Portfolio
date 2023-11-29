package Page_Folder;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterPage {
    String registerUrl = "https://www.demoshop24.com/index.php?route=account/register";

    private Faker faker = new Faker();
    @FindBy(how = How.NAME, using = "firstname")
    private WebElement nameField;
    @FindBy(how = How.NAME, using = "lastname")
    private WebElement lastNameField;
    @FindBy(how = How.NAME, using = "email")
    private WebElement emailField;
    @FindBy(how = How.NAME, using = "telephone")
    private WebElement phoneField;
    @FindBy(how = How.NAME, using = "password")
    private WebElement passField;
    @FindBy(how = How.NAME, using = "confirm")
    private WebElement confirmPassField;

    @FindBy(how = How.CLASS_NAME, using = "list-group")
    private WebElement subList;

    public void fillFirstNameField(String name){
        nameField.clear();
        nameField.sendKeys(name);
        String getName = nameField.getAttribute("value");
        assertEquals(name,getName);
    }

    public void fillLastNameField(String surname){
        lastNameField.clear();
        lastNameField.sendKeys(surname);
        String getSurname = lastNameField.getAttribute("value");
        assertEquals(surname,getSurname);
    }

    public void fillEmailField(){
        String randomEmail = faker.internet().emailAddress();
        emailField.clear();
        emailField.sendKeys(randomEmail);
        assertEquals(randomEmail, emailField.getAttribute("value"));
    }

    public void fillTelephoneField(String phonenumber){
        phoneField.clear();
        phoneField.sendKeys(phonenumber);
        String getPhone = phoneField.getAttribute("value");
        assertEquals(phonenumber,getPhone);
    }
    public void fillPasswordField(String password){
        passField.clear();
        passField.sendKeys(password);
        String getPassword = passField.getAttribute("value");
        assertEquals(password,getPassword);
    }
    public void fillConfirmPswrdField(String confirmpass){
        confirmPassField.clear();
        confirmPassField.sendKeys(confirmpass);
        String getConfirmPwrd = confirmPassField.getAttribute("value");
        assertEquals(confirmpass,getConfirmPwrd);
    }

    public void subListField(){
        assertTrue(subList.isDisplayed());
        List<WebElement> subItems = subList.findElements(By.xpath("//div[@class='list-group']//a[@class='list-group-item']"));
        int numberSubItems = subItems.size();
        List<String> expectedValues = Arrays.asList(
                "My account", "Edit account", "Password", "Wish List", "Order History",
                "Downloads", "Returns", "Logout", "Newsletter", "Reward Points",
                "Recurring payments", "Transactions"
        );
        assertEquals(numberSubItems, expectedValues.size());

    }


}

