package org.example.service.csv_filtr.csv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeparateGoodsTest {

    @Test
    public void separateGoodsTest(){
        String[] array1 = {"", "", ""};
        String[] array2 = {"", "", "", ""};
        List<String[]> rows = new ArrayList<>();
        rows.add(array1);
        rows.add(array2);

        List<String[]> newRows = new SeparateGoods().separateArray(rows, 4);

        int result = newRows.size();

        assertEquals(1, result);
    }

}
