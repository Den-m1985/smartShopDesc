package org.example.service.sadovod;

import org.example.DTO.DtoError;
import org.example.controller.TabController;
import org.example.enums.FileExtension;
import org.example.enums.TextLinks;
import org.example.enums.TextLinksSadovod;
import org.example.service.AbstractStartProcess;
import org.example.service.browser.OpenWebPage;
import org.example.service.browser.chrome.BrowserManager;
import org.example.service.create_path_file.FileChooserManager;
import org.example.service.csv_filter.SadovodCsvFilter;
import org.example.service.csv_filter.sadovod.SadovodCSV;
import org.example.service.excel.excel_new.ReportExcelCreator;
import org.example.service.sadovod.login.SadovodLogin;
import org.example.service.sadovod.search.GoodsHandlerFromSadovod;
import org.example.service.sadovod.search.add_to_backet.SadovodBasket;

import java.util.List;

public class StartSadovod extends AbstractStartProcess {
    public StartSadovod(TabController tabController) {
        super(tabController);
        tabController.getExtension().add(new FileExtension[]{FileExtension.CSV});
    }

    public void run() {
        startProcess();
    }

    @Override
    protected void startController() {
        new FileChooserManager(tabController);

        String csvPathFile = tabController.getFilePathManager().getFilePaths(FileExtension.CSV);

        SadovodCsvFilter csvFilter = new SadovodCsvFilter();
        List<SadovodCSV> data = csvFilter.csvFilter(csvPathFile);
        List<DtoError> reportList = csvFilter.getError();

        printText(TextLinks.COUNT_ROWS_CSV.getString() + data.size());

        BrowserManager browserManager = new BrowserManager();

        new OpenWebPage(browserManager, TextLinksSadovod.ADDRESS.getString());

        new SadovodLogin(browserManager).tryToLogInAccount();

        new SadovodBasket(browserManager, tabController).handleBacket();

        new GoodsHandlerFromSadovod(browserManager, reportList).processProductsForPurchase(data);

        new OpenWebPage(browserManager, TextLinksSadovod.BASKET_ADDRESS.getString());

        printText(TextLinks.REPORT_SIZE.getString() + reportList.size());

        if (!reportList.isEmpty())
            new ReportExcelCreator(tabController).createReportExcel(reportList, TextLinksSadovod.FILE_NAME_SAVE.getString());
    }

    private void printText(String text){
        tabController.getView().appendToTextArea("\n\n");
        tabController.getView().appendToTextArea(text);
    }

}
