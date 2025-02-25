package org.example.service.csv_filter.csv;

import java.util.ArrayList;
import java.util.List;

public class SeparateGoods {
    
    public List<String[]> separateArray(List<String[]> rows, int lengthLine){
        List<String[]> newArray = new ArrayList<>();
        for (String[] row : rows) {
            if (row.length == lengthLine) {
                newArray.add(row);
            }
        }
        return newArray;
    }
    
}
