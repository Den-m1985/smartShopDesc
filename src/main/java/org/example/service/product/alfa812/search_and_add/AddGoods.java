package org.example.service.product.alfa812.search_and_add;

import org.example.service.BasicLanguageManager;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.WebElement;

public class AddGoods extends BasicLanguageManager {
    private final WebElementsUtil webElementsUtil;
    private final CloudWindow cloudWindow;

    public AddGoods(WebElementsUtil webElementsUtil) {
        this.webElementsUtil = webElementsUtil;
        this.cloudWindow = new CloudWindow(webElementsUtil);
    }

    public void addGoods(int item) {
        cloudWindow.closeModalWindow();

        WebElement search;

        String linkAddItem = languageManager.get("alfa812", "item.add");
        search = webElementsUtil.getXPathWait().xPathVisibility(linkAddItem);
        search.click();

        search.clear();
        String strItem = String.valueOf(item);
        search.sendKeys(strItem);

        String linkClickBay = languageManager.get("alfa812", "clickby");
        WebElement buttonSearch = webElementsUtil.getXPathWait().xPath(linkClickBay);
        buttonSearch.click();

        String linkCloseWindow = languageManager.get("alfa812", "close.window");
        WebElement buttonClose = webElementsUtil.getXPathWait().xPath(linkCloseWindow);
        buttonClose.click();
    }
}
