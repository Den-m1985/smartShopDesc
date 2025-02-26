package org.example.service.browser.chrome;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class XPathWait/* extends WaitChrome*/ {
    private final WebDriverWait wait;

    public XPathWait(WebDriver driver) {
        Duration duration = Duration.ofSeconds(10);
        wait = new WebDriverWait(driver, duration);
    }

    public WebElement xPath(String str) {
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(str)));
    }

    public WebElement xPathVisibility(String str) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(str)));
    }

    public WebElement xPathClassName(String str) {
        return wait.until(ExpectedConditions.elementToBeClickable(By.className(str)));
    }

}
