package org.example.service.product.sadovod.search.check_good;

import org.example.enums.TextLinksSadovod;
import org.example.service.browser.chrome.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoodsCountChecker {
    private final BrowserManager browserManager;

    public GoodsCountChecker(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    public int getCountCarts() {
        By allCartsLocator = By.className(TextLinksSadovod.ALL_CARTS_LOCATOR.getString());
        List<WebElement> allCards = browserManager.getDriver().findElements(allCartsLocator);
        int count = 0;
        By countCartsLocator = By.className(TextLinksSadovod.COUNT_CARTS_LOCATOR.getString());
        for (WebElement card : allCards) {
            if (!card.findElements(countCartsLocator).isEmpty()) {
                count++;
            }
        }
        return count;
    }

}
