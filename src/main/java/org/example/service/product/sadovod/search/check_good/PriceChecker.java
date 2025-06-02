package org.example.service.product.sadovod.search.check_good;

import org.example.dto.DtoError;
import org.example.service.csv_filter.csv.StructureCSV;

public class PriceChecker {
    int percent;
    String webPrice;

    public boolean verifyPrice(StructureCSV product, String webPrice) {
        this.webPrice = webPrice;
        int intWebPrice = stringToInt(webPrice);
        percent = (intWebPrice * 100) / product.getPrice();
        return percent < 101;
    }

    private int stringToInt(String str) {
        if (str.contains(",")) {
            String str2 = str.replace(",", ".");
            return (int) Float.parseFloat(str2);
        }
        return (int) Float.parseFloat(str);
    }

    public DtoError getErrorPrice(StructureCSV product) {
        return new DtoError(product.getName(), product.getArticular(), "цена на сайте " + webPrice + " больше на " + percent + "%");
    }
}
