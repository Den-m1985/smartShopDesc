package org.example.ui.menu;


import org.example.service.BasicLanguageManager;

import javax.swing.*;
import java.util.Objects;

public class JMenuWindows extends BasicLanguageManager {

    public JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(extraMenu());
        return menuBar;
    }

    private JMenu extraMenu() {
        // Создание выпадающего меню
        JMenu file = new JMenu(languageManager.get("main_messages", "additionally"));

        JMenuItem save = new JMenuItem();
        ImageIcon iconSave = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/save40.png")));
        save.setIcon(iconSave);

        JMenuItem info = new JMenuItem();
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/info40.png")));
        info.setIcon(icon);

        JMenuItem login = new JMenuItem();
        ImageIcon iconLogin = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/enter40.png")));
        login.setIcon(iconLogin);

//        file.add(save);
//        file.addSeparator();
        file.add(info);
//        file.addSeparator();
//        file.add(login);

//        save.addActionListener(arg0 -> new WriteTxtReport(data.getText()));
//        info.addActionListener(arg0 -> new Info());
        login.addActionListener(arg0 -> {
            try {
//                new Authorization();
                System.out.println("Авторизация прошла успешно");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        });
        return file;
    }
}
