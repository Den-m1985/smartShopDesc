package org.example.service.bolshe_podarkov.authentication;

import org.example.controller.TabController;
import org.example.service.browser.chrome.BrowserManager;
import org.example.service.browser.login.AbstractLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BolshePodarkovLogin  extends AbstractLoginPage {

    public BolshePodarkovLogin(BrowserManager browserManager, TabController tabController) throws Exception {
        super(browserManager, tabController);
    }

    @Override
    protected void tryToLogInAccount(String[] decryptedData) {
        // field Login
        String LinksLogin =  languageManager.get("bolshe_pod", "field.login");
        WebElement loginField = browserManager.getDriver().findElement(By.name(LinksLogin));
        loginField.click();
        loginField.clear();
        loginField.sendKeys(decryptedData[0]);
//        decryptedData[0] = "";

        // field Password
        String LinksPassword = languageManager.get("bolshe_pod", "field.password");
        WebElement passwordField = browserManager.getDriver().findElement(By.name(LinksPassword));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(decryptedData[1]);
        decryptedData[1] = "";

        // field button enter
        String buttonEnter = languageManager.get("bolshe_pod", "button.enter");
        WebElement buttonEnterField = browserManager.getDriver().findElement(By.name(buttonEnter));
        buttonEnterField.click();
    }
}
