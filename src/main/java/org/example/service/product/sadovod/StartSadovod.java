package org.example.service.product.sadovod;

import org.example.controller.TabController;
import org.example.dto.DtoError;
import org.example.enums.FileExtension;
import org.example.enums.TextLinks;
import org.example.enums.TextLinksSadovod;
import org.example.service.AbstractStartProcess;
import org.example.service.BaseProductTask;
import org.example.service.browser.OpenWebPage;
import org.example.service.create_path_file.FileChooserManager;
import org.example.service.csv_filter.SadovodCsvFilter;
import org.example.service.csv_filter.sadovod.SadovodCSV;
import org.example.service.file_work.excel.excel_new.ReportExcelCreator;
import org.example.service.product.sadovod.login.SadovodLogin;
import org.example.service.product.sadovod.search.GoodsHandlerFromSadovod;
import org.example.service.product.sadovod.search.add_to_backet.SadovodBasket;
import org.example.service.util.WebElementsUtil;

import java.util.List;

public class StartSadovod extends AbstractStartProcess implements BaseProductTask {
    private WebElementsUtil webElementsUtil;

    public StartSadovod(TabController tabController) {
        super(tabController);
        tabController.getExtension().add(new FileExtension[]{FileExtension.CSV});
    }

    public void run() {
        startProcess();
    }

    @Override
    protected void startController() throws Exception {
        new FileChooserManager(tabController);

        String csvPathFile = tabController.getFilePathManager().getFilePaths(FileExtension.CSV);

        SadovodCsvFilter csvFilter = new SadovodCsvFilter();
        List<SadovodCSV> data = csvFilter.csvFilter(csvPathFile, 6);
        List<DtoError> reportList = csvFilter.getError();

        printText(TextLinks.COUNT_ROWS_CSV.getString() + data.size());

        webElementsUtil = new WebElementsUtil();

        new OpenWebPage(webElementsUtil, TextLinksSadovod.ADDRESS.getString());

        new SadovodLogin(webElementsUtil, tabController);

        new SadovodBasket(webElementsUtil, tabController).handleBacket();

        new GoodsHandlerFromSadovod(webElementsUtil, reportList).processProductsForPurchase(data);

        new OpenWebPage(webElementsUtil, TextLinksSadovod.BASKET_ADDRESS.getString());

        printText(TextLinks.REPORT_SIZE.getString() + reportList.size());

        if (!reportList.isEmpty())
            new ReportExcelCreator(tabController).createReportExcel(reportList, TextLinksSadovod.FILE_NAME_SAVE.getString());

        cancelDriver();
    }

    private void printText(String text) {
        tabController.getView().appendToTextArea("\n\n");
        tabController.getView().appendToTextArea(text);
    }

    @Override
    public void cancelDriver() {
        webElementsUtil.getDriver().quit();
    }

}
