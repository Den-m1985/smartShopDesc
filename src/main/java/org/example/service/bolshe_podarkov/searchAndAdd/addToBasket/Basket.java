package org.example.service.bolshe_podarkov.searchAndAdd.addToBasket;

import org.example.controller.TabController;
import org.example.service.BasicLanguageManager;
import org.example.service.browser.chrome.DriverChrome;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.swing.*;
import java.util.List;

public class Basket extends BasicLanguageManager {
    private final WebDriver driver = DriverChrome.getChromeDriver();

    public Basket(TabController tabController) {
        String basket = languageManager.get("bolshe_pod", "basket.address");
        driver.get(basket);
        // if basket have goods
        if (checkBasket() != 0) {
            int option = dialogClearBasket();
            if (option == 0) {
                clearBasket();
                if (checkBasket() != 0) {
                    int errorOption = dialogErrorBasket();
                    if (errorOption == 0)
                        driver.close();
                }
            }
        } else {
            tabController.getView().appendToTextArea("\n\n");
            tabController.getView().appendToTextArea(languageManager.get("main_messages", "basket.empty"));
        }
    }


    public int checkBasket() {
        String basketItem = languageManager.get("bolshe_pod", "basket.item");
        List<WebElement> cart = driver.findElements(By.className(basketItem));
        return cart.size();
    }


    public void clearBasket() {
        String clearBasket = languageManager.get("bolshe_pod", "basket.clear");
        WebElement removeItemButton = driver.findElement(By.className(clearBasket));
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
