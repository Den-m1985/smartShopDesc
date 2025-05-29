package org.example.service.sadovod.search.add_to_backet;

import org.example.service.browser.chrome.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartAdder {
    private final BrowserManager browserManager;
    private final By buttonLocator;

    public CartAdder(BrowserManager browserManager) {
        this.browserManager = browserManager;
        this.buttonLocator = By.cssSelector(".ty-btn__add-to-cart");
    }


    public void clickAddToCart() {
        if (isButtonActive()) {
            WebElement button = browserManager.getWait().until(
                    ExpectedConditions.elementToBeClickable(buttonLocator)
            );
            button.click();
        }
    }


    private boolean isButtonActive() {
        try {
            WebElement button = browserManager.getWait().until(
                    ExpectedConditions.visibilityOfElementLocated(buttonLocator)
            );
            return button.isDisplayed() && button.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
}
