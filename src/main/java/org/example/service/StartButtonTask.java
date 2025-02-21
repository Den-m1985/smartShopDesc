package org.example.service;

import org.example.enums.NameProducts;
import org.example.model.TabModel;
import org.example.service.alfa812.StartAlfa812;
import org.example.service.bolshe_podarkov.StartBolshePodarkov;
import org.example.ui.tabbed_pane.TabView;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StartButtonTask extends SwingWorker<Void, String> {
    private final LanguageManager languageManager = LanguageManager.getInstance();
    private final TabModel model;
    private final TabView view;
    private final NameProducts product;

    public StartButtonTask(TabModel model, TabView view, NameProducts product) {
        this.model = model;
        this.view = view;
        this.product = product;
    }

    @Override
    protected Void doInBackground() {
        switch (product) {
            case BOLSHE_PODARKOV -> {
                publish("\nstart: " + product.getString());
                new StartBolshePodarkov(view, product);
            }
            case ALFA_812 -> {
                publish("\nstart: " + product.getString());
                new StartAlfa812(view, product);
            }
            case COMPARE_2_FILES -> {
                publish("\nstart: " + product.getString());

            }
        }
        return null;
    }

    @Override
    protected void process(List<String> chunks) {
        for (String text : chunks) {
            model.appendText(text);
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
