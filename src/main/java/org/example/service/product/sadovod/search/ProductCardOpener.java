package org.example.service.product.sadovod.search;

import org.example.enums.TextLinksSadovod;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;

public class ProductCardOpener {
    private final WebElementsUtil webElementsUtil;

    public ProductCardOpener(WebElementsUtil webElementsUtil) {
        this.webElementsUtil = webElementsUtil;
    }

    public void openProductDetails() {
        By productPreviewLocator = By.cssSelector(TextLinksSadovod.PRODUCT_PREVIEW_LOCATOR.getString());
        webElementsUtil.clickElement(productPreviewLocator);

        webElementsUtil.readyStateDocument();
    }
}
