package org.example.service.bolshe_podarkov.searchAndAdd;

import org.example.DTO.DtoError;
import org.example.service.BasicLanguageManager;
import org.example.service.bolshe_podarkov.searchAndAdd.addToBasket.AddGood;
import org.example.service.bolshe_podarkov.searchAndAdd.checkGood.CheckAvailability;
import org.example.service.bolshe_podarkov.searchAndAdd.checkGood.CheckPrice;
import org.example.service.bolshe_podarkov.searchAndAdd.checkGood.CountProduct;
import org.example.service.bolshe_podarkov.searchAndAdd.checkGood.GetPrice;
import org.example.service.bolshe_podarkov.searchAndAdd.search.SearchGoods;
import org.example.service.browser.chrome.BrowserManager;
import org.example.service.csv_filter.csv.StructureCSV;

import java.util.ArrayList;
import java.util.List;

public class ServiceAddToBasket extends BasicLanguageManager {
    private final List<DtoError> errorMessages = new ArrayList<>();
    private final  BrowserManager browserManager;

    public ServiceAddToBasket(BrowserManager browserManager, List<StructureCSV> data) {
        this.browserManager = browserManager;

        for (StructureCSV product : data) {
            try {
                new SearchGoods(browserManager, product.getArticular());
                int countProduct = new CountProduct(browserManager).countProduct();
                switch (countProduct) {
                    case 0 ->
                            errorMessages.add(new DtoError(product.getName(), product.getArticular(),
                                    "Неправильный артикул или имя"));
                    case 1 -> executeToBuy(product);
                    default -> {
                        if (countProduct > 2) {
                            errorMessages.add(new DtoError(product.getName(), product.getArticular(),
                                    "Товаров больше чем 1"));
                        }
                    }
                }
            } catch (Exception e) {
                errorMessages.add(new DtoError(product.getName(), product.getArticular(),
                        "Общая ошибка"));
            }
        }
    }


    private void executeToBuy(StructureCSV product) {
        int csvPrice = product.getPrice();
        boolean isButtonToBuyPresent = new CheckAvailability(browserManager).isPresentButtonToCart();

        if (isButtonToBuyPresent) {
            String priceFromWeb = new GetPrice(browserManager).getPriceFromWeb();
            CheckPrice check = new CheckPrice();
            boolean boolPrice = check.checkPrice(csvPrice, priceFromWeb);
            if (boolPrice) {
                // If all is well, then add the product to the cart
                new AddGood(browserManager, product);
            } else {
                errorMessages.add(check.getErrorPrice(product));
            }
        } else {
            errorMessages.add(new DtoError(product.getName(), product.getArticular(), languageManager.get("main_messages", "product.out.stock")));
        }
    }


    public List<DtoError> getErrorSearch() {
        return errorMessages;
    }
}
