package org.example.service.browser;

import org.example.service.browser.chrome.BrowserManager;

public class OpenWebSite {

    public OpenWebSite(BrowserManager browserManager, String address) {
        browserManager.getDriver().get(address);
    }

}
