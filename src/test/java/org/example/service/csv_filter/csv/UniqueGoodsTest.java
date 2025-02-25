package org.example.service.csv_filter.csv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UniqueGoodsTest {

    @Test
    public void uniqueGoodsTest() {
        List<StructureCSV> dataWithItem = new ArrayList<>();
        dataWithItem.add(new StructureCSV("Веточка \"Ель\" с ягодками в мешочке 17 см (SF-3539)", "677-236", 123, 20));
        dataWithItem.add(new StructureCSV("Веточка \"Ель\" с ягодками в мешочке 17 см (SF-3539)", "677-236", 123, 20));
        dataWithItem.add(new StructureCSV("Веточка \"Ель\" с ягодками в мешочке 17 см (SF-3539)", "677-236", 123, 20));
        dataWithItem.add(new StructureCSV("ex1", "677-236", 123, 20));
        dataWithItem.add(new StructureCSV("ex2", "677-236", 123, 20));
        dataWithItem.add(new StructureCSV("ex", "6", 123, 20));
        dataWithItem.add(new StructureCSV("ex", "6", 123, 20));

        UniqueGoods uniqueGoods = new UniqueGoods();
        List<StructureCSV> uniqueValues = uniqueGoods.findUniqueGoods(dataWithItem);
        List<StructureCSV> duplicateNames = uniqueGoods.getDuplicateNames();

        int result = uniqueValues.size();

        assertEquals(2, result);
    }

    @Test
    public void dublicatGoodsTest() {
        List<StructureCSV> dataWithItem = new ArrayList<>();
        dataWithItem.add(new StructureCSV("Веточка \"Ель\" с ягодками в мешочке 17 см (SF-3539)", "677-236", 123, 20));
        dataWithItem.add(new StructureCSV("Веточка \"Ель\" с ягодками в мешочке 17 см (SF-3539)", "677-236", 123, 20));
        dataWithItem.add(new StructureCSV("Веточка \"Ель\" с ягодками в мешочке 17 см (SF-3539)", "677-236", 123, 20));
        dataWithItem.add(new StructureCSV("ex1", "677-236", 123, 20));
        dataWithItem.add(new StructureCSV("ex2", "677-236", 123, 20));
        dataWithItem.add(new StructureCSV("ex", "6", 123, 20));
        dataWithItem.add(new StructureCSV("ex", "6", 123, 20));

        UniqueGoods uniqueGoods = new UniqueGoods();
        List<StructureCSV> uniqueValues = uniqueGoods.findUniqueGoods(dataWithItem);
        List<StructureCSV> duplicateNames = uniqueGoods.getDuplicateNames();

        int result = duplicateNames.size();

        assertEquals(5, result);
    }

}
