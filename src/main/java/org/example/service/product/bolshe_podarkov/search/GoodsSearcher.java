package org.example.service.product.bolshe_podarkov.search;

import org.example.service.BasicLanguageManager;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class GoodsSearcher extends BasicLanguageManager {
    private final WebElementsUtil webElementsUtil;

    public GoodsSearcher(WebElementsUtil webElementsUtil) {
        this.webElementsUtil = webElementsUtil;
    }

    public void searchByArticular(String goodsSize) {
        By fieldSearchLocator = By.className(languageManager.get("bolshe_pod", "field.search"));
        WebElement webElement =webElementsUtil.putTextToInputField(fieldSearchLocator, goodsSize);
        webElement.sendKeys(Keys.ENTER);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
