package stepDefinitions;

import Page_Folder.AccountPage;
import Page_Folder.EditAccountPage;
import Page_Folder.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepTest {
    
    WebDriver driver;
    static RegisterPage registerPage;
    static AccountPage accountPage;
    static EditAccountPage editAccountPage;

    public StepTest(){
        this.driver = Hooks.driver;
        registerPage = PageFactory.initElements(this.driver, RegisterPage.class);
        accountPage = PageFactory.initElements(this.driver, AccountPage.class);
        editAccountPage = PageFactory.initElements(this.driver, EditAccountPage.class);
    }
    @Given("I am on Demo page")
    public void iAmOnDemoPage() {
        driver.get("https://demoshop24.com/");
        assertEquals("https://demoshop24.com/", driver.getCurrentUrl());
    }

    @When("I click on Register")
    public void iClickOnRegister() {
        driver.findElement(By.xpath("//a[@title='My Account']")).click();
        WebElement registerButton = driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a"));
        registerButton.click();
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://www.demoshop24.com/index.php?route=account/register";
        assertEquals(expectedURL, currentURL);

    }
    @And("I am on registration page")
    public void iAmOnRegistrationPage() {
        driver.get("https://www.demoshop24.com/index.php?route=account/register");
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://www.demoshop24.com/index.php?route=account/register";
        assertEquals(expectedURL, currentURL);
    }

    @And("I enter values:")
    public void iEnterValues(Map<String, String> send) {

        registerPage.fillFirstNameField(send.get("name"));
        registerPage.fillLastNameField(send.get("surname"));
        registerPage.fillEmailField();
        registerPage.fillTelephoneField(send.get("phonenumber"));
        registerPage.fillPasswordField(send.get("password"));
        registerPage.fillConfirmPswrdField(send.get("confirmpass"));
    }

    @And("Click on agree Policy")
    public void clickOnAgreePolicy() {
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
    }

    @And("Click on Continue button")
    public void clickOnContinueButton() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://www.demoshop24.com/index.php?route=account/success";
        assertEquals(expectedURL, currentURL);
    }

    @Then("Click on other continue button")
    public void clickOnOtherContinueButton() {
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/a")).click();
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://www.demoshop24.com/index.php?route=account/account";
        assertEquals(expectedURL, currentURL);
    }

    @And("Check all submenu items")
    public void checkAllSubmenuItems() {
        registerPage.subListField();
    }

    @Given("I am in My Account page")
    public void iAmInMyAccountPage() {
        driver.get("https://www.demoshop24.com/index.php?route=account/account");
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://www.demoshop24.com/index.php?route=account/login";
        assertEquals(expectedURL, currentURL);
    }

    @When("I click on Address Book")
    public void iClickOnAddressBook() {
        driver.findElement(By.xpath("//*[@id=\"column-right\"]/div/a[4]")).click();
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://www.demoshop24.com/index.php?route=account/address";
        assertEquals(expectedURL, currentURL);

    }

    @And("I click on New Address button")
    public void iClickOnNewAddressButton() {
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[2]/a")).click();
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://www.demoshop24.com/index.php?route=account/address/add";
        assertEquals(expectedURL, currentURL);
    }

    @When("I login")
    public void iLogin() {
        accountPage.fillEmailField("email");
        accountPage.fillPasswordField("password");
        accountPage.clickButtonField();
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://www.demoshop24.com/index.php?route=account/account";
        assertEquals(expectedURL, currentURL);
    }

    @And("Enter values:")
    public void enterValues(Map<String, String> send) {
        accountPage.fillFirstNameField(send.get("name"));
        accountPage.fillLastNameField(send.get("surname"));
        accountPage.fillAddressField(send.get("address"));
        accountPage.fillCityField(send.get("city"));
    }

    @And("Click on Country")
    public void clickOnCountry() {
        accountPage.setCountryField();
        WebElement countryDropdown = driver.findElement(By.xpath("//select[@name='country_id']"));
        Select select = new Select(countryDropdown);
        select.selectByValue("10");
        WebElement selectedOption = select.getFirstSelectedOption();
        String selectedText = selectedOption.getText();
        assertEquals("Argentina", selectedText);
    }

    @And("Choose Region State")
    public void chooseRegionState() {
        accountPage.setRegionField();
        WebElement regionDropdown = driver.findElement(By.xpath("//select[@name='zone_id']"));
        Select select = new Select(regionDropdown);
        select.selectByValue("156");
        WebElement selectedOption = select.getFirstSelectedOption();
        String selectedText = selectedOption.getText();
        assertEquals("Buenos Aires", selectedText);
    }

    @And("Check Default box")
    public void checkDefaultBox() {
        driver.findElement(By.xpath("//label[@class='radio-inline']//input[@value='1']")).click();
    }

    @Then("Check address")
    public void checkAddress() {
        accountPage.subListField();
    }

    @And("I click on Edit Account")
    public void iClickOnEditAccount() {
        driver.findElement(By.xpath("//*[@id=\"column-right\"]/div/a[2]")).click();
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://www.demoshop24.com/index.php?route=account/edit";
        assertEquals(expectedURL, currentURL);
    }

    @And("I change values:")
    public void iChangeValues(Map<String, String> send) {
        editAccountPage.fillFirstNameField(send.get("name"));
        editAccountPage.fillLastNameField(send.get("surname"));
        editAccountPage.fillEmailField(send.get("e-mail"));
        editAccountPage.fillTelephoneField(send.get("phonenumber"));
    }

    @Then("Click Continue button")
    public void clickContinueButton() {
        {
            driver.findElement(By.xpath("//input[@type='submit']")).click();
            String currentURL = driver.getCurrentUrl();
            String expectedURL = "https://www.demoshop24.com/index.php?route=account/account";
            assertEquals(expectedURL, currentURL);
        }
    }

    @And("Click the Continue button")
    public void clickTheContinueButton() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://www.demoshop24.com/index.php?route=account/address";
        assertEquals(expectedURL, currentURL);
    }
}
