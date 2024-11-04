package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardPage {

    @FindBy(className = "add-cost-button")
    WebElement btnAddCost;

    @FindBy(id = "itemName")
    WebElement txtItemName;

    @FindBy(id = "amount")
    WebElement txtAmount;

    @FindBy(id = "remarks")
    WebElement txRemarks;

    @FindBy(className = "submit-button")
    WebElement btnSubmit;

    @FindBy(className = "search-input")
    WebElement searchItem;

    @FindBy(xpath = "//button[@aria-controls=\"menu-appbar\"]")
    WebElement profile;

    @FindBy(xpath = "//li[@role=\"menuitem\"]")
    List<WebElement> menuItem;

    @FindBy(xpath = "//button[@type=\"button\"]")
    List<WebElement> btnAction;

    @FindBy(css = "[type=\"email\"]")
    WebElement txtEmail;

    public DashboardPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void addItem(String itemName, String amount, String remarks) {
        btnAddCost.click();
        txtItemName.sendKeys(itemName);
        txtAmount.sendKeys(amount);
        txRemarks.sendKeys(remarks);

        btnSubmit.click();
    }

    public void searchItem(String itemName) {
        searchItem.clear();
        searchItem.sendKeys(itemName);
    }

    public void updateUserEmail(String email) {
        profile.click();
        menuItem.get(0).click(); //click on profile
        btnAction.get(1).click(); //click on edit
        txtEmail.clear();
        txtEmail.sendKeys(email);
        btnAction.get(2).click(); //click on update
    }
}
