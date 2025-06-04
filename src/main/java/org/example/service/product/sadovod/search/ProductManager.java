package org.example.service.product.sadovod.search;

import org.example.dto.DtoError;
import org.example.enums.TextLinks;
import org.example.service.browser.chrome.BrowserManager;
import org.example.service.csv_filter.sadovod.SadovodCSV;
import org.example.service.product.sadovod.search.add_to_backet.CartAdder;
import org.example.service.product.sadovod.search.check_good.CartButtonVerifier;
import org.example.service.product.sadovod.search.check_good.GetPrice;
import org.example.service.product.sadovod.search.check_good.OptionChooser;
import org.example.service.product.sadovod.search.check_good.PriceChecker;
import org.example.service.util.WebElementsUtil;

import java.util.List;

public class ProductManager {
    private final List<DtoError> errorMessages;
    private final ProductCardOpener cardOpener;
    private final PriceChecker priceChecker;
    private final CartAdder cartAdder;
    private final GetPrice getPrice;
    private final CartButtonVerifier checkAvailability;
    private final OptionChooser optionChooser;

    public ProductManager(WebElementsUtil webElementsUtil, List<DtoError> errorMessages) {
        this.errorMessages = errorMessages;
        this.cardOpener = new ProductCardOpener(webElementsUtil);
        this.priceChecker = new PriceChecker();
        this.cartAdder = new CartAdder(webElementsUtil);
        this.getPrice = new GetPrice(webElementsUtil);
        this.checkAvailability = new CartButtonVerifier(webElementsUtil);
        this.optionChooser = new OptionChooser(webElementsUtil);
    }

    public void processProduct(SadovodCSV product) {
        cardOpener.openProductDetails();

        boolean isButtonToBuyPresent = checkAvailability.isPresentButtonToCart();
        if (isButtonToBuyPresent) {
            boolean isOptionsSelect = optionChooser.selectCartOptions(product);
            if (isOptionsSelect) {
                String priceFromWeb = getPrice.getPriceFromWeb();
                boolean boolPrice = priceChecker.verifyPrice(product, priceFromWeb);
                if (boolPrice) {
                cartAdder.clickAddToCart();
                } else {
                    errorMessages.add(priceChecker.getErrorPrice(product));
                }
            } else {
                errorMessages.add(new DtoError(product.getName(), product.getArticular(), "Нет размера или цвета"));
            }

        } else {
            errorMessages.add(new DtoError(product.getName(), product.getArticular(), TextLinks.PRODUCT_OUT_OF_STOCK.getString()));
        }
    }

}
