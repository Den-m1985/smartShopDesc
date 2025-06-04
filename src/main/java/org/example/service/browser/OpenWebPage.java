package org.example.service.browser;

import org.example.service.util.WebElementsUtil;

public class OpenWebPage {

    public OpenWebPage(WebElementsUtil webElementsUtil, String address) {
        webElementsUtil.getDriver().get(address);
    }
}
