package org.example.service.product.bolshe_podarkov.search_and_add.checkGood;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckAvailability extends BasicLanguageManager {
    private final BrowserManager browserManager;

    public CheckAvailability(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    public boolean isPresentButtonToCart() {
        String toBasketString = languageManager.get("bolshe_pod", "to.basket");
        try {
            WebElement button = browserManager.getDriver().findElement(By.className(toBasketString));
            String str = button.getText();
            String subscribe = languageManager.get("bolshe_pod", "subscribe");
            return !str.equals(subscribe);
        } catch (Exception e) {
            return false;
        }
    }

}
