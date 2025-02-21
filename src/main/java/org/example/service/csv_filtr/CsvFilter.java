package org.example.service.csv_filtr;

import org.example.DTO.DtoError;
import org.example.service.csv_filtr.csv.StructureCSV;

import java.util.List;

public interface CsvFilter {
    List<StructureCSV> csvFilter(String fileName);

    List<DtoError> getError();
}
