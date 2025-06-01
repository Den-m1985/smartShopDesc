package org.example.service.product.bolshe_podarkov.search_and_add.search;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchGoods extends BasicLanguageManager {


    public SearchGoods(BrowserManager browserManager, String goodsSize) {
        String LinksSearch = languageManager.get("bolshe_pod", "field.search");
        WebElement search = browserManager.getDriver().findElement(By.className(LinksSearch));
        search.click();
        search.sendKeys(goodsSize);

        String linkButtonSearch = languageManager.get("bolshe_pod", "button.search");
        WebElement buttonSearch = browserManager.getDriver().findElement(By.className(linkButtonSearch));
        buttonSearch.click();
    }

}
