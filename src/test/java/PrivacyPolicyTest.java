
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PrivacyPolicyPage;

import java.time.Duration;

public class PrivacyPolicyTest {

    @Test
    public void PrivacyPolicyTest() {

        // initialisation
        WebDriver browser = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofMillis(1000));
        browser.manage().window().maximize();
        String url = "http://store.steampowered.com/";
        String language = "Español - España (Spanish - Spain)";
        //Working on Home page
        HomePage homePage = new HomePage(browser);
        // Go to the main page
        homePage.browseMainPage(url);
        //Scrolling down in the page
        homePage.scrollDown();
        //Click on privacy link
        homePage.selectPrivacyPolicyLink();

        //Working on privacy policy page
        PrivacyPolicyPage privacyPolicyPage = new PrivacyPolicyPage(browser);
        //Click on language dropdown menu
        privacyPolicyPage.clickOnLanguageList();
        //Select the language option
        privacyPolicyPage.selectLanguage(language);

    }
    }

