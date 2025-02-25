package org.example.service.csv_filter;

import org.example.DTO.DtoError;
import org.example.service.csv_filter.csv.StructureCSV;

import java.util.List;

public interface CsvFilter {
    List<StructureCSV> csvFilter(String fileName);

    List<DtoError> getError();
}
