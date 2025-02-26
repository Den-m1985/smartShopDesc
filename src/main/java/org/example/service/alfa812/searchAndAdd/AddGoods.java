package org.example.service.alfa812.searchAndAdd;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.BrowserManager;
import org.openqa.selenium.WebElement;

public class AddGoods extends BasicLanguageManager {

    public AddGoods(BrowserManager browserManager, int item) throws InterruptedException {
        WebElement search;

        String linkAddItem = languageManager.get("alfa812", "item.add");
        search = browserManager.getXPathWait().xPathVisibility(linkAddItem);
        search.click();

        new CloudWindow(browserManager);

        search.clear();
        String strItem = String.valueOf(item);
        search.sendKeys(strItem);

        new CloudWindow(browserManager);

        String linkClickBay = languageManager.get("alfa812", "clickby");
        WebElement buttonSearch = browserManager.getXPathWait().xPath(linkClickBay);
        buttonSearch.click();

        new CloudWindow(browserManager);

        String linkCloseWindow = languageManager.get("alfa812", "close.window");
        WebElement buttonClose = browserManager.getXPathWait().xPath(linkCloseWindow);
        buttonClose.click();

        new CloudWindow(browserManager);
    }

}
