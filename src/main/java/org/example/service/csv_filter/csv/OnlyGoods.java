package org.example.service.csv_filter.csv;

import java.util.ArrayList;
import java.util.List;

public class OnlyGoods {
    private List<StructureCSV> dataWithItem;
    int cellPrice = 2;
    int cellItem = 3;


    public List<StructureCSV> findOnlyGoods(List<String[]> rows) {
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
            dataWithItem.add(new StructureCSV(row[0], row[1], price, item));
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
