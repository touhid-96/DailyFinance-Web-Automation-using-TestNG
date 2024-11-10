package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminDashboardPage {

    @FindBy(className = "search-box")
    WebElement txtSearch;
    @FindBy(xpath = "//button[contains(text(),'View')]")
    WebElement btnView;

    public AdminDashboardPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void search(String search) {
        txtSearch.clear();
        txtSearch.sendKeys(search);
    }
    public void navigateToUserDetailsPage() {
        btnView.click();
    }
}
