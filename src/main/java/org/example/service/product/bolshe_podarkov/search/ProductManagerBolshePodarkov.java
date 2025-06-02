package org.example.service.product.bolshe_podarkov.search;

import org.example.dto.DtoError;
import org.example.service.BasicLanguageManager;
import org.example.service.csv_filter.csv.StructureCSV;
import org.example.service.product.bolshe_podarkov.add_to_basket.GoodsAdder;
import org.example.service.product.bolshe_podarkov.check_good.CheckAvailability;
import org.example.service.product.bolshe_podarkov.check_good.PriceChecker;
import org.example.service.product.bolshe_podarkov.check_good.ProductPrice;
import org.example.service.util.WebElementsUtil;

import java.util.List;

public class ProductManagerBolshePodarkov extends BasicLanguageManager {
    private final List<DtoError> errorMessages;
    private final CheckAvailability checkAvailability;
    private final ProductPrice productPrice;
    private final PriceChecker priceChecker;
    private final GoodsAdder goodsAdder;

    public ProductManagerBolshePodarkov(WebElementsUtil webElementsUtil, List<DtoError> errorMessages) {
        this.errorMessages = errorMessages;
        this.checkAvailability = new CheckAvailability(webElementsUtil);
        this.productPrice = new ProductPrice(webElementsUtil);
        this.priceChecker = new PriceChecker();
        this.goodsAdder = new GoodsAdder(webElementsUtil);
    }

    public void processProduct(StructureCSV product) {
        int csvPrice = product.getPrice();
        boolean isButtonToBuyPresent = checkAvailability.isPresentButtonToCart();

        if (isButtonToBuyPresent) {
            String priceFromWeb = productPrice.getPriceFromWeb();
            boolean boolPrice = priceChecker.checkPrice(csvPrice, priceFromWeb);
            if (boolPrice) {
                // If all is well, then add the product to the cart
                goodsAdder.addToCart(product);
            } else {
                errorMessages.add(priceChecker.getErrorPrice(product));
            }
        } else {
            errorMessages.add(new DtoError(product.getName(), product.getArticular(), languageManager.get("main_messages", "product.out.stock")));
        }
    }
}
