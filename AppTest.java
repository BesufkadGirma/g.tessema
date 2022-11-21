import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;


public class AppTest {

    @Test
    public void eightComponents() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://store.steampowered.com");
        String title = driver.getTitle();
        assertEquals("Welcome to Steam", title);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500000));

        WebElement loginButton = driver.findElement(By.className("global_action_link"));

        loginButton.click();

        driver.quit();
    }
}
