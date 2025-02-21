package org.example.service.alfa812.account;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.DriverChrome;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class Alfa812Account extends BasicLanguageManager {

    public boolean isEnterAccount() {
        WebDriver driver = DriverChrome.getChromeDriver();
        String isAccount = languageManager.get("alfa812", "field.account");

        WebElement element = driver.findElement(By.id(isAccount));
        String str = element.getText();
        List<String> array = Arrays.stream(str.split(" ")).toList();
        return array.contains(languageManager.get("alfa812", "field.exit"));
    }

}
