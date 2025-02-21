package org.example.service.newExel;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriteExelXlsx {

    public WriteExelXlsx(Workbook workbook, String fileNamePrice) {
        try (FileOutputStream outputStream = new FileOutputStream(fileNamePrice)) {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
