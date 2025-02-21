package org.example.service.alfa812;

import org.example.DTO.DtoError;
import org.example.enums.NameProducts;
import org.example.service.AbstractStartProcess;
import org.example.service.alfa812.login.Alfa812LogIn;
import org.example.service.alfa812.searchAndAdd.CloudWindow;
import org.example.service.alfa812.searchAndAdd.SearchProcess;
import org.example.service.alfa812.searchAndAdd.ShoppingCart;
import org.example.service.browser.OpenWebSite;
import org.example.service.csv_filtr.CsvFilter;
import org.example.service.csv_filtr.csv.CsvFilterImpl;
import org.example.service.csv_filtr.csv.StructureCSV;
import org.example.service.newExel.CreateReportExel;
import org.example.ui.tabbed_pane.TabView;

import java.util.List;

public class StartAlfa812 extends AbstractStartProcess {
    private final NameProducts product;

    public StartAlfa812(TabView view, NameProducts product) {
        super(view);
        this.product = product;
        startProcess();
    }

    @Override
    protected void startController(String pathFile) throws Exception {
        CsvFilter csvFilter = new CsvFilterImpl();
        List<StructureCSV> data = csvFilter.csvFilter(pathFile);
        List<DtoError> reportList = csvFilter.getError();
        view.appendToTextArea("\n\n");
        view.appendToTextArea(languageManager.get("main_messages", "count.rows.csv") + ": " + data.size());
        view.appendToTextArea("\n\n");

        new OpenWebSite(languageManager.get("alfa812", "address"));

        new CloudWindow();

        new Alfa812LogIn(product);

        new CloudWindow();

        for (StructureCSV goods : data) {
            String goodsName = goods.getName();
            try {
                new SearchProcess(reportList).executeProcess(goods);
            } catch (Exception e) {
                view.appendToTextArea("\n\n");
                view.appendToTextArea(languageManager.get("main_messages", "error.goods") + goodsName);
                view.appendToTextArea("\n");

                view.appendToTextArea(e.getMessage());
                view.appendToTextArea("\n\n");

                String goodsSize = goods.getArticular();
                reportList.add(new DtoError(goodsName, goodsSize, languageManager.get("main_messages", "error.main")));
            }
        }

        new CloudWindow();

        // по завершению заходим в корзину
        new ShoppingCart().clickCart();

        view.appendToTextArea(languageManager.get("main_messages", "report.size") + reportList.size());

        if (reportList.size() > 0)
            new CreateReportExel(reportList, view, languageManager.get("alfa812", "file.nane.save"));

         /*
        При закрытии браузера список покупок не сохраняется.
        DriverChrome.getChromeDriver().quit();
         */
    }

}
