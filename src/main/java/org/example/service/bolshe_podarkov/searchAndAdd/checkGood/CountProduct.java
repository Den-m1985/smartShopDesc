package org.example.service.bolshe_podarkov.searchAndAdd.checkGood;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CountProduct extends BasicLanguageManager {
    private final BrowserManager browserManager;

    public CountProduct(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    public int countProduct() {
        String cartField = languageManager.get("bolshe_pod", "field.cart");
        try {
            List<WebElement> goodsArray = browserManager.getDriver().findElements(By.className(cartField));
            return goodsArray.size();
        } catch (Exception ignored) {
        }
        return 0;
    }

}
