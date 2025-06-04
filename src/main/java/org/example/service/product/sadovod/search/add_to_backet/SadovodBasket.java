package org.example.service.product.sadovod.search.add_to_backet;

import org.example.controller.TabController;
import org.example.enums.TextLinks;
import org.example.enums.TextLinksSadovod;
import org.example.service.BasicLanguageManager;
import org.example.service.browser.OpenWebPage;
import org.example.service.util.NumberParser;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.*;

public class SadovodBasket extends BasicLanguageManager {
    private final WebElementsUtil webElementsUtil;
    private final TabController tabController;
    private final NumberParser numberParser;

    public SadovodBasket(WebElementsUtil webElementsUtil, TabController tabController) {
        this.webElementsUtil = webElementsUtil;
        this.tabController = tabController;
        this.numberParser = new NumberParser();
    }


    public void handleBacket() {
        new OpenWebPage(webElementsUtil, TextLinksSadovod.BASKET_ADDRESS.getString());

        String webCartCount = getCountProductInCart();
        int countInCart = numberParser.stringToInt(webCartCount);

        if (countInCart != 0) {
            int option = dialogClearBasket();
            if (option == 0) {
                clearBasket();
                String cartCount = getCountProductInCart();
                int count = numberParser.stringToInt(cartCount);
                if (count != 0) {
                    int errorOption = dialogErrorBasket();
                    if (errorOption == 0)
                        webElementsUtil.getDriver().quit();
                }
            }
        } else {
            tabController.getView().appendToTextArea("\n\n");
            tabController.getView().appendToTextArea(TextLinks.BASKET_EMPTY.getString());
        }

    }

    private String getCountProductInCart() {
        By by = By.className(TextLinksSadovod.COUNT_PRODUCT_IN_CART.getString());
        return webElementsUtil.getText(by);
    }

    public void clearBasket() {
        By by = By.cssSelector(TextLinksSadovod.CLEAR_BASKET.getString());
        WebElement button = webElementsUtil.getWait().until(
                ExpectedConditions.elementToBeClickable(by)
        );
        button.click();
    }

    private int dialogClearBasket() {
        Object[] options = {"Очистить корзину", "Оставить"};
        String text = "В корзине есть товары";
        return JOptionPane.showOptionDialog(null, text, "Вопрос",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
    }

    private int dialogErrorBasket() {
        Object[] options = {"Попробовать снова", "Продолжить"};
        String text = "В корзине есть товары";
        return JOptionPane.showOptionDialog(null, text, "Вопрос",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
    }
}
