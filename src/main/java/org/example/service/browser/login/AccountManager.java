package org.example.service.browser.login;

import org.example.enums.NameProducts;
import org.example.service.login_storage.LoginStorage;
import org.example.service.product.alfa812.login.Alfa812Account;
import org.example.service.product.bolshe_podarkov.login.BolshePodarkovAccount;
import org.example.service.product.sadovod.login.SadovodAccount;
import org.example.service.util.WebElementsUtil;

public class AccountManager {
    private final LoginStorage loginStorage;
    private final Alfa812Account alfa812Account;
    private final BolshePodarkovAccount bolshePodarkovAccount;
    private final NameProducts product;
    private final SadovodAccount sadovodAccount;

    public AccountManager(WebElementsUtil webElementsUtil, NameProducts product) {
        this.product = product;
        this.loginStorage = new LoginStorage(product);
        this.alfa812Account = new Alfa812Account(webElementsUtil);
        this.bolshePodarkovAccount = new BolshePodarkovAccount(webElementsUtil);
        this.sadovodAccount = new SadovodAccount(webElementsUtil);
    }

    public String[] getCredentials() throws Exception {
        String[] credentials = loginStorage.readFromFile();
        return (credentials == null || credentials.length == 0) ? null : credentials;
    }

    public boolean isLoginSuccessful(String[] credentials) {
        if (credentials == null) return false;
        return switch (product) {
            case BOLSHE_PODARKOV -> bolshePodarkovAccount.isEnterAccount(credentials[0]);
            case ALFA_812 -> alfa812Account.isEnterAccount();
            case SADOVOD -> sadovodAccount.isEnterAccount(credentials[0]);
            default -> false;
        };
    }

}
