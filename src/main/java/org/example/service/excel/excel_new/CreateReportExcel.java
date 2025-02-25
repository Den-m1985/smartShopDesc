package org.example.service.excel.excel_new;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.DTO.DtoError;
import org.example.controller.TabController;
import org.example.service.BasicLanguageManager;
import org.example.service.createPathFile.CreatePathFile;

import java.util.Comparator;
import java.util.List;

public class CreateReportExcel extends BasicLanguageManager {

    public CreateReportExcel(List<DtoError> list, TabController tabController, String fileName) {

        list.sort(Comparator.comparing(DtoError::getMessage));

        //create no find article
        XSSFWorkbook workbook = new CreateExcel().createExel(list);

        //write no find article, xls file in downloads directory
        String extension = languageManager.get("main_messages", "extension.xlsx");
        String downloadsPath = new CreatePathFile().createPathFile(fileName, extension);

        new WriteExcelXlsx(workbook, downloadsPath);

        tabController.getView().appendToTextArea("\n\n");
        tabController.getView().appendToTextArea(languageManager.get("main_messages", "text.file.save"));
        tabController.getView().appendToTextArea("\n");
        tabController.getView().appendToTextArea(downloadsPath);
    }

}
