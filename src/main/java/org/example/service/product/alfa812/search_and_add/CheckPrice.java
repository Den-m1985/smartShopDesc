package org.example.service.product.alfa812.search_and_add;

import org.example.service.BasicLanguageManager;
import org.example.service.csv_filter.csv.StructureCSV;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckPrice extends BasicLanguageManager {
    private final WebElementsUtil webElementsUtil;
    private int percent;
    private String webPrice;

    public CheckPrice(WebElementsUtil webElementsUtil) {
        this.webElementsUtil = webElementsUtil;
    }

    public boolean checkPrice(StructureCSV goods) {
        By by = By.cssSelector(languageManager.get("alfa812", "price"));
        WebElement priceClass = webElementsUtil.getDriver().findElement(by);
        String[] x = priceClass.getText().split("\\.");
        webPrice = x[0];

        if (webPrice.contains(" ")) {
            webPrice = webPrice.replace(" ", "");
        }
        int intParsePrice = Integer.parseInt(webPrice);
        percent = (intParsePrice * 100) / goods.getPrice();
        return percent < 101;
    }

    public String getErrorPrice() {
        return "цена на сайте " + webPrice + " больше на " + percent + "%";
    }

}
