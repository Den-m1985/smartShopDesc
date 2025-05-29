package org.example.service.sadovod.search.check_good;

import org.example.enums.TextLinksSadovod;
import org.example.service.browser.chrome.BrowserManager;
import org.example.service.csv_filter.sadovod.SadovodCSV;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OptionChooser {
    private final BrowserManager browserManager;
    private final OptionParser sizeSeparator;
    private final String colorOption;
    private final String sizeOption;

    public OptionChooser(BrowserManager browserManager) {
        this.browserManager = browserManager;
        this.sizeSeparator = new OptionParser();
        this.colorOption = TextLinksSadovod.COLOR_OPTION.getString();
        this.sizeOption = TextLinksSadovod.SIZE_OPTION.getString();
    }

    public boolean selectCartOptions(SadovodCSV product) {
        try {
            select(product);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    private void select(SadovodCSV product) {
        if (sizeSeparator.removeParentheses(product.getSize()).equals("*")) {
            return;
        }

        Map<String, String> productSize = sizeSeparator.parseOptionToMap(product.getSize());

        boolean hasColorChoice = isOptionPresent(colorOption);
        boolean hasSizeChoice = isOptionPresent(sizeOption);

        if (!hasColorChoice && !hasSizeChoice) {
            // 1. Нет ничего
        } else if (hasColorChoice && !hasSizeChoice) {
            // 2. Есть выбор цвета
            handleColorSelection(productSize);
        } else if (!hasColorChoice && hasSizeChoice) {
            // 3. Есть выбор размера
            handleSizeSelection(productSize);
        } else {
            // 4. Есть выбор цвета и выбор размера
            handleColorSelection(productSize);
            handleSizeSelection(productSize);
        }
    }

    private boolean isOptionPresent(String optionName) {
        try {
            By by = By.xpath(String.format("//label[contains(text(), '%s')]", optionName));
            browserManager.getDriver().findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private void handleColorSelection(Map<String, String> productMap) {
        List<String> colors = getOptionsFromSelect(colorOption);
        String productColor = productMap.get(colorOption).toLowerCase();
        if (colors.contains(productColor)) {
            selectOption(colorOption, productMap.get(colorOption));
        }
    }

    private void handleSizeSelection(Map<String, String> productMap) {
        List<String> sizes = getOptionsFromSelect(sizeOption);
        String productSize = productMap.get(sizeOption).toLowerCase();
        if (sizes.contains(productSize)) {
            selectOption(sizeOption, productMap.get(sizeOption));
        }
    }


    private List<String> getOptionsFromSelect(String fieldName) {
        By by = By.xpath(
                String.format("//label[contains(text(), '%s')]/following-sibling::select", fieldName)
        );
        WebElement selectElement = browserManager
                .getWait()
                .until(ExpectedConditions.presenceOfElementLocated(by));
        Select select = new Select(selectElement);
        List<String> options = new ArrayList<>();
        select.getOptions().forEach(option ->
                options.add(option.getText().trim())
        );
        return options;
    }

    private void selectOption(String fieldName, String optionValue) {
        By selectLocator = By.xpath(
                String.format("//label[contains(text(), '%s')]/following-sibling::select", fieldName)
        );
        WebElement selectElement = browserManager.getWait().until(
                ExpectedConditions.elementToBeClickable(selectLocator)
        );
        Select select = new Select(selectElement);
        select.selectByVisibleText(optionValue);
    }

}
