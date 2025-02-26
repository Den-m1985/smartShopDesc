package org.example.service.bolshe_podarkov.authentication;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BolshePodarkovAccount extends BasicLanguageManager {
    private final BrowserManager browserManager;

    public BolshePodarkovAccount(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    public boolean isEnterAccount(String login) {
        String isAccount = languageManager.get("bolshe_pod", "is.enter.account");

        WebElement element = browserManager.getDriver().findElement(By.className(isAccount));
        String str = element.getText();
        return str.equals(login);
    }

}
