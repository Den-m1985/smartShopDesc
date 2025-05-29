package org.example.service.csv_filter.csv;

import org.example.service.util.NumberParser;

import java.util.ArrayList;
import java.util.List;

public class OnlyGoods {
    private final NumberParser numberParser;
    private List<StructureCSV> dataWithItem;
    int cellPrice = 2;
    int cellItem = 3;

    public OnlyGoods() {
        this.numberParser = new NumberParser();
    }

    public List<StructureCSV> findOnlyGoods(List<String[]> rows) {
        dataWithItem = new ArrayList<>();
        for (String[] row : rows) {
            iteratorRow(row);
        }
        return dataWithItem;
    }

    private void iteratorRow(String[] row) {
        boolean rowCellItem = numberParser.isFigure(row[cellItem]);
        boolean rowCellPrice = numberParser.isFigure(row[cellPrice]);
        if (rowCellItem && rowCellPrice) {
            int price = numberParser.stringToInt(row[cellPrice]);
            int item = Integer.parseInt(row[cellItem]);
            dataWithItem.add(new StructureCSV(row[0], row[1], price, item));
        }
    }

}
