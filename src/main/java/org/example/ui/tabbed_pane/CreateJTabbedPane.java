package org.example.ui.tabbed_pane;

import org.example.controller.TabController;
import org.example.enums.NameProducts;
import org.example.service.BasicLanguageManager;

import javax.swing.*;

public class CreateJTabbedPane extends BasicLanguageManager {

    public JTabbedPane createJTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();

        for (NameProducts product : NameProducts.values()) {
            TabView view = new TabView();
            TabController controller = new TabController(view, product);

            view.appendToTextArea("\n" + languageManager.get("main_messages", "start_message"));

            view.getStartButton().addActionListener(controller);

            tabbedPane.addTab(product.getString(), view);
        }
        return tabbedPane;
    }

}
