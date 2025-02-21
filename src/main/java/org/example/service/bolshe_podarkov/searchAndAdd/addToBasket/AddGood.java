package org.example.service.bolshe_podarkov.searchAndAdd.addToBasket;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.DriverChrome;
import org.example.service.csv_filtr.csv.StructureCSV;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AddGood extends BasicLanguageManager {

    public AddGood(StructureCSV product) {
        WebDriver driver = DriverChrome.getChromeDriver();

        String linkString = languageManager.get("bolshe_pod", "item.add");
        WebElement elementLocator = driver.findElement(By.className(linkString));

        Actions actions = new Actions(driver);
        actions.doubleClick(elementLocator).perform();
        String goodsItem = String.valueOf(product.getItem());
        elementLocator.sendKeys(goodsItem);

        String linkAddItem = languageManager.get("bolshe_pod", "to.basket");
        WebElement linkButton = driver.findElement(By.className(linkAddItem));
        linkButton.click();
    }
}
