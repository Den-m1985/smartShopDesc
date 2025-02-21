package org.example.service.bolshe_podarkov.searchAndAdd.checkGood;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.XPathWait;
import org.openqa.selenium.WebElement;

public class GetPrice extends BasicLanguageManager {

    public String getPriceFromWeb() {
        XPathWait pathWait = new XPathWait();
        //WebDriver driver = DriverChrome.getChromeDriver();

        String LinksSearch = languageManager.get("bolshe_pod", "price");
        WebElement price = pathWait.xPathClassName(LinksSearch);
        String str = price.getText();

        /*
        ["49", "руб.", "Старая", "цена
        29", "руб.", "Оптовая"]
         */
        String[] strArray = str.split(" ");

        return strArray[0].trim();
    }

}
