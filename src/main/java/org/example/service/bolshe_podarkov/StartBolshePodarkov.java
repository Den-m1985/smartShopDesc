package org.example.service.bolshe_podarkov;

import org.example.DTO.DtoError;
import org.example.controller.TabController;
import org.example.enums.FileExtension;
import org.example.service.AbstractStartProcess;
import org.example.service.bolshe_podarkov.authentication.BolshePodarkovLogin;
import org.example.service.bolshe_podarkov.searchAndAdd.ServiceAddToBasket;
import org.example.service.bolshe_podarkov.searchAndAdd.addToBasket.Basket;
import org.example.service.browser.OpenWebSite;
import org.example.service.browser.chrome.DriverChrome;
import org.example.service.createPathFile.FileChooserManager;
import org.example.service.csv_filter.CsvFilter;
import org.example.service.csv_filter.csv.CsvFilterImpl;
import org.example.service.csv_filter.csv.StructureCSV;
import org.example.service.excel.excel_new.CreateReportExcel;

import java.util.List;

public class StartBolshePodarkov extends AbstractStartProcess {

    public StartBolshePodarkov(TabController tabController) {
        super(tabController);
        tabController.getExtension().add(new FileExtension[]{FileExtension.CSV});
        startProcess();
    }

    @Override
    protected void startController() throws Exception {
        new FileChooserManager(tabController);

        String csvPathFile = tabController.getFilePathManager().getFilePaths(FileExtension.CSV);
        CsvFilter csvFilter = new CsvFilterImpl();
        List<StructureCSV> data = csvFilter.csvFilter(csvPathFile);
        List<DtoError> reportList = csvFilter.getError();
        tabController.getView().appendToTextArea("\n\n");
        tabController.getView().appendToTextArea(languageManager.get("main_messages", "count.rows.csv") + ": " + data.size());
        tabController.getView().appendToTextArea("\n\n");

        new OpenWebSite(languageManager.get("bolshe_pod", "address"));

        new BolshePodarkovLogin(tabController);

        new Basket(tabController);

        ServiceAddToBasket searchAndAdd = new ServiceAddToBasket(data);
        reportList.addAll(searchAndAdd.getErrorSearch());

        tabController.getView().appendToTextArea("Размер отчета: " + reportList.size());

        if (reportList.size() != 0)
            new CreateReportExcel(reportList, tabController, languageManager.get("bolshe_pod", "file.nane.save"));

        /*
       Используем метод `quit()` вместо `close()`,
       чтобы гарантировать полное закрытие драйвера и освобождение всех связанных с ним ресурсов
         */
        DriverChrome.getChromeDriver().quit();
    }

}
