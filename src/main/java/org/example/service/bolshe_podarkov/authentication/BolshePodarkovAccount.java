package org.example.service.bolshe_podarkov.authentication;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.DriverChrome;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class BolshePodarkovAccount extends BasicLanguageManager {

    public boolean isEnterAccount(String login) {
        WebDriver driver = DriverChrome.getChromeDriver();
        String isAccount = languageManager.get("bolshe_pod", "is.enter.account");

        WebElement element = driver.findElement(By.className(isAccount));
        String str = element.getText();
        return str.equals(login);
    }

}
