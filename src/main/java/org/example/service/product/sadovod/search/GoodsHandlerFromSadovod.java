package org.example.service.product.sadovod.search;

import org.example.dto.DtoError;
import org.example.enums.TextLinks;
import org.example.service.csv_filter.sadovod.SadovodCSV;
import org.example.service.product.sadovod.search.check_good.GoodsCountChecker;
import org.example.service.util.WebElementsUtil;

import java.util.List;

public class GoodsHandlerFromSadovod {
    private final List<DtoError> reportList;
    private final GoodsSearcher goodsSearcher;
    private final GoodsCountChecker goodsCountChecker;
    private final ProductManager productManager;

    public GoodsHandlerFromSadovod(WebElementsUtil webElementsUtil, List<DtoError> reportList) {
        this.reportList = reportList;
        this.goodsSearcher = new GoodsSearcher(webElementsUtil);
        this.goodsCountChecker = new GoodsCountChecker(webElementsUtil);
        this.productManager = new ProductManager(webElementsUtil, this.reportList);
    }

    public void processProductsForPurchase(List<SadovodCSV> data) {
        for (SadovodCSV product : data) {
            try {
                goodsSearcher.searchByArticular(product.getArticular());
                int countProduct = goodsCountChecker.getCountCarts();

                switch (countProduct) {
                    case 0 -> reportList.add(new DtoError(product.getName(), product.getArticular(),
                            TextLinks.WRONG_ARTICULAR.getString()));
                    case 1 -> productManager.processProduct(product);
                    default -> {
                        if (countProduct >= 2) {
                            reportList.add(new DtoError(product.getName(), product.getArticular(),
                                    TextLinks.MORE_THAN_1_ITEMS.getString()));
                        }
                    }
                }
                reportList.add(new DtoError(product.getName(), product.getArticular(),
                        TextLinks.WRONG_ARTICULAR.getString()));
            } catch (Exception e) {
                reportList.add(new DtoError(product.getName(), product.getArticular(),
                        TextLinks.COMMON_ERROR.getString()));
            }
        }
    }

}
