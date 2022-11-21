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
    public void loginPageValidation() {
        WebDriver driver = new ChromeDriver();
       //We can simply get the title of the main page and make sure if it's the right one
        driver.get("https://store.steampowered.com");
        String title = driver.getTitle();
        assertEquals("Welcome to Steam", title);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        //Clicking the login button to open the login page
        WebElement loginButton = driver.findElement(By.className("global_action_link"));
        loginButton.click();
        //we can simply retieve the title of the login page to make sure if it's the right one
        String titleLoginPage = driver.getTitle();
        assertEquals("Sign In", titleLoginPage);

        WebElement textBox = driver.findElement(By.className("newlogindialog_TextInput_2eKVn"));
        WebElement submitButton = driver.findElement(By.className("newlogindialog_SubmitButton_2QgFE"));

        //here we gonna give a random string "Selinium" to the input field and submit the input
        textBox.sendKeys("Selenium");
        submitButton.click();

        //we can check the displayed message or the error message after validation of the form
        WebElement message = driver.findElement(By.className("newlogindialog_FormError_1Mcy9"));
        String value = message.getText();
        assertEquals(message.getText(), value);

        //Closing or stopping the driver that we've been using to drive the browser
        driver.quit();
    }
}
