package org.example.service.csv_filter;

import org.example.DTO.DtoError;
import org.example.enums.TextLinks;
import org.example.service.csv_filter.csv.CsvRead;
import org.example.service.csv_filter.csv.DeleteQuotes;
import org.example.service.csv_filter.csv.SeparateGoods;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCsvFilter {
    private final List<DtoError> error = new ArrayList<>();

    protected abstract <T> List<T> csvFilter(String fileName, int lengthRow);

    protected List<String[]> getRawRows(String fileName, int lengthRow) {
        String encoding = TextLinks.ENCODING.getString();
        List<String[]> rowsArray = new CsvRead().readCSV(fileName, encoding);
        List<String[]> rows = new SeparateGoods().separateArray(rowsArray, lengthRow);

        new DeleteQuotes(rows);
        return rows;
    }

    public List<DtoError> getError() {
        return error;
    }
}
