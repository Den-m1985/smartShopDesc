package org.example.service.browser.chrome;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserManager {
    private final WebDriver driver;
    private final XPathWait xPathWait;
    private final WebDriverWait wait;
    private final Duration duration;

    public BrowserManager() {
        driver = new DriverChrome().getDriverChrome();
        xPathWait = new XPathWait(driver);
        duration = Duration.ofSeconds(10);
        wait = new WebDriverWait(driver, duration); // Ожидание до 10 секунд
    }

    public WebDriver getDriver() {
        return driver;
    }

    public XPathWait getXPathWait() {
        return xPathWait;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public Duration getDuration() {
        return duration;
    }
}
