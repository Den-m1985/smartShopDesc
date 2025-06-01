package org.example.service.product.alfa812.search_and_add;

import org.example.service.BasicLanguageManager;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;

public class CloudWindow extends BasicLanguageManager {
    private final WebElementsUtil webElementsUtil;

    public CloudWindow(WebElementsUtil webElementsUtil) {
        this.webElementsUtil = webElementsUtil;
    }

    public void closeModalWindow() {
        By by = By.id(languageManager.get("alfa812", "dont.go"));
        webElementsUtil.clickElementNoWait(by);
    }

}
