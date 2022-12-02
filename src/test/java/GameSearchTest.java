import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pages.HomePage;
import pages.SearchPage;

import java.time.Duration;
import java.util.List;

@Test
public class GameSearchTest {

    public void gameSearchTest() {
        // initialisation and Icognito Mode

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("Chrome");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

        WebDriver browser = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofMillis(1000));

        browser.manage().window().maximize();
        String url = "http://store.steampowered.com/";
        String language = "Español - España (Spanish - Spain)";
        String keyword = "Dota 2";


        //Working on Home page
        HomePage homePage = new HomePage(browser);
        // Go to the main page
        homePage.browseMainPage(url);

        homePage.setSearchKeyWord(keyword);

        homePage.clickOnSearchButton();

        SearchPage searchPage = new SearchPage(browser);
        String result = searchPage.getSearchResultKeyword();
        Assert.assertEquals(result, keyword, "The text on the search bar is not the same with the given keyword");

        String title = searchPage.getName();
        System.out.println(title);

        String[] platforms = searchPage.getPlatforms().split(" ");

        for(String platform : platforms) {
            System.out.println(platform);
        }

        String releaseDate = searchPage.getReleaseDate();
        System.out.println(releaseDate);

        String review = searchPage.getSummaryResult();
        System.out.println(review);

        String price = searchPage.getPrice();
        //System.out.println(price);
    }
}
