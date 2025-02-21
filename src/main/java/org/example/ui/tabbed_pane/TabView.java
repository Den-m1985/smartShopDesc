package org.example.ui.tabbed_pane;

import javax.swing.*;
import java.awt.*;

public class TabView extends JPanel {
    private final JTextArea textArea;
    private final JButton startButton;

    public TabView() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setBackground(new Color(220, 220, 220));
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setMargin(new Insets(10, 10, 10, 10));
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        startButton = new ButtonStart().buttonStart();
        add(startButton, BorderLayout.NORTH);
    }

    public void updateTextArea(String text) {
        textArea.setText(text);
    }

    public void appendToTextArea(String newText) {
        textArea.append(newText);
    }

    public JButton getStartButton() {
        return startButton;
    }

}
