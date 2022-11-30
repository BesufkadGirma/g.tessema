package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class HomePage extends BasePage{
    public HomePage(WebDriver browser) {
      super(browser);
    }
    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }
    public void selectPrivacyPolicyLink() {
        List<WebElement> footerLinks = browser.findElements(By.id("footer_text"));
        wait.until(ExpectedConditions.elementToBeClickable(footerLinks.get(0)));
        WebElement privacyLink = footerLinks.get(0).findElement(By.linkText("Privacy Policy"));
        wait.until(ExpectedConditions.elementToBeClickable(privacyLink));
        privacyLink.click();
    }
    public void browseMainPage(String url) {
        browser.get(url);
    }

    public void setSearchKeyWord(String keyword) {

        WebElement searchBox = browser.findElement(By.id("store_nav_search_term"));
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.sendKeys(keyword);

    }

    public void clickOnSearchButton() {
        WebElement searchButton = browser.findElement(By.id("store_search_link"));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.submit();
    }
}
