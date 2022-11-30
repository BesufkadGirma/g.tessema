package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends BasePage{

    public SearchPage(WebDriver browser){
        super(browser);
    }

    public String getSearchResult() {
         WebElement searchResult = browser.findElement(By.name("displayterm"));
         wait.until(ExpectedConditions.visibilityOf(searchResult));
         return searchResult.getAttribute("value");
    }
}
