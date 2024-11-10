package TestRunners;

import Config.Setup;
import Pages.AdminDashboardPage;
import Pages.LoginPage;
import Util.Utils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class AdminTestRunner extends Setup {
    AdminDashboardPage adminDashboardPage;

    @BeforeTest
    public void login() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.doLogin(
                Utils.getAdminInfo().get("username").toString(),
                Utils.getAdminInfo().get("password").toString()
        );
    }

    @Test (priority = 1, description = "Verify that the registered user is visible in dashboard")
    public void verifyUserExistence() throws IOException, ParseException {
        adminDashboardPage = new AdminDashboardPage(webDriver);
        adminDashboardPage.search(Utils.getUserInfo().get("email").toString());
        adminDashboardPage.navigateToUserDetailsPage();
    }

    @Test(priority = 2, description = "Verify that registered user profile image is displayed")
    public void verifyUserProfileImageExistence() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        WebElement profileImage = webDriver.findElement(By.className("profile-image"));
        wait.until(ExpectedConditions.visibilityOf(profileImage));
        Assert.assertTrue(profileImage.isDisplayed(), "Profile image is not displayed on the page.");
    }

    @Test(priority = 3, description = "Admin logout")
    public void logout() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.doLogOut();
    }
}
