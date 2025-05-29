package org.example.service.sadovod.search.check_good;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartButtonVerifier extends BasicLanguageManager {
    private final BrowserManager browserManager;

    public CartButtonVerifier(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    public boolean isPresentButtonToCart() {
        try {
            By by = By.cssSelector(".ty-btn__add-to-cart");
            WebElement button = browserManager.getWait().until(
                    ExpectedConditions.visibilityOfElementLocated(by)
            );
            return button.isDisplayed() && button.isEnabled();
        } catch (Exception e) {
            return false; // Кнопка не найдена или не видна
        }
    }

}
