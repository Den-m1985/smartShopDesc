package org.example.service.csv_filter.csv;

import org.example.DTO.DtoError;
import org.example.service.BasicLanguageManager;
import org.example.service.csv_filter.CsvFilter;

import java.util.ArrayList;
import java.util.List;

public class CsvFilterImpl extends BasicLanguageManager implements CsvFilter {
    private final List<DtoError> error = new ArrayList<>();


    public List<StructureCSV> csvFilter(String fileName) {
        String encoding = languageManager.get("main_messages", "encoding.windows");
        List<String[]> rowsArray = new CsvRead().readCSV(fileName, encoding);

        int lengthLine = 4; // count normal length line in report in csv file
        List<String[]> rows = new SeparateGoods().separateArray(rowsArray, lengthLine);

        new DeleteQuotes(rows);

        // В этом блоке оставляем только те колонки где есть цена и кол-во
        List<StructureCSV> dataWithItem = new OnlyGoods().findOnlyGoods(rows);

        // этот блок возвращает иникальные элементы
        UniqueGoods uniqueGoods = new UniqueGoods();
        List<StructureCSV> uniqueValues = uniqueGoods.findUniqueGoods(dataWithItem);
        List<StructureCSV> duplicateNames = uniqueGoods.getDuplicateNames();

        // этот блок работатет с повторяющимися именами.
        List<StructureCSV> resolveDuplicatedNames = new DuplicateGoods().findDuplicateGoods(duplicateNames);
        uniqueValues.addAll(resolveDuplicatedNames);
        return uniqueValues;
    }

    public List<DtoError> getError() {
        return error;
    }

}
