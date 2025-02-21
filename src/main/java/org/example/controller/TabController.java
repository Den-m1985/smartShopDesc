package org.example.controller;

import org.example.enums.NameProducts;
import org.example.model.TabModel;
import org.example.service.BasicLanguageManager;
import org.example.service.StartButtonTask;
import org.example.ui.tabbed_pane.TabView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabController extends BasicLanguageManager implements ActionListener {
    private final TabModel model;
    private final TabView view;
    private final NameProducts product;

    public TabController(TabModel model, TabView view, NameProducts product) {
        this.model = model;
        this.view = view;
        this.product = product;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        button.setText(languageManager.get("main_messages", "button.in.work"));
        new StartButtonTask(model, view, product).execute();
    }

}
