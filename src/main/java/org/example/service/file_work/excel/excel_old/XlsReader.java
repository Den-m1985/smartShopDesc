package org.example.service.file_work.excel.excel_old;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class XlsReader {
    public HSSFWorkbook xlsRead(String filePath) {
        try {
            // Чтение .xls файла
            return new HSSFWorkbook(new FileInputStream(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
