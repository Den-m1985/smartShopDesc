package org.example.ui.tabbed_pane;

import org.example.controller.TabController;
import org.example.enums.NameProducts;
import org.example.model.TabModel;
import org.example.service.BasicLanguageManager;

import javax.swing.*;

public class CreateJTabbedPane extends BasicLanguageManager {

    public JTabbedPane createJTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();

        for (NameProducts product : NameProducts.values()) {
            TabModel model = new TabModel();
            TabView view = new TabView();
            TabController controller = new TabController(model, view, product);

            view.appendToTextArea("\n" + languageManager.get("main_messages", "start_message"));

            view.getStartButton().addActionListener(controller);

            tabbedPane.addTab(product.getString(), view);
        }
        return tabbedPane;
    }

}
