package org.example.service.product.alfa812;

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
import org.example.service.product.alfa812.login.Alfa812LogIn;
import org.example.service.product.alfa812.search.SearchProcess;
import org.example.service.product.alfa812.search_and_add.CloudWindow;
import org.example.service.product.alfa812.search_and_add.ShoppingCart;
import org.example.service.util.WebElementsUtil;

import java.util.List;

public class StartAlfa812 extends AbstractStartProcess {

    public StartAlfa812(TabController tabController) {
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
        tabController.getView().appendToTextArea("\n\n");
        tabController.getView().appendToTextArea(languageManager.get("main_messages", "count.rows.csv") + ": " + data.size());
        tabController.getView().appendToTextArea("\n\n");

        WebElementsUtil webElementsUtil = new WebElementsUtil();
        CloudWindow cloudWindow = new CloudWindow(webElementsUtil);

        new OpenWebPage(webElementsUtil, languageManager.get("alfa812", "address"));

        cloudWindow.closeModalWindow();

        new Alfa812LogIn(webElementsUtil, tabController);

        cloudWindow.closeModalWindow();

        SearchProcess searchProcess = new SearchProcess(webElementsUtil, reportList);

        for (StructureCSV goods : data) {
            String goodsName = goods.getName();
            try {
                searchProcess.executeProcess(goods);
            } catch (Exception e) {
                tabController.getView().appendToTextArea("\n\n");
                tabController.getView().appendToTextArea(languageManager.get("main_messages", "error.goods") + goodsName);
                tabController.getView().appendToTextArea("\n");

                tabController.getView().appendToTextArea(e.getMessage());
                tabController.getView().appendToTextArea("\n\n");

                String goodsSize = goods.getArticular();
                reportList.add(new DtoError(goodsName, goodsSize, languageManager.get("main_messages", "error.main")));
            }
        }

        cloudWindow.closeModalWindow();

        // по завершению заходим в корзину
        new ShoppingCart(webElementsUtil).clickCart();

        tabController.getView().appendToTextArea(languageManager.get("main_messages", "report.size") + reportList.size());

        if (!reportList.isEmpty())
            new ReportExcelCreator(tabController).createReportExcel(reportList, languageManager.get("alfa812", "file.nane.save"));

         /*
        При закрытии браузера список покупок не сохраняется.
        DriverChrome.getChromeDriver().quit();
         */
    }

}
