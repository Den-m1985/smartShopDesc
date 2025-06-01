package org.example.service.product.sadovod.search;

import org.example.service.browser.chrome.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductCardOpener {
    private final BrowserManager browserManager;

    public ProductCardOpener(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    public void openProductDetails() {
        By productPreviewLocator = By.cssSelector(".ut2-gl__item");

        WebElement productCard = browserManager.getWait().until(
                ExpectedConditions.elementToBeClickable(productPreviewLocator)
        );
        productCard.click();

        browserManager.getWait().until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );
    }

}
