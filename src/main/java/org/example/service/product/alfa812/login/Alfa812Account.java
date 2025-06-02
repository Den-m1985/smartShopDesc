package org.example.service.product.alfa812.login;

import org.example.service.BasicLanguageManager;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;

public class Alfa812Account extends BasicLanguageManager {
    private final WebElementsUtil webElementsUtil;

    public Alfa812Account(WebElementsUtil webElementsUtil) {
        this.webElementsUtil = webElementsUtil;
    }

    public boolean isEnterAccount() {
        return webElementsUtil.isEnterAccount(
                By.id(languageManager.get("alfa812", "field.account")),
                languageManager.get("alfa812", "field.exit")
        );
    }
}
