package org.example.ui.tabbed_pane;

public class TabModel {
    private String text;

    public TabModel() {
        this.text = "";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void appendText(String newText) {
        this.text += newText;
    }

}
