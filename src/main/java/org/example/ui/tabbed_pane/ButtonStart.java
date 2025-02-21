package org.example.ui.tabbed_pane;

import org.example.service.BasicLanguageManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ButtonStart extends BasicLanguageManager {

    public JButton buttonStart() {
        JButton startButton = new JButton(languageManager.get("main_messages", "button.start"));
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        startButton.setFont(buttonFont);
        startButton.setForeground(Color.BLUE);
        startButton.setFocusPainted(false);  // убираем рамку вокруг кнопки
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/play64.png")));
        startButton.setIcon(icon);
        return startButton;
    }

}
