package org.example.service.alfa812.searchAndAdd;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.XPathWait;
import org.openqa.selenium.WebElement;

public class SearchGoods extends BasicLanguageManager {

    public SearchGoods(String article) throws InterruptedException {
        XPathWait pathWait = new XPathWait();

        String linksSearch = languageManager.get("alfa812", "field.search");
        WebElement search = pathWait.xPath(linksSearch);

        new CloudWindow();

        search.click();

        new CloudWindow();

        search.sendKeys(article);

        new CloudWindow();

        String linkButtonSearch = languageManager.get("alfa812", "button.search");
        WebElement buttonSearch = pathWait.xPath(linkButtonSearch);

        new CloudWindow();

        buttonSearch.click();

        new CloudWindow();
    }

}
