package org.example.controller;

import org.example.enums.FileExtension;
import org.example.enums.NameProducts;
import org.example.model.FilePathManager;
import org.example.service.BasicLanguageManager;
import org.example.service.StartButtonTask;
import org.example.ui.tabbed_pane.ButtonStart;
import org.example.ui.tabbed_pane.TabView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TabController extends BasicLanguageManager implements ActionListener {
    private final TabView view;
    private final NameProducts product;
    private final ArrayList<FileExtension[]> extension;
    private final FilePathManager filePathManager;
    private StartButtonTask startButtonTask;

    public TabController(TabView view, NameProducts product) {
        this.view = view;
        this.product = product;
        extension = new ArrayList<>();
        filePathManager = new FilePathManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        ButtonStart buttonStart = new ButtonStart();
        if (startButtonTask == null || startButtonTask.isDone()) {
            buttonStart.setLoadingState(button, true); // Включаем состояние загрузки
            startButtonTask = new StartButtonTask(this);
            startButtonTask.execute();
        } else {
//            startButtonTask.cancelDriver();
            buttonStart.setLoadingState(button, false);
            startButtonTask = null;
            view.appendToTextArea("\n\n");
            view.appendToTextArea("Отмена процесса");
        }
    }

    public TabView getView() {
        return view;
    }

    public NameProducts getProduct() {
        return product;
    }

    public ArrayList<FileExtension[]> getExtension() {
        return extension;
    }

    public FilePathManager getFilePathManager() {
        return filePathManager;
    }

}
