package org.example.service.alfa812.searchAndAdd;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.XPathWait;
import org.openqa.selenium.WebElement;

public class AddGoods extends BasicLanguageManager {

    public AddGoods(int item) throws InterruptedException {
        XPathWait pathWait = new XPathWait();

        WebElement search;

        String linkAddItem = languageManager.get("alfa812", "item.add");
        search = pathWait.xPathVisibility(linkAddItem);
        search.click();

        new CloudWindow();

        search.clear();
        String strItem = String.valueOf(item);
        search.sendKeys(strItem);

        new CloudWindow();

        String linkClickBay = languageManager.get("alfa812", "clickby");
        WebElement buttonSearch = pathWait.xPath(linkClickBay);
        buttonSearch.click();

        new CloudWindow();

        String linkCloseWindow = languageManager.get("alfa812", "close.window");
        WebElement buttonClose = pathWait.xPath(linkCloseWindow);
        buttonClose.click();

        new CloudWindow();
    }

}
