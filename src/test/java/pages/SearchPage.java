package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchPage extends BasePage{

    public SearchPage(WebDriver browser){
        super(browser);
    }

    public String getSearchResult() {
         WebElement searchResult = browser.findElement(By.name("displayterm"));
         wait.until(ExpectedConditions.visibilityOf(searchResult));
         return searchResult.getAttribute("value");
    }

    public String getName() {
       List<WebElement> searchItem = browser.findElements(By.id("search_resultsRows"));
       String title = searchItem.get(0).findElement(By.xpath("//span[@class = 'title']")).getText();
       return title;

    }

    public String getPlatforms() {
        List<WebElement> searchItem = browser.findElements(By.id("search_resultsRows"));
         String platforms = "";
        if(isWindowsSupported(searchItem)){
            platforms+="Windows ";
        }
        if(isLinuxSupported(searchItem)){
            platforms+= "Linux ";
        }
        if(isMacSupported(searchItem)){
            platforms+= "Mac";
        }

        return platforms;
    }
    public boolean isWindowsSupported(List<WebElement> searchItem) {
        boolean isSupported = false;
        List<WebElement> windows = searchItem.get(0).findElements(By.xpath("//span[contains(@class, 'platform_img') and contains(@class, 'win')]"));
        if(!windows.isEmpty()) {
            isSupported = true;
        }
        return isSupported;
    }
    public boolean isLinuxSupported(List<WebElement> searchItem) {
        boolean isSupported = false;
        List<WebElement> linux = searchItem.get(0).findElements(By.xpath("//span[contains(@class, 'platform_img') and contains(@class, 'mac')]"));
        if(!linux.isEmpty()) {
            isSupported = true;
        }
        return isSupported;

    }
    public boolean isMacSupported(List<WebElement> searchItem) {
        boolean isSupported = false;
        List<WebElement> mac = searchItem.get(0).findElements(By.xpath("//span[contains(@class, 'platform_img') and contains(@class, 'mac')]"));
        if(!mac.isEmpty()) {
            isSupported = true;
        }
        return isSupported;

    }
}
