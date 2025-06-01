package org.example.service.product.compare_files.service_process;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.example.service.csv_filter.csv.StructureCSV;

import java.lang.reflect.Field;
import java.util.List;

public class ExcelDataValidator {
    private final List<StructureCSV> data;
    private final int cellHeaderName;
    private final int cellHeaderSum;
    private final int cellLast;
    private final CellStyle yellowCellStyle;
    private final Workbook workbook;
    private final int numberSheet;
    private final int headerRowIndex;


    public ExcelDataValidator(List<StructureCSV> data, Workbook workbook, int numberSheet) {
        this.data = data;
        this.workbook = workbook;
        this.numberSheet = numberSheet;

        // Создаем стиль для закраски ячейки желтым цветом
        yellowCellStyle = workbook.createCellStyle();
        yellowCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        yellowCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // получаем индексы заголовка
        HeaderPositionFinder findHeaderIndex = new HeaderPositionFinder(workbook, numberSheet);
        cellHeaderName = findHeaderIndex.getCellHeaderName();
        cellHeaderSum = findHeaderIndex.getCellHeaderSum();
        cellLast = findHeaderIndex.getCellLast();
        headerRowIndex = findHeaderIndex.getHeaderRowIndex();

    }


    public Workbook findCellEXEL() {
        //Row  строка      Cell столб
        Sheet sheet = workbook.getSheetAt(numberSheet);
        fillHeaderReport(sheet.getRow(headerRowIndex));

        int length = sheet.getLastRowNum();
        for (int i = headerRowIndex + 1; i < length; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Cell cellName = row.getCell(cellHeaderName);
                String nameEXL = cellRead(cellName);

                Cell cellSum = row.getCell(cellHeaderSum);
                if (nameEXL != null && isCellSum(cellSum)) {   //  если в ячейке есть цифра с суммой
                    // сравниваем строки
                    ReportData resultCompared = new ExcelCsvDataComparer(data, nameEXL).compareWithCSV();
                    if (resultCompared != null) {
                        addToSheet(row, resultCompared);
                    } else {
                        setCellColor(row);  // закрашиваем ячейку
                    }
                }
            }
        }
        return workbook;
    }


    private void addToSheet(Row row, ReportData reportData) {
        Field[] fields = ReportData.class.getDeclaredFields(); // получаем все поля класса
        for (int i = 0; i < fields.length; i++) {
            int cellIndex = cellLast + 2 + i;
            Cell cellReport = row.getCell(cellIndex); // получаем ячейку
            if (cellReport == null) { // если ячейка пустая, создаем ее
                cellReport = row.createCell(cellIndex);
            }
            try {
                // Получаем значение поля с использованием рефлексии
                Object value = fields[i].get(reportData);
                // Устанавливаем значение в ячейку
                if (value != null) {
                    cellReport.setCellValue(value.toString());
                }
            } catch (IllegalAccessException e) {
                // Обработка ошибок доступа к полю
                e.printStackTrace();
            }
        }
    }

    private void fillHeaderReport(Row row) {
        String[] nameReport = {"Совпадение", "Цена", "Кол-во", "Наименование"};
        Field[] fields = ReportData.class.getDeclaredFields(); // получаем все поля класса
        for (int i = 0; i < fields.length; i++) {
            int cellIndex = cellLast + 2 + i;
            Cell cellReport = row.getCell(cellIndex); // получаем ячейку
            if (cellReport == null) { // если ячейка пустая, создаем ее
                cellReport = row.createCell(cellIndex);
            }
            cellReport.setCellValue(nameReport[i]);
        }
    }


    // закрашиваем пустую ячейку
    private void setCellColor(Row row) {
        int spacePercentage = cellLast + 2;
        Cell cell = row.getCell(spacePercentage); // получаем  ячейку
        // если ячейка пустая, создаем ее
        if (cell == null) {
            cell = row.createCell(spacePercentage);
        }
        cell.setCellStyle(yellowCellStyle);  // Применяем стиль к ячейке
    }


    private String cellRead(Cell cell) {
        if (cell != null) {
            CellType cellType = cell.getCellType();
            if (cellType == CellType.STRING) {
                return cell.getStringCellValue().trim();
            } else if (cellType == CellType.NUMERIC) {
                double numericValue = cell.getNumericCellValue();
                int intValue = (int) numericValue;  // Преобразование к целому числу
                return String.valueOf(intValue);
            } else if (cellType == CellType.BOOLEAN) {
                return String.valueOf(cell.getBooleanCellValue());
            }
        }
        return null;
    }


    private boolean isCellSum(Cell cell) {
        if (cell == null) {
            return false;
        }
        CellType cellType = cell.getCellType();
        return cellType == CellType.NUMERIC;
    }

}