package org.example.service.product.bolshe_podarkov.check_good;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.BrowserManager;
import org.openqa.selenium.WebElement;

public class ProductPrice extends BasicLanguageManager {
private final BrowserManager browserManager;

    public ProductPrice(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    public String getPriceFromWeb() {
        String LinksSearch = languageManager.get("bolshe_pod", "price");
        WebElement price = browserManager.getXPathWait().xPathClassName(LinksSearch);
        String str = price.getText();

        /*
        ["49", "руб.", "Старая", "цена
        29", "руб.", "Оптовая"]
         */
        String[] strArray = str.split(" ");

        return strArray[0].trim();
    }

}
