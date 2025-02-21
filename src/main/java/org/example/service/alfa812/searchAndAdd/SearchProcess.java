package org.example.service.alfa812.searchAndAdd;

import org.example.DTO.DtoError;
import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.DriverChrome;
import org.example.service.csv_filtr.csv.StructureCSV;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchProcess extends BasicLanguageManager {
    private final List<DtoError> reportList;

    public SearchProcess(List<DtoError> reportList) {
        this.reportList = reportList;
    }

    public void executeProcess(StructureCSV goods) throws InterruptedException {
        WebDriver driver = DriverChrome.getChromeDriver();

        String goodsName = goods.getName();
        String goodsSize = goods.getArticular();
        int goodsItem = goods.getItem();

        new CloudWindow();

        // Search goods
        new SearchGoods(goodsName);

        new CloudWindow();

        // находим несколько имен в поисковике
        List<WebElement> products = driver.findElements(By.className(languageManager.get("alfa812", "products")));

        // проверяем на наличие товара
        List<WebElement> product = driver.findElements(By.className(languageManager.get("alfa812", "product")));

        // проверяем на ниличие выбора размера
        List<WebElement> size = driver.findElements(By.className(languageManager.get("alfa812", "b1c.option")));

        // если товара в поисковике более 1шт
        if (products.size() > 0) {
            // работаем с несколькими товарами
            new ManyGoods(products, goods);
            reportList.add(new DtoError(goodsName, goodsSize, languageManager.get("main_messages", "product.size")));

            // если товар есть
        } else if (product.size() > 0) {
            // если надо выбрать размер
            if (size.get(0).getText().length() > 0) {
                CommandSelectSize selectSize = new CommandSelectSize(goods);
                new CloudWindow();

                if (selectSize.getReportList() != null) {
                    reportList.add(selectSize.getReportList());
                }

            } else {
                CheckPrice check = new CheckPrice();
                if (check.checkPrice(goods)) {
                    new AddGoods(goodsItem);  // товар найден, добавляем в корзину
                    new CloudWindow();
                } else {
                    reportList.add(new DtoError(goodsName, goodsSize, check.getErrorPrice()));
                }
            }
        } else {
            reportList.add(new DtoError(goodsName, goodsSize, languageManager.get("main_messages", "product.out.stock")));
        }
    }

}
