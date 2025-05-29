package org.example.service.compare_files.service_process;

import org.apache.poi.ss.usermodel.Workbook;
import org.example.controller.TabController;
import org.example.enums.FileExtension;
import org.example.service.BasicLanguageManager;
import org.example.service.create_path_file.CreatePathFile;
import org.example.service.csv_filter.csv.StructureCSV;
import org.example.service.excel.excel_new.WriteExcelXlsx;

import java.util.List;

public class CreateReportInvoice extends BasicLanguageManager {

    public CreateReportInvoice(Workbook workbook, List<StructureCSV> noFindData, int numberSheet, TabController tabController) {
        String pathCSV = tabController.getFilePathManager().getFilePaths(FileExtension.CSV);
        String pathXLS = tabController.getFilePathManager().getFilePaths(FileExtension.XLS);
        if (pathXLS == null) {
            pathXLS = tabController.getFilePathManager().getFilePaths(FileExtension.XLSX);
        }

        String extension = FileExtension.XLSX.getExtension();
        String FILENAME_REPORT = languageManager.get("compare2files", "file.nane.save");
        String pricePathExls = new CreatePathFile().createPathFile(FILENAME_REPORT, extension);
        new NoMatchReportGenerator(workbook, noFindData, numberSheet, pathCSV, pathXLS);

        new WriteExcelXlsx(workbook, pricePathExls);
    }

}
