package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
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

    @FindBy(xpath = "//input[@name='phoneNumber']")
    WebElement txtPhone;

    @FindBy(xpath = "//button[contains(text(),'Upload Image')]")
    WebElement btnUploadImage;

    @FindBy(xpath = "//button[text()='Update']")
    WebElement btnUpdate;

    @FindBy(className = "upload-input")
    WebElement fileImage;

    WebDriver driver;

    public DashboardPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.driver = webDriver;
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

    public void clearSearch() {
        searchItem.clear();
    }

    public void navigateToEditProfile() {
        profile.click();
        menuItem.get(0).click(); //click on profile
        btnAction.get(1).click(); //click on edit
    }

    public void updatePhoneNumber(String phoneNumber) {
        txtPhone.clear();
        txtPhone.sendKeys(phoneNumber);
    }

    public void updateImage() {
        String imageFileLocation = "./src/test/resources/image.jpg";
        String imageFileAbsoluteLocation = new File(imageFileLocation).getAbsolutePath();
        fileImage.clear();
        fileImage.sendKeys(imageFileAbsoluteLocation);
        btnUploadImage.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void submitUpdate() {
        btnUpdate.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}
