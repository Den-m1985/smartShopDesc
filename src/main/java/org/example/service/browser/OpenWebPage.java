package org.example.service.browser;

import org.example.service.browser.chrome.BrowserManager;
import org.example.service.util.WebElementsUtil;

public class OpenWebPage {

    public OpenWebPage(BrowserManager browserManager, String address) {
        browserManager.getDriver().get(address);
    }

    public OpenWebPage(WebElementsUtil webElementsUtil, String address) {
        webElementsUtil.openPage(address);
    }
}
