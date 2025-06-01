package org.example.service.product.bolshe_podarkov.login;

import org.example.controller.TabController;
import org.example.enums.TextLinksBolshePodarkov;
import org.example.service.browser.login.AbstractLoginPage;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;

public class BolshePodarkovLogin extends AbstractLoginPage {

    public BolshePodarkovLogin(WebElementsUtil webElementsUtil, TabController tabController) throws Exception {
        super(webElementsUtil, tabController);
    }

    @Override
    protected void tryToLogInAccount(String[] decryptedData) {
        By emailLocator = By.cssSelector(TextLinksBolshePodarkov.LOGIN_FIELD.getString());
        webElementsUtil.putTextToInputField(emailLocator, decryptedData[0]);

        By passwordLocator = By.cssSelector(TextLinksBolshePodarkov.PASSWORD_FIELD.getString());
        webElementsUtil.putTextToInputField(passwordLocator, decryptedData[1]);
        decryptedData[1] = "";

        By buttonLocator = By.cssSelector(TextLinksBolshePodarkov.BUTTON_ENTER.getString());
        webElementsUtil.clickElement(buttonLocator);
    }
}
