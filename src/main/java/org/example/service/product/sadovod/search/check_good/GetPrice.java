package org.example.service.product.sadovod.search.check_good;

import org.example.service.browser.chrome.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GetPrice {
    private final BrowserManager browserManager;

    public GetPrice(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    public String getPriceFromWeb() {
        By by = By.className("ty-price-num");
        WebElement price = browserManager.getWait().until(
                ExpectedConditions.visibilityOfElementLocated(by)
        );
        return price.getText();
    }

}
