package org.example.service.bolshe_podarkov.searchAndAdd.addToBasket;

import org.example.controller.TabController;
import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.swing.*;
import java.util.List;

public class Basket extends BasicLanguageManager {
    private final BrowserManager browserManager;

    public Basket(BrowserManager browserManager, TabController tabController) {
        this.browserManager = browserManager;

        String basket = languageManager.get("bolshe_pod", "basket.address");
        browserManager.getDriver().get(basket);
        // if basket have goods
        if (checkBasket() != 0) {
            int option = dialogClearBasket();
            if (option == 0) {
                clearBasket();
                if (checkBasket() != 0) {
                    int errorOption = dialogErrorBasket();
                    if (errorOption == 0)
                        browserManager.getDriver().close();
                }
            }
        } else {
            tabController.getView().appendToTextArea("\n\n");
            tabController.getView().appendToTextArea(languageManager.get("main_messages", "basket.empty"));
        }
    }


    public int checkBasket() {
        String basketItem = languageManager.get("bolshe_pod", "basket.item");
        List<WebElement> cart = browserManager.getDriver().findElements(By.className(basketItem));
        return cart.size();
    }


    public void clearBasket() {
        String clearBasket = languageManager.get("bolshe_pod", "basket.clear");
        WebElement removeItemButton = browserManager.getDriver().findElement(By.className(clearBasket));
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
