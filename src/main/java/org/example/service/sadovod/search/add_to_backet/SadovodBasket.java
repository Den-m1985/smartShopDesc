package org.example.service.sadovod.search.add_to_backet;

import org.example.controller.TabController;
import org.example.enums.TextLinks;
import org.example.enums.TextLinksSadovod;
import org.example.service.BasicLanguageManager;
import org.example.service.browser.OpenWebPage;
import org.example.service.browser.chrome.BrowserManager;
import org.example.service.util.NumberParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.*;

public class SadovodBasket extends BasicLanguageManager {
    private final BrowserManager browserManager;
    private final TabController tabController;
    private final NumberParser numberParser;

    public SadovodBasket(BrowserManager browserManager, TabController tabController) {
        this.browserManager = browserManager;
        this.tabController = tabController;
        this.numberParser = new NumberParser();
    }


    public void handleBacket() {
        new OpenWebPage(browserManager, TextLinksSadovod.BASKET_ADDRESS.getString());

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
                        browserManager.getDriver().quit();
                }
            }
        } else {
            tabController.getView().appendToTextArea("\n\n");
            tabController.getView().appendToTextArea(TextLinks.BASKET_EMPTY.getString());
        }

    }

    private String getCountProductInCart() {
        By by = By.className("ty-minicart-count");
        WebElement price = browserManager.getWait().until(
                ExpectedConditions.visibilityOfElementLocated(by)
        );
        return price.getText();
    }

    public void clearBasket() {
        By by = By.cssSelector("a.ty-btn__tertiary[href*='checkout.clear']");
        if (isButtonAvailable(by)) {
            WebElement button = browserManager.getWait().until(
                    ExpectedConditions.elementToBeClickable(by)
            );
            button.click();
        }
    }

    public boolean isButtonAvailable(By by) {
        try {
            WebElement button = browserManager.getWait().until(
                    ExpectedConditions.visibilityOfElementLocated(by)
            );
            return button.isDisplayed() && button.isEnabled();
        } catch (Exception e) {
            return false;
        }
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
