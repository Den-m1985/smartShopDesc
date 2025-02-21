package org.example.service;

import org.example.service.createPathFile.GetPathFile;
import org.example.ui.tabbed_pane.TabView;

import javax.swing.*;

public abstract class AbstractStartProcess extends BasicLanguageManager {
    protected final TabView view;

    public AbstractStartProcess(TabView view) {
        this.view = view;
    }

    public void startProcess() {
        try {
            long start = System.nanoTime();
            String pathFile = getPathFile();
            startController(pathFile);
            long end = System.nanoTime();
            long time = (end - start) / 1000000000;
            finish(time);
        } catch (Exception ex) {
            view.appendToTextArea("\n" + languageManager.get("main_messages", "error"));
            view.appendToTextArea("\n");
            view.appendToTextArea(ex.getMessage());
        }
    }

    protected abstract void startController(String pathFile) throws Exception;

    private String getPathFile() {
        String pathFile = new GetPathFile().getPathFile(languageManager.get("main_messages", "extension.csv"));
        if (pathFile != null) {
            view.appendToTextArea("\n" + languageManager.get("main_messages", "file.source"));
            view.appendToTextArea(pathFile);
            return pathFile;
        } else {
            view.appendToTextArea("\n" + languageManager.get("main_messages", "file.open.error"));
            throw new RuntimeException();
        }
    }

    protected void finish(long time) {
        view.appendToTextArea("\n\n");
        view.appendToTextArea(languageManager.get("main_messages", "successfully"));
        view.appendToTextArea("\n\n");
        view.appendToTextArea("Время выполнения: " + time / 60 + "мин " + time % 60 + "сек");
        view.appendToTextArea("\n\n");
        view.appendToTextArea("_________Оля молодец_________");
        view.appendToTextArea("\n\n");
        view.appendToTextArea("_________Попей чайку_________");
        view.appendToTextArea("\n\n");

        view.getStartButton().setText(languageManager.get("main_messages", "successfully"));
        JOptionPane.showMessageDialog(null, languageManager.get("main_messages", "successfully"));
    }
}
