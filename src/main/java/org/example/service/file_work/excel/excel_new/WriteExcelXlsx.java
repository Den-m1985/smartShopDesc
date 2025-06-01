package org.example.service.file_work.excel.excel_new;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriteExcelXlsx {

    public WriteExcelXlsx(Workbook workbook, String fileNamePrice) {
        try (FileOutputStream outputStream = new FileOutputStream(fileNamePrice)) {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
