package org.example.service.alfa812.login;

import org.example.enums.NameProducts;
import org.example.service.browser.chrome.XPathWait;
import org.example.service.browser.login.AbstractLoginPage;
import org.openqa.selenium.WebElement;

public class Alfa812LogIn extends AbstractLoginPage {
    private final NameProducts product;

    public Alfa812LogIn(NameProducts product) throws Exception {
        super(product);
        this.product = product;
    }

    @Override
    protected void tryToLogInAccount(String[] decryptedData) {
        XPathWait pathWait = new XPathWait();

        // field Login
        String linksLogin = languageManager.get("alfa812", "field.login");
        WebElement loginField = pathWait.xPath(linksLogin);
        loginField.click();

        loginField.sendKeys(decryptedData[0]); // enter login
        decryptedData[0] = "";

        // field Password
        String linksPassword = languageManager.get("alfa812", "field.password");
        WebElement passwordField = pathWait.xPath(linksPassword);
        passwordField.click();

        passwordField.sendKeys(decryptedData[1]);  // enter password
        decryptedData[1] = "";

        // field Enter
        String linksEnter = languageManager.get("alfa812", "button.enter");
        WebElement enterField = pathWait.xPath(linksEnter);
        enterField.click();
    }

}
