package org.example.ui.authorisation;

import org.example.enums.NameProducts;
import org.example.service.login_storage.LoginStorage;

public class AuthorizationWindow {

    public void showWindow(NameProducts product) throws Exception {
        String[] authorizationData = new LoginPasswordDialog().enterLoginPassword();

        String login = authorizationData[0];
        String password = authorizationData[1];

        new LoginStorage(product).saveToFile(login, password);
    }
}
