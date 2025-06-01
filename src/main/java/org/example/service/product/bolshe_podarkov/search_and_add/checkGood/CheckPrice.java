package org.example.service.product.bolshe_podarkov.search_and_add.checkGood;

import org.example.DTO.DtoError;
import org.example.service.csv_filter.csv.StructureCSV;

public class CheckPrice {
    int percent;
    String webPrice;

    public boolean checkPrice(int csvPrice, String webPrice) {
        this.webPrice = webPrice;
        int intWebPrice = floatToInt(webPrice);
        percent = (intWebPrice * 100) / csvPrice;
        return percent < 101;
    }

    public DtoError getErrorPrice(StructureCSV product) {
        return new DtoError(product.getName(), product.getArticular(), "цена на сайте " + webPrice + " больше на " + percent + "%");
    }

    private int floatToInt(String str) {
        if (str.contains(",")) {
            String str2 = str.replace(",", ".");
            return (int) Float.parseFloat(str2);
        }
        return (int) Float.parseFloat(str);
    }
}
