package org.example.service.product.sadovod.search.check_good;

import org.example.enums.TextLinksSadovod;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;

public class GetPrice {
    private final WebElementsUtil webElementsUtil;

    public GetPrice(WebElementsUtil webElementsUtil) {
        this.webElementsUtil = webElementsUtil;
    }

    public String getPriceFromWeb() {
        By by = By.className(TextLinksSadovod.PRICE_LOCATOR.getString());
        return webElementsUtil.getText(by);
    }

}
