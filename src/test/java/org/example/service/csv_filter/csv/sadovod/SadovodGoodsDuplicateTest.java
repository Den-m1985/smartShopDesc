package org.example.service.csv_filter.csv.sadovod;

import org.example.service.csv_filter.sadovod.SadovodCSV;
import org.example.service.csv_filter.sadovod.SadovodGoodsDuplicate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SadovodGoodsDuplicateTest {

    @Test
    void duplicateGoodsTest() {
        List<SadovodCSV> inputData = List.of(
                new SadovodCSV("FORZA Кольцо-подставка для смартфона, 6,5x3x0,1см, фэйс", "(12-470-011)", 17, 1, "Обувь", "48"),
                new SadovodCSV("FORZA Кольцо-подставка для смартфона, 6,5x3x0,1см, фэйс", "(12-470-011)", 17, 1, "Обувь", "48"),
                new SadovodCSV("FORZA Кольцо-подставка для смартфона, 6,5x3x0,1см, фэйс", "(12-470-011)", 17, 1, "Обувь", "48"),
                new SadovodCSV("FORZA Кольцо-подставка для смартфона, 6,5x3x0,1см, фэйс", "(12-470-011)", 17, 1, "Обувь", "44"),
                new SadovodCSV("FORZA Кольцо-подставка для смартфона, 6,5x3x0,1см, фэйс", "(12-470-011)", 17, 1, "Обувь", "44"),
                new SadovodCSV("INBLOOM  Заборчик декоративный Цветок 10шт, 15x33 см", "(44-172-003)", 87, 4, "Обувь", "48"),
                new SadovodCSV("INBLOOM  Заборчик декоративный Цветок 10шт, 15x33 см", "(44-172-003)", 87, 4, "Обувь", "48")
        );

        List<SadovodCSV> resolveDuplicatedNames = new SadovodGoodsDuplicate().findDuplicateGoods(inputData);

        List<SadovodCSV> expected = List.of(
                new SadovodCSV("FORZA Кольцо-подставка для смартфона, 6,5x3x0,1см, фэйс", "(12-470-011)", 17, 3, "Обувь", "48"),
                new SadovodCSV("FORZA Кольцо-подставка для смартфона, 6,5x3x0,1см, фэйс", "(12-470-011)", 17, 2, "Обувь", "44"),
                new SadovodCSV("INBLOOM  Заборчик декоративный Цветок 10шт, 15x33 см", "(44-172-003)", 87, 8, "Обувь", "48")
        );
        assertEquals(expected.size(), resolveDuplicatedNames.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), resolveDuplicatedNames.get(i));
        }
    }
}
