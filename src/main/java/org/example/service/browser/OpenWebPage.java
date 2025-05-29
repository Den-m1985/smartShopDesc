package org.example.service.browser;

import org.example.service.browser.chrome.BrowserManager;

public class OpenWebPage {

    public OpenWebPage(BrowserManager browserManager, String address) {
        browserManager.getDriver().get(address);
    }

}
