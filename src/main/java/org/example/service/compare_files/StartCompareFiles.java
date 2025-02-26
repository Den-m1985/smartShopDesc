package org.example.service.compare_files;

import org.apache.poi.ss.usermodel.Workbook;
import org.example.controller.TabController;
import org.example.enums.FileExtension;
import org.example.service.AbstractStartProcess;
import org.example.service.compare_files.service_process.CreateReportInvoice;
import org.example.service.compare_files.service_process.ExcelDataValidator;
import org.example.service.compare_files.service_process.ExcelFormatSelector;
import org.example.service.createPathFile.FileChooserManager;
import org.example.service.csv_filter.csv.CsvFilterImpl;
import org.example.service.csv_filter.csv.StructureCSV;

import java.util.List;

public class StartCompareFiles extends AbstractStartProcess {

    public StartCompareFiles(TabController tabController) {
        super(tabController);
        tabController.getExtension().add(new FileExtension[]{FileExtension.XLS, FileExtension.XLSX});
        tabController.getExtension().add(new FileExtension[]{FileExtension.CSV});
    }

    public void run(){
        startProcess();
    }

    @Override
    protected void startController() {
        new FileChooserManager(tabController);

        String csvPathFile = tabController.getFilePathManager().getFilePaths(FileExtension.CSV);
        List<StructureCSV> data = new CsvFilterImpl().csvFilter(csvPathFile);
        tabController.getView().appendToTextArea("\n\n");
        tabController.getView().appendToTextArea(languageManager.get("main_messages", "count.rows.csv") + ": " + data.size());
        tabController.getView().appendToTextArea("\n\n");

        Workbook workbook = new ExcelFormatSelector(tabController).choiceOldNewExel();

        int numberSheet = 0;  // номер страницы в файле.
        ExcelDataValidator addCell = new ExcelDataValidator(data, workbook, numberSheet);
        workbook = addCell.findCellEXEL();

        new CreateReportInvoice(workbook, data, numberSheet, tabController);
    }

}
