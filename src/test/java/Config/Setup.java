package Config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class Setup {
    public WebDriver webDriver;

    @BeforeTest
    public void setup() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        webDriver.get("https://dailyfinance.roadtocareer.net/");
    }

    @AfterTest
    public void closeBrowser() {
        webDriver.quit();
    }
}