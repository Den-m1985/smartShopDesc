package org.example.service.bolshe_podarkov.searchAndAdd.search;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.DriverChrome;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchGoods extends BasicLanguageManager {

    public SearchGoods(String goodsSize) {
        WebDriver driver = DriverChrome.getChromeDriver();

        String LinksSearch = languageManager.get("bolshe_pod", "field.search");
        WebElement search = driver.findElement(By.className(LinksSearch));
        search.click();
        search.sendKeys(goodsSize);

        String linkButtonSearch = languageManager.get("bolshe_pod", "button.search");
        WebElement buttonSearch = driver.findElement(By.className(linkButtonSearch));
        buttonSearch.click();
    }

}
