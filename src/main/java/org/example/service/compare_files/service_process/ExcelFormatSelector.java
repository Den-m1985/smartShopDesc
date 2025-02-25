package org.example.service.compare_files.service_process;

import org.apache.poi.ss.usermodel.Workbook;
import org.example.controller.TabController;
import org.example.enums.FileExtension;
import org.example.service.BasicLanguageManager;
import org.example.service.excel.excel_new.XlsxReader;
import org.example.service.excel.excel_old.XlsReader;

public class ExcelFormatSelector extends BasicLanguageManager {
    private final TabController tabController;
    private final String pathExel;


    public ExcelFormatSelector(TabController tabController) {
        this.tabController = tabController;
        String excelPathFile = tabController.getFilePathManager().getFilePaths(FileExtension.XLS);
        if (excelPathFile == null) {
            excelPathFile = tabController.getFilePathManager().getFilePaths(FileExtension.XLSX);
        }
        this.pathExel = excelPathFile;
    }

    public Workbook choiceOldNewExel() {
        if (pathExel.endsWith(FileExtension.XLS.getExtension())) {
            return new XlsReader().xlsRead(pathExel);
        } else if (pathExel.endsWith(FileExtension.XLSX.getExtension())) {
            return new XlsxReader().xlsxRead(pathExel);
        } else {
            String message = languageManager.get("main_messages", "extension.error");
            tabController.getView().appendToTextArea("\n" + message);
        }
        return null;
    }

}
