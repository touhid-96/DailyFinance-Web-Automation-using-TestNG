package TestRunners;

import Config.ProductModel;
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
import org.testng.annotations.AfterTest;
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

    @Test(priority = 1, description = "Add items from CSV and validate total cost")
    public void addItem() throws InterruptedException, IOException {
        List<ProductModel> products = Utils.loadProducts();
        int itemCount = products.size();

        if (itemCount == 0) {
            Assert.fail("Product list is empty");
        }

        dashboardPage = new DashboardPage(webDriver);
        for (ProductModel product : products) {
            dashboardPage.addItem(
                    product.getName(),
                    product.getAmount(),
                    product.getRemarks()
            );

            Thread.sleep(1000);
            webDriver.switchTo().alert().accept();

            Thread.sleep(1000);
            dashboardPage.searchItem(product.getName());
            WebElement table = webDriver.findElement(By.tagName("tbody"));
            List<WebElement> allRows = table.findElements(By.tagName("tr"));
            String itemNamesActual = allRows.get(0).findElements(By.tagName("td")).get(0).getText();
            Assert.assertEquals(itemNamesActual, product.getName());
        }

        dashboardPage.clearSearch();
        webDriver.get("https://dailyfinance.roadtocareer.net/user");  //reloading the page to get total amount

        int totalCostExpected = products.stream().mapToInt(
                product -> Integer.parseInt(product.getAmount())
        ).sum();

        WebElement totalCostElement = webDriver.findElement(By.xpath("//span[contains(text(),'Total Cost')]"));
        String totalCostActual = totalCostElement.getText();

        Assert.assertTrue(
                totalCostActual.toLowerCase().endsWith(totalCostExpected + " TK".toLowerCase())
        );
    }

    @Test(priority = 2, description = "Updates phone and image")
    public void updateDetails() {
        dashboardPage = new DashboardPage(webDriver);
        dashboardPage.navigateToEditProfile();
        dashboardPage.updatePhoneNumber("017" + RandomNumberGenerator.generateNumber(8));
        dashboardPage.updateImage();
        dashboardPage.submitUpdate();
    }

    @Test(priority = 3, description = "User logout")
    public void logout() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.doLogOut();
    }
}
