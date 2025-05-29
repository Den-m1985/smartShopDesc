package org.example.service.csv_filter.sadovod;

import java.util.ArrayList;
import java.util.List;

public class SadovodGoodsOnly {
    private List<SadovodCSV> dataWithItem;
    int cellPrice;
    int cellItem;


    public List<SadovodCSV> findOnlyGoods(List<String[]> rows,  int cellPrice, int cellItem) {
        this.cellPrice = cellPrice;
        this.cellItem = cellItem;
        dataWithItem = new ArrayList<>();
        for (String[] row : rows) {
            iteratorRow(row);
        }
        return dataWithItem;
    }

    private void iteratorRow(String[] row) {
        boolean rowCellItem = isFigure(row[cellItem]);
        boolean rowCellPrice = isFigure(row[cellPrice]);
        if (rowCellItem && rowCellPrice) {
            int price = convertFloatToInt(row[cellPrice]);
            int item = Integer.parseInt(row[cellItem]);
            dataWithItem.add(new SadovodCSV(row[0], row[2], price, item, row[1], row[3]));
        }
    }


    private boolean isFigure(String str) {
        if (str == null) {
            return false;
        }
        try {
            convertFloatToInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    private int convertFloatToInt(String str) {
        if (str.contains(",")) {
            String str2 = str.replace(",", ".");
            return (int) Float.parseFloat(str2);
        }
        return (int) Float.parseFloat(str);
    }
}
