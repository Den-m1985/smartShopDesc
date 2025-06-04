package org.example.service.product.sadovod.search.add_to_backet;

import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;

public class CartAdder {
    private final WebElementsUtil webElementsUtil;

    public CartAdder(WebElementsUtil webElementsUtil) {
        this.webElementsUtil = webElementsUtil;
    }

    public void clickAddToCart() {
        By buttonLocator = By.cssSelector(".ty-btn__add-to-cart");
        webElementsUtil.clickElement(buttonLocator);
    }
}
