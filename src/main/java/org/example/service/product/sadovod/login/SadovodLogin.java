package org.example.service.product.sadovod.login;

import org.example.controller.TabController;
import org.example.enums.TextLinksSadovod;
import org.example.service.browser.login.AbstractLoginPage;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class SadovodLogin extends AbstractLoginPage {

    public SadovodLogin(WebElementsUtil webElementsUtil, TabController tabController) throws Exception {
        super(webElementsUtil, tabController);
        this.webElementsUtil = webElementsUtil;
    }

    @Override
    protected void tryToLogInAccount(String[] decryptedData) {
        By emailLocator = By.cssSelector(TextLinksSadovod.EMAIL_LOCATOR.getString());
        clickButtonEmail(emailLocator);

        By loginLocator = By.id(TextLinksSadovod.LOGIN_FIELD.getString());
        webElementsUtil.putTextToInputField(loginLocator, decryptedData[0]);

        Duration timeoutDuration = Duration.ofMinutes(5);
        if (!waitToAuth(timeoutDuration)) {
            throw new RuntimeException("Авторизация не прошла в течении " + timeoutDuration.toSeconds() + " с");
        }
    }

    private void clickButtonEmail(By by) {
        List<WebElement> tabButtons = webElementsUtil.getDriver().findElements(by);
        for (WebElement button : tabButtons) {
            if (button.getText().trim().equalsIgnoreCase(TextLinksSadovod.BY_EMAIL.getString())) {
                button.click();
                return;
            }
        }
    }

    private boolean waitToAuth(Duration timeoutDuration) {
        Duration pollIntervalMillis = Duration.ofMillis(500);
        By by = By.cssSelector(TextLinksSadovod.LOGIN_BUTTON_BY_EMAIL.getString());
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < timeoutDuration.toMillis()) {
            List<WebElement> elements = webElementsUtil.getDriver().findElements(by);
            if (!elements.isEmpty()) {
                return true;
            }
            try {
                Thread.sleep(pollIntervalMillis.toMillis());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
