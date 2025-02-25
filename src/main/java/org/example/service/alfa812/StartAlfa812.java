package org.example.service.alfa812;

import org.example.DTO.DtoError;
import org.example.controller.TabController;
import org.example.enums.FileExtension;
import org.example.service.AbstractStartProcess;
import org.example.service.alfa812.login.Alfa812LogIn;
import org.example.service.alfa812.searchAndAdd.CloudWindow;
import org.example.service.alfa812.searchAndAdd.SearchProcess;
import org.example.service.alfa812.searchAndAdd.ShoppingCart;
import org.example.service.browser.OpenWebSite;
import org.example.service.createPathFile.FileChooserManager;
import org.example.service.csv_filter.CsvFilter;
import org.example.service.csv_filter.csv.CsvFilterImpl;
import org.example.service.csv_filter.csv.StructureCSV;
import org.example.service.excel.excel_new.CreateReportExcel;

import java.util.List;

public class StartAlfa812 extends AbstractStartProcess {

    public StartAlfa812(TabController tabController) {
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

        new OpenWebSite(languageManager.get("alfa812", "address"));

        new CloudWindow();

        new Alfa812LogIn(tabController);

        new CloudWindow();

        for (StructureCSV goods : data) {
            String goodsName = goods.getName();
            try {
                new SearchProcess(reportList).executeProcess(goods);
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

        new CloudWindow();

        // по завершению заходим в корзину
        new ShoppingCart().clickCart();

        tabController.getView().appendToTextArea(languageManager.get("main_messages", "report.size") + reportList.size());

        if (reportList.size() > 0)
            new CreateReportExcel(reportList, tabController, languageManager.get("alfa812", "file.nane.save"));

         /*
        При закрытии браузера список покупок не сохраняется.
        DriverChrome.getChromeDriver().quit();
         */
    }

}
