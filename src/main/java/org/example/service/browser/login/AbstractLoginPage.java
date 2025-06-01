package org.example.service.browser.login;

import org.example.controller.TabController;
import org.example.service.BasicLanguageManager;
import org.example.service.util.WebElementsUtil;
import org.example.ui.authorisation.AuthorizationWindow;

import javax.swing.*;

public abstract class AbstractLoginPage extends BasicLanguageManager {
    protected WebElementsUtil webElementsUtil;
    private final AuthorizationWindow authorizationWindow;
    protected final TabController tabController;
    protected final AccountManager accountManager;

    protected AbstractLoginPage(WebElementsUtil webElementsUtil, TabController tabController) throws Exception {
        this.webElementsUtil = webElementsUtil;
        this.tabController = tabController;
        this.authorizationWindow = new AuthorizationWindow();
        this.accountManager = new AccountManager(webElementsUtil, tabController.getProduct());

        performLogin();
    }

    protected void performLogin() throws Exception {
        int attempt = 3;
        while (attempt > 0) {
            try {
                String[] credentials = getCredentialsWithFallback();
                if (credentials == null) continue;

                tryToLogInAccount(credentials);

                if (accountManager.isLoginSuccessful(credentials)) {
                    return;
                }

                handleFailedLoginAttempt(attempt);
            } catch (Exception e) {
                handleFailedLoginAttempt(attempt);
            }
            attempt--;
        }
        throw new RuntimeException(getErrorMessage());
    }

    private String[] getCredentialsWithFallback() throws Exception {
        String[] credentials = accountManager.getCredentials();
        if (credentials == null) {
            authorizationWindow.showWindow(tabController.getProduct());
            credentials = accountManager.getCredentials();
        }
        return credentials;
    }

    protected void handleFailedLoginAttempt(int attempt) throws Exception {
        if (attempt == 1) {
            JOptionPane.showMessageDialog(null, getErrorMessage());
            throw new RuntimeException(getErrorMessage());
        }
        authorizationWindow.showWindow(tabController.getProduct());
    }

    protected String getErrorMessage() {
        return languageManager.get("main_messages", "failed.login");
    }

    protected abstract void tryToLogInAccount(String[] decryptedData);
}
