package org.example.service.product.alfa812.search_and_add;

import org.example.service.BasicLanguageManager;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class CloudWindow extends BasicLanguageManager {
    private final WebElementsUtil webElementsUtil;

    public CloudWindow(WebElementsUtil webElementsUtil) {
        this.webElementsUtil = webElementsUtil;
    }

    public void closeModalWindow() {
        try {
            tryClosePopup();
        } catch (StaleElementReferenceException e) {
            tryClosePopup();
        } catch (Exception e) {
            throw new RuntimeException("closeModalWindow \n" + e.getMessage());
        }
    }

    private void tryClosePopup() {
        By popupLocator = By.cssSelector("#fancybox-wrap");
        WebElement cloudWindow = webElementsUtil.getDriver().findElement(popupLocator);
        if (cloudWindow.isDisplayed()) {
            By closeLocator = By.id("fancybox-close");
            webElementsUtil.clickElement(closeLocator);
        }
    }
}
