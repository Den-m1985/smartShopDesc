package org.example.service.newExel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.DTO.DtoError;
import org.example.service.BasicLanguageManager;

import java.util.List;

public class CreateExel extends BasicLanguageManager {

    public XSSFWorkbook createExel(List<DtoError> list) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        String textSheet = languageManager.get("main_messages", "name.sheet");
        Sheet sheet = workbook.createSheet(textSheet);

        //Row  строка
        //Cell столб
        Row row = sheet.createRow(0);
        String noFindArticular = languageManager.get("main_messages", "product.check");
        row.createCell(0).setCellValue(noFindArticular);

        // create topic
        Row rowTop = sheet.createRow(2);
        rowTop.createCell(0).setCellValue("Наименование");
        rowTop.createCell(2).setCellValue("Артикул");
        rowTop.createCell(3).setCellValue("Тип ошибки");


        for (int i = 0; i < list.size(); i++) {
            Row row2 = sheet.createRow(i + 3);
            row2.createCell(0).setCellValue(list.get(i).getName());
            row2.createCell(2).setCellValue(list.get(i).getArticular());
            row2.createCell(3).setCellValue(list.get(i).getMessage());
        }
        return workbook;
    }
}
