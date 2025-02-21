package org.example.service.alfa812.searchAndAdd;

import org.example.DTO.DtoError;
import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.DriverChrome;
import org.example.service.csv_filtr.csv.StructureCSV;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CommandSelectSize extends BasicLanguageManager {
    private DtoError reportList;


    public CommandSelectSize(StructureCSV goods) {
        WebDriver driver = DriverChrome.getChromeDriver();
        WebElement size = driver.findElement(By.className(languageManager.get("alfa812", "b1c.option")));

        String text = size.getText();

        String goodsName = goods.getName();
        String goodsSize = goods.getArticular();
        int goodsItem = goods.getItem();

        /*
        частая ошибка, что спарсено не так как с сайта
        то вместо запятой /
        то регистры разные
        здесь исправляем все эти косяки
         */
        String res = new AnalyzeText().substituteValues(goodsSize, text);

        try {
            Select select = new Select(size);
            select.selectByVisibleText(res);  // выбираем размер

            new CloudWindow();

            CheckPrice check = new CheckPrice();
            if (check.checkPrice(goods)) {
                new AddGoods(goodsItem);  // товар найден, добавляем в корзину
            } else {
                reportList = new DtoError(goodsName, goodsSize, check.getErrorPrice());
            }

        } catch (Exception e) {
            reportList = new DtoError(goodsName, goodsSize, languageManager.get("main_messages", "error.size"));
        }
    }

    public DtoError getReportList() {
        return reportList;
    }
}
