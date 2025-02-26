package org.example.service.alfa812.login;

import org.example.controller.TabController;
import org.example.service.browser.chrome.BrowserManager;
import org.example.service.browser.login.AbstractLoginPage;
import org.openqa.selenium.WebElement;

public class Alfa812LogIn extends AbstractLoginPage {

    public Alfa812LogIn(BrowserManager browserManager, TabController tabController) throws Exception {
        super(browserManager, tabController);
    }

    @Override
    protected void tryToLogInAccount(String[] decryptedData) {
//        XPathWait pathWait = new XPathWait(driver);

        // field Login
        String linksLogin = languageManager.get("alfa812", "field.login");
        WebElement loginField = browserManager.getXPathWait().xPath(linksLogin);
        loginField.click();

        loginField.sendKeys(decryptedData[0]); // enter login
        decryptedData[0] = "";

        // field Password
        String linksPassword = languageManager.get("alfa812", "field.password");
        WebElement passwordField = browserManager.getXPathWait().xPath(linksPassword);
        passwordField.click();

        passwordField.sendKeys(decryptedData[1]);  // enter password
        decryptedData[1] = "";

        // field Enter
        String linksEnter = languageManager.get("alfa812", "button.enter");
        WebElement enterField = browserManager.getXPathWait().xPath(linksEnter);
        enterField.click();
    }

}
