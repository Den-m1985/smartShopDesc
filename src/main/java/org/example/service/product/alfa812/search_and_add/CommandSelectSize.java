package org.example.service.product.alfa812.search_and_add;

import org.example.DTO.DtoError;
import org.example.service.BasicLanguageManager;
import org.example.service.csv_filter.csv.StructureCSV;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CommandSelectSize extends BasicLanguageManager {
    private DtoError reportList;
    private final WebElementsUtil webElementsUtil;
    private final AddGoods addGoods;
    private final CheckPrice checkPrice;

    public CommandSelectSize(WebElementsUtil webElementsUtil) {
        this.webElementsUtil = webElementsUtil;
        this.addGoods = new AddGoods(webElementsUtil);
        this.checkPrice = new CheckPrice(webElementsUtil);
    }

    public void selectSize(StructureCSV goods) {
        By by = By.className(languageManager.get("alfa812", "b1c.option"));
        WebElement size = webElementsUtil.getDriver().findElement(by);

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
            select.selectByVisibleText(res);

            if (checkPrice.checkPrice(goods)) {
                addGoods.addGoods(goodsItem);
            } else {
                reportList = new DtoError(goodsName, goodsSize, checkPrice.getErrorPrice());
            }

        } catch (Exception e) {
            reportList = new DtoError(goodsName, goodsSize, languageManager.get("main_messages", "error.size"));
        }
    }

    public DtoError getReportList() {
        return reportList;
    }
}
