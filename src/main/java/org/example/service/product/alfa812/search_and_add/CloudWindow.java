package org.example.service.product.alfa812.search_and_add;

import org.example.service.BasicLanguageManager;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CloudWindow extends BasicLanguageManager {
    private final WebElementsUtil webElementsUtil;

    public CloudWindow(WebElementsUtil webElementsUtil) {
        this.webElementsUtil = webElementsUtil;
    }

    public void closeModalWindow() {
        By popupLocator = By.id(languageManager.get("alfa812", "dont.go"));
        WebElement cloudWindow = null;
        try {
            cloudWindow = webElementsUtil.getDriver().findElement(popupLocator);
            if (cloudWindow.isDisplayed()) {
                Thread.sleep(2000);
                By closeLocator = By.id("fancybox-close");
                webElementsUtil.clickElement(closeLocator);
            }
        } catch (Exception ignore) {
        }
    }

}
