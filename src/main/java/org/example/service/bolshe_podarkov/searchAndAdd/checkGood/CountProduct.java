package org.example.service.bolshe_podarkov.searchAndAdd.checkGood;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.DriverChrome;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CountProduct extends BasicLanguageManager {

    public int countProduct() {
        WebDriver driver = DriverChrome.getChromeDriver();
        String cartField = languageManager.get("bolshe_pod", "field.cart");
        try {
            List<WebElement> goodsArray = driver.findElements(By.className(cartField));
            return goodsArray.size();
        } catch (Exception ignored) {
        }
        return 0;
    }

}
