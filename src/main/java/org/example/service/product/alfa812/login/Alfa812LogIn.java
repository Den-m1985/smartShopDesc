package org.example.service.product.alfa812.login;

import org.example.controller.TabController;
import org.example.service.browser.login.AbstractLoginPage;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;

public class Alfa812LogIn extends AbstractLoginPage {

    public Alfa812LogIn(WebElementsUtil webElementsUtil, TabController tabController) throws Exception {
        super(webElementsUtil, tabController);
    }

    @Override
    protected void tryToLogInAccount(String[] decryptedData) {
        By emailLocator = By.cssSelector("input[name='email'][data-format='email']");
        webElementsUtil.putTextToInputField(emailLocator, decryptedData[0]);
        decryptedData[0] = "";

        By passwordLocator = By.cssSelector("input[name='password'][type='password']");
        webElementsUtil.putTextToInputField(passwordLocator, decryptedData[1]);
        decryptedData[1] = "";

        By buttonLocator = By.cssSelector("input.button[name='login'][type='submit']");
        webElementsUtil.clickElement(buttonLocator);
    }
}
