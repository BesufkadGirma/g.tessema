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

    public String getSearchResultKeyword() {
         WebElement searchResult = browser.findElement(By.name("displayterm"));
         wait.until(ExpectedConditions.visibilityOf(searchResult));
         return searchResult.getAttribute("value");
    }

    public List<WebElement> getSearchItems(){
        List<WebElement> searchItem = browser.findElements(By.id("search_resultsRows"));
        return  searchItem;
    }
    public String getName() {
       List<WebElement> searchItem = getSearchItems();
       String title = searchItem.get(0).findElement(By.xpath("//span[@class = 'title']")).getText();
       return title;

    }

    public String getPlatforms() {
        List<WebElement> searchItem = getSearchItems();
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

    public String getReleaseDate() {
        List<WebElement> searchItem = getSearchItems();
        WebElement releaseDate = searchItem.get(0).findElement(By.xpath("//div[contains(@class, 'search_released') and contains(@class, 'responsive_secondrow')]"));
        String date = releaseDate.getText();
        return date;

    }

    public String getPrice() {
        List<WebElement> searchItem = getSearchItems();
        WebElement price = searchItem.get(0).findElement(By.xpath("//div[contains(@class, 'search_release')]"));
        String gamePrice = price.getText();
        return gamePrice;
    }

    public String getSummaryResult() {
        List<WebElement> searchItem = getSearchItems();
        String review = "";

        if(isPositiveReview(searchItem)){
            review = "Positive";
        }
        if(isNegativeReview(searchItem)){
            review = "Negative";
        }
        return review;
    }
    public boolean isPositiveReview(List<WebElement> searchItem){
        List<WebElement> review = searchItem.get(0).findElements(By.xpath("//span[contains(@class, 'search_review_summary') and contains(@class, 'positive')]"));
        boolean isPositive = false;
        if (!review.isEmpty()) {
            isPositive = true;
        }
        return isPositive;

    }
    public boolean isNegativeReview(List<WebElement> searchItem){
        List<WebElement> review = searchItem.get(0).findElements(By.xpath("//span[contains(@class, 'search_review_summary') and contains(@class, 'negative')]"));
        boolean isNegative = false;
        if (!review.isEmpty()) {
            isNegative = true;
        }
        return isNegative;

    }
}
