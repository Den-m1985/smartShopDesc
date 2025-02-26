package org.example.service.bolshe_podarkov.searchAndAdd.addToBasket;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.BrowserManager;
import org.example.service.csv_filter.csv.StructureCSV;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AddGood extends BasicLanguageManager {

    public AddGood(BrowserManager browserManager, StructureCSV product) {
        String linkString = languageManager.get("bolshe_pod", "item.add");
        WebElement elementLocator = browserManager.getDriver().findElement(By.className(linkString));

        Actions actions = new Actions(browserManager.getDriver());
        actions.doubleClick(elementLocator).perform();
        String goodsItem = String.valueOf(product.getItem());
        elementLocator.sendKeys(goodsItem);

        String linkAddItem = languageManager.get("bolshe_pod", "to.basket");
        WebElement linkButton = browserManager.getDriver().findElement(By.className(linkAddItem));
        linkButton.click();
    }
}
