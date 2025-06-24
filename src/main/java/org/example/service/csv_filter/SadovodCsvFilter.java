package org.example.service.csv_filter;

import org.example.service.csv_filter.sadovod.SadovodCSV;
import org.example.service.csv_filter.sadovod.SadovodGoodsDuplicate;
import org.example.service.csv_filter.sadovod.SadovodGoodsOnly;
import org.example.service.csv_filter.sadovod.SadovodGoodsUniq;

import java.util.List;

public class SadovodCsvFilter extends AbstractCsvFilter {

    @Override
    public List<SadovodCSV> csvFilter(String fileName, int lengthRow) {
        List<String[]> rows = getRawRows(fileName, lengthRow);

        List<SadovodCSV> dataWithItem = new SadovodGoodsOnly().findOnlyGoods(rows, 3, 4);

        SadovodGoodsUniq uniqueGoods = new SadovodGoodsUniq();
        List<SadovodCSV> uniqueValues = uniqueGoods.findUniqueGoods(dataWithItem);
        List<SadovodCSV> duplicateNames = uniqueGoods.getDuplicateNames();

        List<SadovodCSV> resolveDuplicatedNames = new SadovodGoodsDuplicate().findDuplicateGoods(duplicateNames);
        uniqueValues.addAll(resolveDuplicatedNames);

        return uniqueValues;
    }

}
