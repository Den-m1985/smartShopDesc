package org.example.service.product.alfa812.add_to_cart;

import org.example.service.BasicLanguageManager;
import org.example.service.product.alfa812.search_and_add.CloudWindow;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;

public class AddGoods extends BasicLanguageManager {
    private final WebElementsUtil webElementsUtil;
    private final CloudWindow cloudWindow;

    public AddGoods(WebElementsUtil webElementsUtil) {
        this.webElementsUtil = webElementsUtil;
        this.cloudWindow = new CloudWindow(webElementsUtil);
    }

    public void addGoods(int item) {
        cloudWindow.closeModalWindow();

        By countItemLocator = By.name("amount");
        webElementsUtil.putTextToInputField(countItemLocator, String.valueOf(item));

        By buttonToBayLocator = By.className("buttonred");
        webElementsUtil.clickElement(buttonToBayLocator);

        By closeWindowLocator = By.id("fancybox-close");
        webElementsUtil.clickElement(closeWindowLocator);
    }
}
