package org.example.service.product.alfa812.search;

import org.example.service.BasicLanguageManager;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;

public class GoodsSearcher extends BasicLanguageManager {
    private final WebElementsUtil webElementsUtil;

    public GoodsSearcher(WebElementsUtil webElementsUtil) {
        this.webElementsUtil = webElementsUtil;
    }

    public void searchGoods(String textToSearch) {
        By searchLocator = By.cssSelector("input.input_search[name='keyword']");
        webElementsUtil.putTextToInputField(searchLocator, textToSearch);

        By buttonLocator = By.cssSelector("input.button_search[type='submit']");
        webElementsUtil.clickElement(buttonLocator);
    }
}
