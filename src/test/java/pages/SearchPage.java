package pages;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.FileWriter;
import java.io.IOException;
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
        //WebElement searchItemContainer = browser.findElement(By.id("search_resultsRows"));
        List<WebElement> searchItem = browser.findElements(By.cssSelector(".search_result_row"));
        return  searchItem;
    }
    public String getName(int itemRow) {
       List<WebElement> searchItem = getSearchItems();
       String title = searchItem.get(itemRow).findElement(By.xpath("//span[@class = 'title']")).getText();
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
        WebElement price = searchItem.get(0).findElement(By.xpath("//div[contains(@class, 'search_price')]"));
        String gamePrice = price.getText();
        return gamePrice;
    }

    public String getSummaryResult(int itemRow) {
        List<WebElement> searchItem = getSearchItems();
        WebElement review = searchItem.get(itemRow).findElement(By.cssSelector(".search_review_summary"));
        String reviewText[] = review.getAttribute("data-tooltip-html").split("<br>");
        return reviewText[0];
    }
    public void getJsonOutput(String title, String platforms[], String releaseDate, String review, String price) {
        JSONObject gameDetails = new JSONObject();

        gameDetails.put("title", title);
        JSONArray arrayPlatforms = new JSONArray();
        for(int i = 0; i < platforms.length; i++) {
            arrayPlatforms.add(platforms[i]);
        }
        gameDetails.put("platforms", arrayPlatforms);
        gameDetails.put("release date", releaseDate);
        gameDetails.put("review", review);
        gameDetails.put("price", price);

        JSONObject gameObject = new JSONObject();
        gameObject.put("game", gameDetails);

        JSONArray gameList = new JSONArray();
        gameList.add(gameObject);

        //Write JSON file
        try (FileWriter file = new FileWriter("games.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(gameList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
