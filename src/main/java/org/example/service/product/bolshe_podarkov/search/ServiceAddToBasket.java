package org.example.service.product.bolshe_podarkov.search;

import org.example.dto.DtoError;
import org.example.enums.TextLinks;
import org.example.service.BasicLanguageManager;
import org.example.service.csv_filter.csv.StructureCSV;
import org.example.service.product.bolshe_podarkov.check_good.ProductCounter;
import org.example.service.util.WebElementsUtil;

import java.util.List;

public class ServiceAddToBasket extends BasicLanguageManager {
    private final List<DtoError> reportList;
    private final ProductManagerBolshePodarkov productManager;
    private final GoodsSearcher searchGoods;
    private final ProductCounter productCounter;

    public ServiceAddToBasket(WebElementsUtil webElementsUtil, List<DtoError> reportList) {
        this.reportList = reportList;
        this.searchGoods = new GoodsSearcher(webElementsUtil);
        this.productManager = new ProductManagerBolshePodarkov(webElementsUtil, reportList);
        this.productCounter = new ProductCounter(webElementsUtil);
    }

    public void processProductsForPurchase(List<StructureCSV> data) {
        for (StructureCSV product : data) {
            try {
                searchGoods.searchByArticular(product.getArticular());
                int countProduct = productCounter.countProduct();
                switch (countProduct) {
                    case 0 -> reportList.add(new DtoError(product.getName(), product.getArticular(),
                            TextLinks.WRONG_ARTICULAR.getString()));
                    case 1 -> productManager.processProduct(product);
                    default -> {
                        if (countProduct >= 2) {
                            reportList.add(new DtoError(product.getName(), product.getArticular(),
                                    "Товаров больше чем 1"));
                        }
                    }
                }
            } catch (Exception e) {
                reportList.add(new DtoError(product.getName(), product.getArticular(),
                        e.getMessage()));

            }
        }
    }

}
