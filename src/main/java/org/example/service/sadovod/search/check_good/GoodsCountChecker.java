package org.example.service.sadovod.search.check_good;

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
        List<WebElement> allCards = browserManager.getDriver().findElements(By.className("ty-column4"));
        int count = 0;
        for (WebElement card : allCards) {
            if (!card.findElements(By.className("ut2-gl__item")).isEmpty()) {
                count++;
            }
        }
        return count;
    }

}
