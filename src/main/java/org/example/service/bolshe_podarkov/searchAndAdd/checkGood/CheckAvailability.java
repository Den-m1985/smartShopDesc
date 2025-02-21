package org.example.service.bolshe_podarkov.searchAndAdd.checkGood;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.DriverChrome;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckAvailability extends BasicLanguageManager {

    public boolean isPresentButtonToCart() {
        WebDriver driver = DriverChrome.getChromeDriver();
        String toBasketString = languageManager.get("bolshe_pod", "to.basket");
        try {
            WebElement button = driver.findElement(By.className(toBasketString));
            String str = button.getText();
            String subscribe = languageManager.get("bolshe_pod", "subscribe");
            return !str.equals(subscribe);
        } catch (Exception e) {
            return false;
        }
    }

}
