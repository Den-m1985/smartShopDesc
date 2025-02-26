package org.example.service.alfa812.searchAndAdd;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.BrowserManager;
import org.openqa.selenium.WebElement;

public class SearchGoods extends BasicLanguageManager {

    public SearchGoods(BrowserManager browserManager, String article) throws InterruptedException {

        String linksSearch = languageManager.get("alfa812", "field.search");
        WebElement search = browserManager.getXPathWait().xPath(linksSearch);

        new CloudWindow(browserManager);

        search.click();

        new CloudWindow(browserManager);

        search.sendKeys(article);

        new CloudWindow(browserManager);

        String linkButtonSearch = languageManager.get("alfa812", "button.search");
        WebElement buttonSearch = browserManager.getXPathWait().xPath(linkButtonSearch);

        new CloudWindow(browserManager);

        buttonSearch.click();

        new CloudWindow(browserManager);
    }

}
