package org.example.service.alfa812.searchAndAdd;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.XPathWait;
import org.openqa.selenium.WebElement;

public class ShoppingCart extends BasicLanguageManager {

    public void clickCart() {
        XPathWait pathWait = new XPathWait();
        String linkCart = languageManager.get("alfa812", "field.cart");
        WebElement buttonSearch = pathWait.xPath(linkCart);
        buttonSearch.click();
    }


    public void countGoodsInCart() {
        /*
        Не удается сделать подсчет кол-ва товара в корзине.
         */
    }

}
