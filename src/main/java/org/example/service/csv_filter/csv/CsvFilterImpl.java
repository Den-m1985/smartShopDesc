package org.example.service.csv_filter.csv;

import org.example.DTO.DtoError;
import org.example.enums.TextLinks;
import org.example.service.csv_filter.CsvFilter;

import java.util.ArrayList;
import java.util.List;

public class CsvFilterImpl implements CsvFilter {
    private final List<DtoError> error = new ArrayList<>();


    public List<StructureCSV> csvFilter(String fileName) {
        List<String[]> rows = getRawRows(fileName, 4);

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

    public List<String[]> getRawRows(String fileName, int lengthRow){
        String encoding = TextLinks.ENCODING.getString();
        List<String[]> rowsArray = new CsvRead().readCSV(fileName, encoding);
        List<String[]> rows = new SeparateGoods().separateArray(rowsArray, lengthRow);
        int difference = rowsArray.size() - rows.size();
        if (difference > 0){
            String message = rowsArray.size() + " строк не вошли в диапазон количества колонок. Разница: " + difference + " колонок.";
            error.add(new DtoError("", "", message));
        }
        new DeleteQuotes(rows);
        return rows;
    }

    public List<DtoError> getError() {
        return error;
    }

}
