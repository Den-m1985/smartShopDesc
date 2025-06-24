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
                new SadovodCSV("FORZA Кольцо-подставка для смартфона, 6,5x3x0,1см, фэйс", "(12-470-011)", "48", 1, 1),
                new SadovodCSV("FORZA Кольцо-подставка для смартфона, 6,5x3x0,1см, фэйс", "(12-470-011)", "48", 1, 1),
                new SadovodCSV("FORZA Кольцо-подставка для смартфона, 6,5x3x0,1см, фэйс", "(12-470-011)", "48", 1, 1),
                new SadovodCSV("FORZA Кольцо-подставка для смартфона, 6,5x3x0,1см, фэйс", "(12-470-011)", "44", 1, 1),
                new SadovodCSV("FORZA Кольцо-подставка для смартфона, 6,5x3x0,1см, фэйс", "(12-470-011)", "44", 1, 1),
                new SadovodCSV("INBLOOM  Заборчик декоративный Цветок 10шт, 15x33 см", "(44-172-003)", "48", 4, 4),
                new SadovodCSV("INBLOOM  Заборчик декоративный Цветок 10шт, 15x33 см", "(44-172-003)", "48", 4, 4)
        );

        List<SadovodCSV> resolveDuplicatedNames = new SadovodGoodsDuplicate().findDuplicateGoods(inputData);

        List<SadovodCSV> expected = List.of(
                new SadovodCSV("FORZA Кольцо-подставка для смартфона, 6,5x3x0,1см, фэйс", "(12-470-011)", "48", 1, 3),
                new SadovodCSV("FORZA Кольцо-подставка для смартфона, 6,5x3x0,1см, фэйс", "(12-470-011)", "44", 1, 2),
                new SadovodCSV("INBLOOM  Заборчик декоративный Цветок 10шт, 15x33 см", "(44-172-003)", "48", 4, 8)
        );
        assertEquals(expected.size(), resolveDuplicatedNames.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), resolveDuplicatedNames.get(i));
        }
    }
}
