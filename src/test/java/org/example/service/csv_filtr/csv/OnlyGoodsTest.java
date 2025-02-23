package org.example.service.csv_filtr.csv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OnlyGoodsTest {

    @Test
    public void onlyGoodsTest() {

        String[] array1 = {"\"Веточка \"\"Ель\"\" с ягодками в мешочке 17 см (SF-3539)\"", "677-236", "41,5", "5"};
        String[] array2 = {"ex", "123", "123.2", "20"};
        String[] array3 = {"", "", "", ""};
        List<String[]> rows = new ArrayList<>();
        rows.add(array1);
        rows.add(array2);
        rows.add(array3);

        List<StructureCSV> dataWithItem = new OnlyGoods().findOnlyGoods(rows);

        int result = dataWithItem.size();

        assertEquals(2, result);
    }

    @Test
    public void onlyGoodsTest2() {
        List<String[]> inputData = List.of(
                new String[]{"НОВИНКИ", "", "", ""},
                new String[]{"Название", "Размер/Цвет", "Цена", "Количество"},
                new String[]{"FORZA Кольцо-подставка для смартфона, 6,5x3x0,1см, фэйс", "(12-470-011)", "17", "1"},
                new String[]{"INBLOOM  Заборчик декоративный Цветок 10шт, 15x33 см", "(44-172-003)", "87", "4"},
                new String[]{"INBLOOM Чехлы-бахилы на обувь силиконовые S, 21x12,5см (+-1см)", "(27-188-094)", "55", "4"},
                new String[]{"Свисток спортивный на веревочке", "(343-069)", "13", "2"},
                new String[]{"ЭЛЕКТРОТОВАРЫ", ";", ";", ";"},
                new String[]{"Название", "Размер/Цвет", "Цена", "Количество"}
        );
        List<StructureCSV> dataWithItem = new OnlyGoods().findOnlyGoods(inputData);
        List<String[]> expected = List.of(
                new String[]{"FORZA Кольцо-подставка для смартфона, 6,5x3x0,1см, фэйс", "(12-470-011)", "17", "1"},
                new String[]{"INBLOOM  Заборчик декоративный Цветок 10шт, 15x33 см", "(44-172-003)", "87", "4"},
                new String[]{"INBLOOM Чехлы-бахилы на обувь силиконовые S, 21x12,5см (+-1см)", "(27-188-094)", "55", "4"},
                new String[]{"Свисток спортивный на веревочке", "(343-069)", "13", "2"}
        );
        assertEquals(dataWithItem.size(), expected.size());
    }

}
