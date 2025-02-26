package org.example.service;

import org.example.controller.TabController;
import org.example.service.alfa812.StartAlfa812;
import org.example.service.bolshe_podarkov.StartBolshePodarkov;
import org.example.service.compare_files.StartCompareFiles;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StartButtonTask extends SwingWorker<Void, String> {
    private final LanguageManager languageManager = LanguageManager.getInstance();
    //    private final AtomicReference<StartBolshePodarkov> bolshePodarkovRef = new AtomicReference<>();
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
//                bolshePodarkovRef.set(bolshePodarkov);
                bolshePodarkov.run();
            }
            case ALFA_812 -> {
                publish("\nstart: " + name);
                new StartAlfa812(tabController).run();
            }
            case COMPARE_2_FILES -> {
                publish("\nstart: " + name);
                new StartCompareFiles(tabController).run();
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
        try {
            get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // TODO оставлено для нового функционала по отмене действий.
//    public void cancelDriver() {
//        StartBolshePodarkov bolshePodarkov = bolshePodarkovRef.get();
//        if (bolshePodarkov != null) {
//            bolshePodarkov.cancelDriver();
//        } else {
//            System.out.println("bolshePodarkov is null. Cannot cancel driver.");
//        }
//    }

}
