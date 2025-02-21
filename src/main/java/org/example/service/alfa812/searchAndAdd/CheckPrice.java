package org.example.service.alfa812.searchAndAdd;

import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.DriverChrome;
import org.example.service.csv_filtr.csv.StructureCSV;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckPrice extends BasicLanguageManager {
    int percent;
    String webPrice;
    String goodsName;
    String goodsSize;
    int intGoodsPrice;
    int goodsItem;


    public boolean checkPrice(StructureCSV goods) {
        WebDriver driver = DriverChrome.getChromeDriver();

        goodsName = goods.getName();
        goodsSize = goods.getArticular();
        intGoodsPrice = goods.getPrice();
        goodsItem = goods.getItem();

        String priceField = languageManager.get("alfa812", "price");
        WebElement priceClass = driver.findElement(By.cssSelector(priceField));
        String[] x = priceClass.getText().split("\\.");
        webPrice = x[0];

        if (webPrice.contains(" ")) {
            webPrice = webPrice.replaceAll(" ", "");
        }
        int intParsePrice = Integer.parseInt(webPrice);
        percent = (intParsePrice * 100) / intGoodsPrice;
        return percent < 101;
    }

    public String getErrorPrice() {
        return "цена на сайте " + webPrice + " больше на " + percent + "%";
    }

}
