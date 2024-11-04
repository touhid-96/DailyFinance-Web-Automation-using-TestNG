package TestRunners;

import Config.Setup;
import Config.UserModel;
import Pages.DashboardPage;
import Pages.LoginPage;
import Util.RandomNumberGenerator;
import Util.Utils;
import com.github.javafaker.Faker;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Util.Utils.getUser;

public class DashboardTestRunner extends Setup {

    DashboardPage dashboardPage;

    @BeforeTest
    public void login() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.doLogin(
                Utils.getUserInfo().get("email").toString(),
                Utils.getUserInfo().get("password").toString()
        );
    }

    @Test (priority = 1)
    public void addItem() throws InterruptedException {
        Faker faker = new Faker();
        dashboardPage = new DashboardPage(webDriver);
        List<String> itemNamesExpected = new ArrayList<>();
        itemNamesExpected.add(faker.commerce().productName());
        itemNamesExpected.add(faker.commerce().productName());

        for(int i=0; i<2; i++) {
            dashboardPage.addItem(
                    itemNamesExpected.get(i),
                    RandomNumberGenerator.generateNumber(3),
                    faker.lorem().characters(50)
            );
            Thread.sleep(1000);
            webDriver.switchTo().alert().accept();
        }

        List<String> itemNamesActual = new ArrayList<>();

        for(int i=0; i<2; i++) {
            dashboardPage.searchItem(itemNamesExpected.get(i));
            WebElement table = webDriver.findElement(By.tagName("tbody"));
            List<WebElement> allRows = table.findElements(By.tagName("tr"));
            itemNamesActual.add(allRows.get(0).findElements(By.tagName("td")).get(0).getText());
            Assert.assertEquals(itemNamesActual.get(i), itemNamesExpected.get(i));
        }
    }

    @Test (priority = 2)
    public void updateEmail() throws IOException, ParseException, InterruptedException {
        dashboardPage = new DashboardPage(webDriver);

        //making new email
        String originalMail = getUser().get("email").toString();
        originalMail = originalMail.replace("@gmail.com", "");
        String newEmail = originalMail + "+" + RandomNumberGenerator.generateNumber(8) + "@gmail.com";

        dashboardPage.updateUserEmail(newEmail);
        Thread.sleep(1000);
        webDriver.switchTo().alert().accept();

        //update user email data
        UserModel userModel = new UserModel();
        userModel.setEmail(newEmail);

        JSONObject userObj = Utils.getUserInfo();
        userModel.setFirstName(userObj.get("first-name").toString());
        userModel.setLastName(userObj.get("last-name").toString());
        userModel.setEmail(newEmail);
        userModel.setPassword(userObj.get("password").toString());
        userModel.setPhoneNumber(userObj.get("phone-number").toString());
        userModel.setAddress(userObj.get("address").toString());
        userModel.setAddress(userObj.get("gender").toString());

        Utils.saveUser(userModel);
    }

    @Test (priority = 3)
    public void logout() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.doLogOut();
    }
}
