package org.example.service.product.bolshe_podarkov.login;

import org.example.service.BasicLanguageManager;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;

public class BolshePodarkovAccount extends BasicLanguageManager {
    private final WebElementsUtil webElementsUtil;

    public BolshePodarkovAccount(WebElementsUtil webElementsUtil) {
        this.webElementsUtil = webElementsUtil;
    }

    public boolean isEnterAccount(String login) {
        By by = By.className(languageManager.get("bolshe_pod", "is.enter.account"));
        return webElementsUtil.isEnterAccount(by, login);
    }
}
