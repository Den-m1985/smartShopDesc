package org.example.ui;

import org.example.service.LanguageManager;
import org.example.ui.menu.JMenuWindows;
import org.example.ui.tabbed_pane.CreateJTabbedPane;

import javax.swing.*;

public class MainWindow extends JFrame {
    LanguageManager languageManager = LanguageManager.getInstance();

    public MainWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setTitle(languageManager.get("main_messages","title"));

        centerScreen();

        setJMenuBar(new JMenuWindows().createMenu());  // create menu

        add(new CreateJTabbedPane().createJTabbedPane());

        setVisible(true);
    }
    

    private void centerScreen() {
        // get screen size width and height
        int screenWidth = getToolkit().getScreenSize().width;
        int screenHeight = getToolkit().getScreenSize().height;
        // find coordinate window to set
        int x = (int) ((screenWidth - getWidth()) / 1.1);
        int y = (screenHeight - getHeight()) / 2;
        setLocation(x, y);
    }
}
