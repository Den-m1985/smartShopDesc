package org.example.service.product.sadovod.search;

import org.example.enums.TextLinksSadovod;
import org.example.service.browser.chrome.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoodsSearcher {
    private final BrowserManager browserManager;

    public GoodsSearcher(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    public void searchByArticular(String goodsArticular) {
        By by = By.id(TextLinksSadovod.SEARCH_FIELD.getString());
        WebElement search = browserManager.getWait().until(
                ExpectedConditions.elementToBeClickable(by)
        );
        search.click();

        By byPopup = By.className(TextLinksSadovod.SEARCH_FIELD_POPUP.getString());
        WebElement searchPopUp = browserManager.getWait().until(
                ExpectedConditions.elementToBeClickable(byPopup)
        );
        searchPopUp.click();
        searchPopUp.clear();
        searchPopUp.sendKeys(goodsArticular);
        searchPopUp.sendKeys(Keys.ENTER);

        // Ждём, пока пропадёт модальное окно (если оно есть)
        browserManager.getWait().until(
                ExpectedConditions.invisibilityOf(searchPopUp)
        );

        browserManager.getWait().until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );

        // Ожидаем, пока загрузится либо блок с товарами, либо сообщение "Ничего не найдено"
        long timeout = browserManager.getDuration().getSeconds();
        By noItemsLocator = By.cssSelector(".ty-no-items.cm-pagination-container");
        By productCardsLocator = By.cssSelector(".ty-column4[data-ut2-load-more='first-item']");
        browserManager.getWait().withMessage("Результаты поиска не загрузились за " + timeout + " секунд")
                .until(driver -> !driver.findElements(noItemsLocator).isEmpty() ||
                        !driver.findElements(productCardsLocator).isEmpty());
    }

}
