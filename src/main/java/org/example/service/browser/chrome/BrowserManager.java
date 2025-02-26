package org.example.service.browser.chrome;

import org.openqa.selenium.WebDriver;

public class BrowserManager {
    private final WebDriver driver;
    private final XPathWait xPathWait;

    public BrowserManager() {
        driver = new DriverChrome().getDriverChrome();
        xPathWait = new XPathWait(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public XPathWait getXPathWait() {
        return xPathWait;
    }
}
