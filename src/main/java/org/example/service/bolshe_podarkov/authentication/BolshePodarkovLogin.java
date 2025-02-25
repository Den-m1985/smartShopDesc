package org.example.service.bolshe_podarkov.authentication;

import org.example.controller.TabController;
import org.example.service.browser.chrome.DriverChrome;
import org.example.service.browser.login.AbstractLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BolshePodarkovLogin  extends AbstractLoginPage {

    public BolshePodarkovLogin(TabController tabController) throws Exception {
        super(tabController);
    }

    @Override
    protected void tryToLogInAccount(String[] decryptedData) {
        WebDriver driver = DriverChrome.getChromeDriver();

        // field Login
        String LinksLogin =  languageManager.get("bolshe_pod", "field.login");
        WebElement loginField = driver.findElement(By.name(LinksLogin));
        loginField.click();
        loginField.clear();
        loginField.sendKeys(decryptedData[0]);
//        decryptedData[0] = "";

        // field Password
        String LinksPassword = languageManager.get("bolshe_pod", "field.password");
        WebElement passwordField = driver.findElement(By.name(LinksPassword));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(decryptedData[1]);
        decryptedData[1] = "";

        // field button enter
        String buttonEnter = languageManager.get("bolshe_pod", "button.enter");
        WebElement buttonEnterField = driver.findElement(By.name(buttonEnter));
        buttonEnterField.click();
    }
}
