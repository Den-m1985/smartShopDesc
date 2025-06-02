package org.example.service.product.bolshe_podarkov.add_to_basket;

import org.example.service.BasicLanguageManager;
import org.example.service.csv_filter.csv.StructureCSV;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GoodsAdder extends BasicLanguageManager {
    private final WebElementsUtil webElementsUtil;

    public GoodsAdder(WebElementsUtil webElementsUtil) {
        this.webElementsUtil = webElementsUtil;
    }

    public void addToCart(StructureCSV product) {
        By fieldCountLocator = By.className(languageManager.get("bolshe_pod", "item.add"));
        WebElement element = webElementsUtil.getDriver().findElement(fieldCountLocator);

        Actions actions = new Actions(webElementsUtil.getDriver());
        actions.doubleClick(element).perform();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String goodsItem = String.valueOf(product.getItem());
        element.sendKeys(goodsItem);

        String newValue = element.getAttribute("value");
        if (!goodsItem.equals(newValue)) {
            throw new RuntimeException("Не смог добавить нужное кол-во");
        }

        By buttonToBayLocator = By.className(languageManager.get("bolshe_pod", "to.basket"));
        webElementsUtil.clickElement(buttonToBayLocator);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
