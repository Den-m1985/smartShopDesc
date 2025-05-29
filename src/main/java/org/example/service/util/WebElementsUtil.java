package org.example.service.util;

import org.example.service.browser.chrome.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WebElementsUtil {
    private final BrowserManager browserManager;

    public WebElementsUtil(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    public void clickElement(By by) {
        if (isButtonAvailable(by)) {
            WebElement webElement = browserManager.getWait().until(ExpectedConditions.elementToBeClickable(by));
            webElement.click();
        }
    }

    public boolean isButtonAvailable(By by) {
        try {
            WebElement webElement = browserManager.getWait().until(
                    ExpectedConditions.visibilityOfElementLocated(by)
            );
            return webElement.isDisplayed() && webElement.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
}
