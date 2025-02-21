package org.example.service.alfa812.searchAndAdd;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.DriverChrome;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CloudWindow extends BasicLanguageManager {

    public CloudWindow() throws InterruptedException {
        WebDriver driver = DriverChrome.getChromeDriver();

        WebElement cloudWindow = driver.findElement(By.id(languageManager.get("alfa812", "dont.go")));
        if (cloudWindow.isDisplayed()) {
            Thread.sleep(2000);
            WebElement cloudWindowClose = driver.findElement(
                    By.id(languageManager.get("alfa812", "fancybox.close")));
            cloudWindowClose.click();
        }
    }

}
