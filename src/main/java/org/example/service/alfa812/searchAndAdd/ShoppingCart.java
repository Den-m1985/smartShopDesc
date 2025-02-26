package org.example.service.alfa812.searchAndAdd;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.BrowserManager;
import org.openqa.selenium.WebElement;

public class ShoppingCart extends BasicLanguageManager {
    private final BrowserManager browserManager;

    public ShoppingCart(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    public void clickCart() {
        String linkCart = languageManager.get("alfa812", "field.cart");
        WebElement buttonSearch = browserManager.getXPathWait().xPath(linkCart);
        buttonSearch.click();
    }

}
