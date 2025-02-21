package org.example.ui.authorisation;

import javax.swing.*;
import java.util.Objects;

public class LoginPasswordDialog {
    public LoginPasswordDialog() {
    }

    public String[] enterLoginPassword() {

        JTextField loginField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        Object[] message = {"Login:", loginField, "Password:", passwordField};
        ImageIcon iconLogin = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/enter40.png")));

        int option = JOptionPane.showOptionDialog(null, message,
                "Login and Password", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE, iconLogin, null, null);
        if (option == JOptionPane.OK_OPTION) {
            String login = loginField.getText();
            String password = new String(passwordField.getPassword());
            return new String[]{login, password};
        } else {
            System.out.println("Окно ввода логина и пароля отменено");
        }
        return null;
    }
}
