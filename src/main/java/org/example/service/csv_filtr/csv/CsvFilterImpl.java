package org.example.service.csv_filtr.csv;

import org.example.DTO.DtoError;
import org.example.service.csv_filtr.CsvFilter;

import java.util.ArrayList;
import java.util.List;

public class CsvFilterImpl implements CsvFilter {
    private List<DtoError> error = new ArrayList<>();


    public List<StructureCSV> csvFilter(String fileName) {
        List<String[]> rows = new CsvRead().readCSV2(fileName);

        // В этом блоке оставляем только те колонки где есть цена и кол-во
        OnlyGoods onlyGoods = new OnlyGoods();
        List<StructureCSV> dataWithItem = onlyGoods.findOnlyGoods(rows);
        error = onlyGoods.getReportCSV();

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
