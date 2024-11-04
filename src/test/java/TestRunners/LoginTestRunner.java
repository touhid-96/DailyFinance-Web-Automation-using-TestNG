package TestRunners;

import Config.Setup;
import Pages.LoginPage;
import Util.Utils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestRunner extends Setup {

    LoginPage loginPage;

    @Test
    public void loginWith_NewCredentials() throws IOException, ParseException {
        loginPage = new LoginPage(webDriver);

        loginPage.doLogin(
                Utils.getUser().get("email").toString(),
                Utils.getUser().get("password").toString()
        );

        //assertion-1
        String txtExpected = "Dashboard";
        String txtActual = webDriver.findElement(By.className("MuiTypography-h6")).getText();
        Assert.assertEquals(txtActual, txtExpected);
    }

    @Test
    public void logout() {
        loginPage.doLogOut();
    }
}
