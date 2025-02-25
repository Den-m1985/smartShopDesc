package org.example.service.browser.login;

import org.example.controller.TabController;
import org.example.enums.NameProducts;
import org.example.service.BasicLanguageManager;
import org.example.service.alfa812.account.Alfa812Account;
import org.example.service.bolshe_podarkov.authentication.BolshePodarkovAccount;
import org.example.service.login_storage.LoginStorage;
import org.example.ui.authorisation.AuthorizationWindow;

import javax.swing.*;

public abstract class AbstractLoginPage extends BasicLanguageManager {
    protected LoginStorage loginStorage;


    public AbstractLoginPage(TabController tabController) throws Exception {
        NameProducts product = tabController.getProduct();
        loginStorage = new LoginStorage(product);
        Alfa812Account alfa812Account = new Alfa812Account();
        BolshePodarkovAccount bolshePodarkovAccount = new BolshePodarkovAccount();
        boolean isEnter = false;

        int attempt = 2;
        while (attempt > 0) {
            try {
                String[] decryptedData = loginStorage.readFromFile();
                if (decryptedData == null){
                    new AuthorizationWindow(product);
                    decryptedData = loginStorage.readFromFile();
                }

                tryToLogInAccount(decryptedData);

                switch (product) {
                    case BOLSHE_PODARKOV -> {
                        assert decryptedData != null;
                        isEnter =bolshePodarkovAccount.isEnterAccount(decryptedData[0]);
                    }
                    case ALFA_812 -> {
                        isEnter = alfa812Account.isEnterAccount();
                    }
                }

                if (isEnter) {
                    attempt = 0;
                }
            } catch (Exception ignored) {
                new AuthorizationWindow(product);
                if (attempt == 1) {
                    String errorMessage= languageManager.get("main_messages", "failed.login");
                    JOptionPane.showMessageDialog(null, errorMessage);
                    throw new RuntimeException(errorMessage);
                }
            }
            attempt--;
        }
    }

    protected abstract void tryToLogInAccount(String[] decryptedData) throws Exception;
}
