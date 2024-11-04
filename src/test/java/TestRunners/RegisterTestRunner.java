package TestRunners;

import Config.Setup;
import Config.UserModel;
import Pages.RegisterPage;
import Util.EmailConfirmation;
import Util.Utils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.Duration;

public class RegisterTestRunner extends Setup {

    RegisterPage registerPage;

    @Test(priority = 1)
    public void doRegister() throws IOException, ParseException, InterruptedException, MessagingException {

        registerPage = new RegisterPage(webDriver);
        registerPage.redirectToRegistrationPage(webDriver);

        //register
        JSONObject userObj = Utils.createFakeUser();
        registerPage.doRegister(
                userObj.get("first-name").toString(),
                userObj.get("last-name").toString(),
                userObj.get("email").toString(),
                userObj.get("password").toString(),
                userObj.get("phone-number").toString(),
                userObj.get("address").toString(),
                userObj.get("gender-index").toString()
        );

        //save user data
        UserModel userModel = new UserModel();
        userModel.setFirstName(userObj.get("first-name").toString());
        userModel.setLastName(userObj.get("last-name").toString());
        userModel.setEmail(userObj.get("email").toString());
        userModel.setPassword(userObj.get("password").toString());
        userModel.setPhoneNumber(userObj.get("phone-number").toString());
        userModel.setAddress(userObj.get("address").toString());
        if (userObj.get("gender-index").toString().equals("0"))
            userModel.setGender("male");
        else
            userModel.setGender("female");

        Utils.saveUser(userModel);


        //Assertion
        Thread.sleep(2000); //explicit waiter isn't working
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(70));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast")));

        String successMsgActual = webDriver.findElement(By.className("Toastify__toast")).getText();
        System.out.println(successMsgActual);
        String successMsgExpected = "successfully";
        Assert.assertTrue(successMsgActual.contains(successMsgExpected));
    }
}
