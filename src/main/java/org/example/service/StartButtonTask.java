package org.example.service;

import org.example.controller.TabController;
import org.example.service.product.alfa812.StartAlfa812;
import org.example.service.product.bolshe_podarkov.StartBolshePodarkov;
import org.example.service.product.compare_files.StartCompareFiles;
import org.example.service.product.sadovod.StartSadovod;
import org.example.ui.tabbed_pane.ButtonStart;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

public class StartButtonTask extends SwingWorker<Void, String> {
    private final LanguageManager languageManager = LanguageManager.getInstance();
    private final AtomicReference<BaseProductTask> currentTaskRef = new AtomicReference<>();
    private final TabController tabController;

    public StartButtonTask(TabController tabController) {
        this.tabController = tabController;
    }

    @Override
    protected Void doInBackground() {
        String name = tabController.getProduct().getString();
        switch (tabController.getProduct()) {
            case BOLSHE_PODARKOV -> {
                publish("\nstart: " + name);
                StartBolshePodarkov bolshePodarkov = new StartBolshePodarkov(tabController);
                currentTaskRef.set(bolshePodarkov);
                bolshePodarkov.run();
            }
            case ALFA_812 -> {
                publish("\nstart: " + name);
                StartAlfa812 startAlfa812 = new StartAlfa812(tabController);
                currentTaskRef.set(startAlfa812);
                startAlfa812.run();
            }
            case COMPARE_2_FILES -> {
                publish("\nstart: " + name);
                new StartCompareFiles(tabController).run();
            }
            case SADOVOD -> {
                publish("\nstart: " + name);
                StartSadovod sadovod = new StartSadovod(tabController);
                currentTaskRef.set(sadovod);
                sadovod.run();
            }
        }
        return null;
    }

    @Override
    protected void process(List<String> chunks) {
        for (String text : chunks) {
            tabController.getView().appendToTextArea(text);
        }
    }

    @Override
    protected void done() {
        tabController.getView().getStartButton().setText(languageManager.get("main_messages", "button.start"));
        new ButtonStart().setLoadingState(tabController.getView().getStartButton(), false);
        try {
            get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void cancelDriver() {
        BaseProductTask currentTask = currentTaskRef.get();
        if (currentTask != null) {
            currentTask.cancelDriver();
        } else {
            publish("\nNo active task to cancel");
        }
    }

}
