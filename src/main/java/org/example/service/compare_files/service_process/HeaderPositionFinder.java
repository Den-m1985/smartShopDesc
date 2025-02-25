package org.example.service.compare_files.service_process;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class HeaderPositionFinder {
    private final String headerName = "Товары (работы, услуги)";
    private final String headerName2 = "Товар";
    private final String headerSum = "Сумма";
    private int cellHeaderName;
    private int cellHeaderSum;
    private int cellLast;
    private int headerRowIndex;
    private boolean findRowWithHeader;


    public HeaderPositionFinder(Workbook workbook, int numberSheet) {
        //Row  строка      Cell столб
        Sheet sheet = workbook.getSheetAt(numberSheet);
        for (Row row : sheet) {
            findNameHeader(row);
            if (findRowWithHeader){
                break;
            }
        }
    }


    private void findNameHeader(Row row) {
        for (Cell cell : row) {
            String tempEXL = cellRead(cell);
            if (tempEXL != null) {
                String str = tempEXL.trim();
                if (str.equals(headerName) || str.equals(headerName2)) {
                    cellHeaderName = cell.getColumnIndex();
                    headerRowIndex = row.getRowNum();
                    cellLast = row.getLastCellNum();
                    findRowWithHeader = true;
                }
            }
            if (tempEXL != null) {
                String str = tempEXL.trim();
                if (findRowWithHeader && str.equals(headerSum)) {
                    cellHeaderSum = cell.getColumnIndex();
                    return;
                }
            }
        }
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


    public int getCellHeaderName() {
        return cellHeaderName;
    }

    public int getCellHeaderSum() {
        return cellHeaderSum;
    }

    public int getCellLast() {
        return cellLast;
    }

    public int getHeaderRowIndex() {
        return headerRowIndex;
    }

}
