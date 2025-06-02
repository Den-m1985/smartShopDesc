package org.example.service.product.bolshe_podarkov.check_good;

import org.example.service.BasicLanguageManager;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductCounter extends BasicLanguageManager {
    private final WebElementsUtil webElementsUtil;

    public ProductCounter(WebElementsUtil webElementsUtil) {
        this.webElementsUtil = webElementsUtil;
    }

    public int countProduct() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String cartField = languageManager.get("bolshe_pod", "field.cart");
        try {
            List<WebElement> goodsArray = webElementsUtil.getDriver().findElements(By.className(cartField));
            return goodsArray.size();
        } catch (Exception ignored) {
        }
        return 0;
    }

}
