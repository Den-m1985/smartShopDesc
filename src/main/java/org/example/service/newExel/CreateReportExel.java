package org.example.service.newExel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.DTO.DtoError;
import org.example.service.BasicLanguageManager;
import org.example.service.createPathFile.CreatePathFile;
import org.example.ui.tabbed_pane.TabView;

import java.util.Comparator;
import java.util.List;

public class CreateReportExel extends BasicLanguageManager {

    public CreateReportExel(List<DtoError> list, TabView view, String fileName) {

        list.sort(Comparator.comparing(DtoError::getMessage));

        //create no find article
        XSSFWorkbook workbook = new CreateExel().createExel(list);

        //write no find article, xls file in downloads directory
        String extension = languageManager.get("main_messages", "extension.xlsx");
        String downloadsPath = new CreatePathFile().createPathFile(fileName, extension);

        new WriteExelXlsx(workbook, downloadsPath);

        view.appendToTextArea("\n\n");
        view.appendToTextArea(languageManager.get("main_messages", "text.file.save"));
        view.appendToTextArea("\n");
        view.appendToTextArea(downloadsPath);
    }

}
