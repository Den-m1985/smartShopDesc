package org.example.service.file_work.excel.excel_new;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.dto.DtoError;
import org.example.controller.TabController;
import org.example.enums.TextLinks;
import org.example.service.create_path_file.CreatePathFile;

import java.util.Comparator;
import java.util.List;

public class ReportExcelCreator {
    private final TabController tabController;

    public ReportExcelCreator(TabController tabController) {
        this.tabController = tabController;
    }

    public void createReportExcel(List<DtoError> list, String fileName) {
        list.sort(Comparator.comparing(DtoError::getMessage));

        XSSFWorkbook workbook = new CreateExcel().createExel(list);

        String extension = TextLinks.XLSX.getString();
        String downloadsPath = new CreatePathFile().createPathFile(fileName, extension);

        new WriteExcelXlsx(workbook, downloadsPath);

        reportText(downloadsPath);
    }

    private void reportText(String text){
        tabController.getView().appendToTextArea("\n\n");
        tabController.getView().appendToTextArea(TextLinks.TEXT_SAVE_FILE.getString());
        tabController.getView().appendToTextArea("\n");
        tabController.getView().appendToTextArea(text);
    }
}
