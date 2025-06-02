package org.example.service.product.bolshe_podarkov;

import org.example.controller.TabController;
import org.example.dto.DtoError;
import org.example.enums.FileExtension;
import org.example.service.AbstractStartProcess;
import org.example.service.browser.OpenWebPage;
import org.example.service.create_path_file.FileChooserManager;
import org.example.service.csv_filter.CsvFilter;
import org.example.service.csv_filter.csv.CsvFilterImpl;
import org.example.service.csv_filter.csv.StructureCSV;
import org.example.service.file_work.excel.excel_new.ReportExcelCreator;
import org.example.service.product.bolshe_podarkov.add_to_basket.Basket;
import org.example.service.product.bolshe_podarkov.login.BolshePodarkovLogin;
import org.example.service.product.bolshe_podarkov.search.ServiceAddToBasket;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;

import java.util.List;

public class StartBolshePodarkov extends AbstractStartProcess {
    private WebElementsUtil webElementsUtil;

    public StartBolshePodarkov(TabController tabController) {
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
        CsvFilter csvFilter = new CsvFilterImpl();
        List<StructureCSV> data = csvFilter.csvFilter(csvPathFile);
        List<DtoError> reportList = csvFilter.getError();

        printText(languageManager.get("main_messages", "count.rows.csv") + ": " + data.size());

        webElementsUtil = new WebElementsUtil();

        new OpenWebPage(webElementsUtil, languageManager.get("bolshe_pod", "address"));

        webElementsUtil.clickElement(By.className("cookie-policy__button"));

        new BolshePodarkovLogin(webElementsUtil, tabController);

        new Basket(webElementsUtil, tabController);

        ServiceAddToBasket searchAndAdd = new ServiceAddToBasket(webElementsUtil, reportList);
        searchAndAdd.processProductsForPurchase(data);

        printText("Размер отчета: " + reportList.size());

        if (!reportList.isEmpty())
            new ReportExcelCreator(tabController).createReportExcel(reportList, languageManager.get("bolshe_pod", "file.nane.save"));

        cancelDriver();
    }

    private void printText(String text) {
        tabController.getView().appendToTextArea("\n\n");
        tabController.getView().appendToTextArea(text);
    }

    /*
       Используем метод `quit()` вместо `close()`,
       чтобы гарантировать полное закрытие драйвера и освобождение всех связанных с ним ресурсов
    */
    public void cancelDriver() {
        webElementsUtil.getDriver().quit();
    }

}
