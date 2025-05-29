package org.example.service.sadovod.login;

import org.example.enums.TextLinksSadovod;
import org.example.service.browser.chrome.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class SadovodLogin {
    private final BrowserManager browserManager;

    public SadovodLogin(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    public void tryToLogInAccount() {
        clickButtonEmail();

        WebElement emailField = browserManager.getDriver().findElement(By.id(TextLinksSadovod.LOGIN_FIELD.getString()));
        emailField.sendKeys(TextLinksSadovod.EMAIL.getString());

        Duration timeoutDuration = Duration.ofMinutes(5);
        Duration pollIntervalMillis = Duration.ofMillis(500);
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < timeoutDuration.toMillis()) {

            By by = By.cssSelector(TextLinksSadovod.LOGIN_BUTTON_BY_EMAIL.getString());
            List<WebElement> elements = browserManager.getDriver().findElements(by);
            if (!elements.isEmpty()) {
                return;
            }

            try {
                Thread.sleep(pollIntervalMillis.toMillis());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("Авторизация не прошла в течение " + timeoutDuration.toSeconds() + " с");
    }

    private void clickButtonEmail() {
        List<WebElement> tabButtons = browserManager.getDriver().findElements(By.cssSelector("ul.ty-tabs__list a.ty-tabs__a"));
        for (WebElement button : tabButtons) {
            if (button.getText().trim().equalsIgnoreCase("По почте")) {
                button.click();
                return;
            }
        }
    }

}
