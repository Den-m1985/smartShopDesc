package org.example.service.product.sadovod.search;

import org.example.enums.TextLinksSadovod;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoodsSearcher {
    private final WebElementsUtil webElementsUtil;

    public GoodsSearcher(WebElementsUtil webElementsUtil) {
        this.webElementsUtil = webElementsUtil;
    }

    public void searchByArticular(String goodsArticular) {
        By firstSearchLocator = By.id(TextLinksSadovod.SEARCH_FIELD.getString());
        webElementsUtil.clickElement(firstSearchLocator);

        By byPopupLocator = By.className(TextLinksSadovod.SEARCH_FIELD_POPUP.getString());
        WebElement elementPopUp = webElementsUtil.putTextToInputField(byPopupLocator, goodsArticular);
        elementPopUp.sendKeys(Keys.ENTER);

        // Ждём, пока пропадёт модальное окно (если оно есть)
        webElementsUtil.getWait().until(
                ExpectedConditions.invisibilityOf(elementPopUp)
        );

        webElementsUtil.readyStateDocument();

        // Ожидаем, пока загрузится либо блок с товарами, либо сообщение "Ничего не найдено"
        long timeout = webElementsUtil.getDuration().getSeconds();
        By noItemsLocator = By.cssSelector(".ty-no-items.cm-pagination-container");
        By productCardsLocator = By.cssSelector(".ty-column4[data-ut2-load-more='first-item']");
        webElementsUtil.getWait().withMessage("Результаты поиска не загрузились за " + timeout + " секунд")
                .until(driver -> !driver.findElements(noItemsLocator).isEmpty() ||
                        !driver.findElements(productCardsLocator).isEmpty());
    }

}
