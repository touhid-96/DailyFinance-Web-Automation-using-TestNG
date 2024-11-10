package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {

    @FindBy(id = "email")
    WebElement txtEmail;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(css = "[type=\"submit\"]")
    WebElement btnLogin;

    @FindBy(xpath = "//button[@aria-controls=\"menu-appbar\"]")
    WebElement profile;

    @FindBy(xpath = "//li[@role=\"menuitem\"]")
    List<WebElement> menuItem;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void doLogin(String _username, String _password) {
        txtEmail.sendKeys(_username);
        txtPassword.sendKeys(_password);

        btnLogin.click();
    }

    public void doLogOut() {
        profile.click();
        menuItem.get(1).click(); //click logout
    }
}
