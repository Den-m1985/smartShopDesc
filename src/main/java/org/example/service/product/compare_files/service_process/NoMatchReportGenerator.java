package org.example.service.product.compare_files.service_process;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.example.service.BasicLanguageManager;
import org.example.service.csv_filter.csv.StructureCSV;

import java.util.List;

public class NoMatchReportGenerator extends BasicLanguageManager {

    public NoMatchReportGenerator(Workbook workbook, List<StructureCSV> noFindData, int numberSheet, String pathCSV, String pathXLS) {
        Sheet sheet = workbook.getSheetAt(numberSheet);
        int lastRow = sheet.getLastRowNum();
        int startReport = lastRow + 5;

        Row row1 = sheet.createRow(startReport);
        row1.createCell(0).setCellValue(languageManager.get("compare2files", "report.name"));
        Row row2 = sheet.createRow(startReport + 1);
        row2.createCell(0).setCellValue(pathCSV);
        Row row3 = sheet.createRow(startReport + 2);
        row3.createCell(0).setCellValue(pathXLS);

        // create topic
        Row rowTop = sheet.createRow(startReport + 4);
        rowTop.createCell(0).setCellValue(languageManager.get("compare2files", "report.no.find"));

        for (int i = 0; i < noFindData.size(); i++) {
            Row row5 = sheet.createRow(i + startReport + 5);
            String name = noFindData.get(i).getName();
            row5.createCell(5).setCellValue(name);
        }
    }

}
