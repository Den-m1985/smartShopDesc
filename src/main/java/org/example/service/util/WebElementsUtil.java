package org.example.service.util;

import org.example.service.browser.chrome.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;

public class WebElementsUtil extends BrowserManager {

    public void openPage(String url) {
        getDriver().get(url);
    }

    public void clickElement(By by) {
        if (isElementAvailable(by)) {
            WebElement webElement = getWait().until(ExpectedConditions.elementToBeClickable(by));
            webElement.click();
        }
    }

    public void clickElementNoWait(By by) {
        if (isElementAvailableNoWait(by)) {
            WebElement webElement = getWait().until(ExpectedConditions.elementToBeClickable(by));
            webElement.click();
        }
    }

    public boolean isElementAvailable(By by) {
        try {
            WebElement webElement = getWait().until(
                    ExpectedConditions.visibilityOfElementLocated(by)
            );
            return webElement.isDisplayed() && webElement.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementAvailableNoWait(By by) {
        try {
            WebElement webElement = getDriver().findElement(by);
            return webElement.isDisplayed() && webElement.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement putTextToInputField(By by, String text) {
        WebElement inputField = getWait().until(
                ExpectedConditions.elementToBeClickable(by)
        );
        inputField.clear();
        inputField.sendKeys(text);
        return inputField;
    }

    public boolean isEnterAccount(By by, String exitField) {
        WebElement element = getWait().until(
                ExpectedConditions.visibilityOfElementLocated(by));
        String str = element.getText();
        List<String> array = Arrays.stream(str.split(" ")).toList();
        return array.contains(exitField);
    }

    public List<WebElement> getElements(By by) {
        return getDriver().findElements(by);
    }

    public List<WebElement> getAllProducts(By by) {
        getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return getElements(by);
    }

    public String getText(By by) {
        if (isElementAvailable(by)) {
            WebElement webElement = getWait().until(ExpectedConditions.elementToBeClickable(by));
            return webElement.getText();
        }
        return "";
    }

}
