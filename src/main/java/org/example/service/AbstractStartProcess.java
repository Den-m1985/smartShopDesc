package org.example.service;

import org.example.controller.TabController;

public abstract class AbstractStartProcess extends BasicLanguageManager {
    protected final TabController tabController;

    protected AbstractStartProcess(TabController tabController) {
        this.tabController = tabController;
    }

    public void startProcess() {
        try {
            long start = System.nanoTime();
            startController();
            long end = System.nanoTime();
            long time = (end - start) / 1000000000;
            finish(time);
        } catch (Exception ex) {
            tabController.getView().appendToTextArea("\n" + languageManager.get("main_messages", "error"));
            tabController.getView().appendToTextArea("\n");
            tabController.getView().appendToTextArea(ex.getMessage());
        }
    }

    protected abstract void startController() throws Exception;

    protected void finish(long time) {
        tabController.getView().appendToTextArea("\n\n");
        tabController.getView().appendToTextArea(languageManager.get("main_messages", "successfully"));
        tabController.getView().appendToTextArea("\n\n");
        tabController.getView().appendToTextArea("Время выполнения: " + time / 60 + "мин " + time % 60 + "сек");
        tabController.getView().appendToTextArea("\n\n");
        tabController.getView().appendToTextArea("_________Оля молодец_________");
        tabController.getView().appendToTextArea("\n\n");
        tabController.getView().appendToTextArea("_________Попей чайку_________");
        tabController.getView().appendToTextArea("\n\n");

        tabController.getView().getStartButton().setText(languageManager.get("main_messages", "successfully"));
    }
}
