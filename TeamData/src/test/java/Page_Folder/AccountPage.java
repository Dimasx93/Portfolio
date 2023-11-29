package Page_Folder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static stepDefinitions.Hooks.driver;


public class AccountPage {
    public AccountPage(){
        // default constructor
    }
    String registerUrl = "https://www.demoshop24.com/index.php?route=account/account";

    @FindBy(how = How.XPATH, using = "//input[@name='email']")
    private WebElement emailField;

    @FindBy(how = How.XPATH, using = "//input[@name='password']")
    private WebElement passwordField;

    @FindBy(how = How.NAME, using = "firstname")
    private WebElement nameField;
    @FindBy(how = How.NAME, using = "lastname")
    private WebElement lastNameField;

    @FindBy(how = How.NAME, using = "address_1")
    private WebElement addressField;

    @FindBy(how = How.NAME, using = "city")
    private WebElement cityField;

    @FindBy(how = How.XPATH, using = "//input[@type='submit']")
    private WebElement buttonField;

    @FindBy(how = How.XPATH, using = "//select[@name='country_id']")
    private WebElement countryField;

    @FindBy(how = How.XPATH, using = "//select[@name='zone_id']")
    private WebElement regionField;

    @FindBy(how = How.XPATH, using = "//td[@class='text-left']")
    private WebElement checkAddressField;

    public void subListField() {
        assertTrue(checkAddressField.isDisplayed());
        String expectedName = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[1]/table/tbody/tr/td[1]")).getText();
        expectedName = expectedName.replaceAll("\\n", " ").trim();
        assertEquals("Stefano Di Mauro bamboozled Licuddia Buenos Aires Argentina", expectedName);
    }
    public void setRegionField(){
        regionField.click();
    }
    public void setCountryField(){
        countryField.click();
    }
    public void fillEmailField(String email){
        emailField.clear();
        emailField.sendKeys("hh@hh.hh");
    }

    public void fillPasswordField(String password){
        passwordField.clear();
        passwordField.sendKeys("0000");
    }
    public void clickButtonField(){
        buttonField.click();
    }
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

    public void fillAddressField(String address){
        addressField.clear();
        addressField.sendKeys(address);
        String getAddress = addressField.getAttribute("value");
        assertEquals(address,getAddress);
    }

    public void fillCityField(String city){
        cityField.clear();
        cityField.sendKeys(city);
        String getCity = cityField.getAttribute("value");
        assertEquals(city,getCity);
    }



}


