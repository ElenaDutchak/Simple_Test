import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

public class Simple_Test {

    private WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        String s = System.setProperty("webdriver.chrome.driver", "c:/chromedriver.exe");
        driver = new ChromeDriver(DesiredCapabilities.chrome());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void checkYoutubeVideos() {
        driver.get("https://www.youtube.com");
        WebElement searchStringElement = driver.findElement(By.id("search"));
        searchStringElement.sendKeys("selenium");
        WebElement searchButton = driver.findElement(By.cssSelector("#search-icon-legacy"));
        searchButton.click();
        List<WebElement> videoElements = driver.findElements(By.cssSelector("div[class*='style-scope ytd-item-section-renderer']"));
        assertTrue("There are less then 2 videos on the page", videoElements.size() >= 2);
    }

    @AfterTest
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}