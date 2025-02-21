package org.example.service.browser;

import org.example.service.browser.chrome.DriverChrome;
import org.openqa.selenium.WebDriver;

public class OpenWebSite {

    public OpenWebSite(String address) {
        WebDriver driver = DriverChrome.getChromeDriver();
        driver.get(address);
    }

}
