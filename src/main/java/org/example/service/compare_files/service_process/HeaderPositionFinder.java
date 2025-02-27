package org.example.service.compare_files.service_process;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.example.service.BasicLanguageManager;
import org.example.service.excel.excel_new.ExcelCellRead;

public class HeaderPositionFinder extends BasicLanguageManager {
    private int cellHeaderName;
    private int cellHeaderSum;
    private int cellLast;
    private int headerRowIndex;
    private boolean findRowWithHeader;
    private final ExcelCellRead excelCellRead;


    public HeaderPositionFinder(Workbook workbook, int numberSheet) {
        excelCellRead = new ExcelCellRead();
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
            String tempEXL = excelCellRead.cellRead(cell);
            if (tempEXL != null) {
                String str = tempEXL.trim();
                if (str.equals(languageManager.get("compare2files", "header.name.items")) ||
                        str.equals(languageManager.get("compare2files", "header.name.item"))) {
                    cellHeaderName = cell.getColumnIndex();
                    headerRowIndex = row.getRowNum();
                    cellLast = row.getLastCellNum();
                    findRowWithHeader = true;
                }
            }
            if (tempEXL != null) {
                String str = tempEXL.trim();
                if (findRowWithHeader && str.equals(languageManager.get("compare2files", "header.name.sum"))) {
                    cellHeaderSum = cell.getColumnIndex();
                    return;
                }
            }
        }
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
