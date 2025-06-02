package org.example.service.product.bolshe_podarkov.add_to_basket;

import org.example.controller.TabController;
import org.example.service.BasicLanguageManager;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.swing.*;
import java.util.List;

public class Basket extends BasicLanguageManager {
    private final WebElementsUtil webElementsUtil;

    public Basket(WebElementsUtil webElementsUtil, TabController tabController) {
        this.webElementsUtil = webElementsUtil;

        String basket = languageManager.get("bolshe_pod", "basket.address");
        webElementsUtil.getDriver().get(basket);
        // if basket have goods
        if (checkBasket() != 0) {
            int option = dialogClearBasket();
            if (option == 0) {
                clearBasket();
                if (checkBasket() != 0) {
                    int errorOption = dialogErrorBasket();
                    if (errorOption == 0)
                        webElementsUtil.getDriver().close();
                }
            }
        } else {
            tabController.getView().appendToTextArea("\n\n");
            tabController.getView().appendToTextArea(languageManager.get("main_messages", "basket.empty"));
        }
    }


    public int checkBasket() {
        String basketItem = languageManager.get("bolshe_pod", "basket.item");
        List<WebElement> cart = webElementsUtil.getDriver().findElements(By.className(basketItem));
        return cart.size();
    }


    public void clearBasket() {
        String clearBasket = languageManager.get("bolshe_pod", "basket.clear");
        WebElement removeItemButton = webElementsUtil.getDriver().findElement(By.className(clearBasket));
        removeItemButton.click();
    }


    private int dialogClearBasket() {
        Object[] options = {"Очистить корзину", "Оставить"};
        String text = "В корзине есть товары";

        // window with 2 button
        return JOptionPane.showOptionDialog(null, text, "Вопрос",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
    }

    private int dialogErrorBasket() {
        Object[] options = {"Попробовать снова", "Продолжить"};
        String text = "В корзине есть товары";

        // window with 2 button
        return JOptionPane.showOptionDialog(null, text, "Вопрос",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
    }

}
