package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class PrivacyPolicyPage extends BasePage {
    public PrivacyPolicyPage(WebDriver browser) {
       super(browser);
    }
    public void clickOnLanguageList() {
        for(String winHandle : browser.getWindowHandles()){
            browser.switchTo().window(winHandle);
        }
        WebElement languageDropdown = browser.findElement(By.xpath("//span[text()='language']"));
        wait.until(ExpectedConditions.visibilityOf(languageDropdown));
        languageDropdown.click();
    }
    public void selectLanguage(String language) {
        List<WebElement> languageList = browser.findElements(By.xpath("//div[contains(@class, 'popup_body')]"));
        wait.until(ExpectedConditions.visibilityOf(languageList.get(0)));
        for( WebElement languageOption : languageList){
            languageOption.findElement(By.linkText(language)).click();
        }
    }

    }

