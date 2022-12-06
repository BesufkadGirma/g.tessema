import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.SearchPage;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

@Test
public class GameSearchTest {

    public void gameSearchTest() {
        // initialisation

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("Chrome");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

        WebDriver browser = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofMillis(1000));

        browser.manage().window().maximize();
        String url = "http://store.steampowered.com/";
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

        String title = searchPage.getName(0);

        String[] platforms = searchPage.getPlatforms().split(" ");

        String releaseDate = searchPage.getReleaseDate();

        String review = searchPage.getSummaryResult(0);

        String price = searchPage.getPrice();
        BasePage basePage;
        searchPage.getJsonOutput(title, platforms, releaseDate, review, price);

        //title = searchPage.getName(1);
        //System.out.println(title);

        //homePage.setSearchKeyWord(title);
       // homePage.clickOnSearchButton();
    }
}
