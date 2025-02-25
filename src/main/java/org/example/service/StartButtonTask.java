package org.example.service;

import org.example.controller.TabController;
import org.example.enums.NameProducts;
import org.example.service.alfa812.StartAlfa812;
import org.example.service.bolshe_podarkov.StartBolshePodarkov;
import org.example.service.compare_files.StartCompareFiles;
import org.example.ui.tabbed_pane.TabView;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StartButtonTask extends SwingWorker<Void, String> {
    private final LanguageManager languageManager = LanguageManager.getInstance();
    private final TabView view;
    private final NameProducts product;
    TabController tabController;

    public StartButtonTask(TabView view, NameProducts product, TabController tabController) {
        this.view = view;
        this.product = product;
        this.tabController = tabController;
    }

    @Override
    protected Void doInBackground() {
        String name = tabController.getProduct().getString();
        switch (product) {
            case BOLSHE_PODARKOV -> {
                publish("\nstart: " + name);
                new StartBolshePodarkov(tabController);
            }
            case ALFA_812 -> {
                publish("\nstart: " + name);
                new StartAlfa812(tabController);
            }
            case COMPARE_2_FILES -> {
                publish("\nstart: " + name);
                new StartCompareFiles(tabController);
            }
        }
        return null;
    }

    @Override
    protected void process(List<String> chunks) {
        for (String text : chunks) {
            view.appendToTextArea(text);
        }
    }

    @Override
    protected void done() {
        view.getStartButton().setText(languageManager.get("main_messages", "button.start"));
        try {
            get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
