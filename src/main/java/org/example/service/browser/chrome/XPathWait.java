package org.example.service.browser.chrome;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class XPathWait extends WaitChrome {
    WebDriverWait wait = WaitChrome.getChromeDriverWait();

    public WebElement xPath(String str) {
        wait = WaitChrome.getChromeDriverWait();
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(str)));
    }

    public WebElement xPathVisibility(String str) {
        wait = WaitChrome.getChromeDriverWait();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(str)));
    }

    public WebElement xPathClassName(String str) {
        wait = WaitChrome.getChromeDriverWait();
        return wait.until(ExpectedConditions.elementToBeClickable(By.className(str)));
    }

}
