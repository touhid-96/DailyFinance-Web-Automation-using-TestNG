package Pages;

import Util.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class RegisterPage {

    @FindBy(id = "firstName")
    WebElement txtFirstName;

    @FindBy(id = "lastName")
    WebElement txtLastName;

    @FindBy(id = "email")
    WebElement txtEmail;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(id = "phoneNumber")
    WebElement txtPhoneNumber;

    @FindBy(id = "address")
    WebElement txtAddress;

    @FindBy(css = "[type=\"radio\"]")
    List<WebElement> radioGender;

    @FindBy(css = "[type=\"checkbox\"]")
    WebElement chkTermsAndCondition;

    @FindBy(css = "[type=\"submit\"]")
    WebElement btnRegister;

    @FindBy(xpath = "//a[contains(text(), \"Register\")]")
    WebElement linkRegisterPage;

    @FindBy(xpath = "//h1[text()=\"Register\"]")
    WebElement registerHeader;

    public RegisterPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void doRegister(
            String firstName,
            String lastName,
            String email,
            String password,
            String phoneNumber,
            String address,
            String genderIndex
    ) {
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        txtPhoneNumber.sendKeys(phoneNumber);
        txtAddress.sendKeys(address);

        radioGender.get(Integer.parseInt(genderIndex)).click();
        chkTermsAndCondition.click();
        btnRegister.click();
    }

    public void redirectToRegistrationPage(WebDriver webDriver) {
        linkRegisterPage.click();

        //wait for registration page
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOf(registerHeader));
    }
}