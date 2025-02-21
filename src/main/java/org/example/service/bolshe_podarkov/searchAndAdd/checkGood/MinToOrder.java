package org.example.service.bolshe_podarkov.searchAndAdd.checkGood;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.DriverChrome;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MinToOrder extends BasicLanguageManager {

    public boolean checkMinItem(int csvItem) {
        WebDriver driver = DriverChrome.getChromeDriver();
        try {
            String linkString = languageManager.get("bolshe_pod", "item.min");
            WebElement minItem = driver.findElement(By.className(linkString));
            String fieldValueText = minItem.getAttribute("value");  // Получение значения поля
            int result = Integer.parseInt(fieldValueText);
            if (result <= csvItem) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

}
