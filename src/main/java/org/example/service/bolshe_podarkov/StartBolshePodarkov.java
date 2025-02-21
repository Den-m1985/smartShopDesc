package org.example.service.bolshe_podarkov;

import org.example.DTO.DtoError;
import org.example.enums.NameProducts;
import org.example.service.AbstractStartProcess;
import org.example.service.bolshe_podarkov.authentication.BolshePodarkovLogin;
import org.example.service.bolshe_podarkov.searchAndAdd.ServiceAddToBasket;
import org.example.service.bolshe_podarkov.searchAndAdd.addToBasket.Basket;
import org.example.service.browser.OpenWebSite;
import org.example.service.browser.chrome.DriverChrome;
import org.example.service.csv_filtr.CsvFilter;
import org.example.service.csv_filtr.csv.CsvFilterImpl;
import org.example.service.csv_filtr.csv.StructureCSV;
import org.example.service.newExel.CreateReportExel;
import org.example.ui.tabbed_pane.TabView;

import java.util.List;

public class StartBolshePodarkov extends AbstractStartProcess {
    private final NameProducts product;

    public StartBolshePodarkov(TabView view, NameProducts product) {
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

        new OpenWebSite(languageManager.get("bolshe_pod", "address"));

        new BolshePodarkovLogin(product);

        new Basket(view);

        ServiceAddToBasket searchAndAdd = new ServiceAddToBasket(data);
        reportList.addAll(searchAndAdd.getErrorSearch());

        view.appendToTextArea("Размер отчета: " + reportList.size());

        if (reportList.size() != 0)
            new CreateReportExel(reportList, view, languageManager.get("bolshe_pod", "file.nane.save"));

        /*
       Используем метод `quit()` вместо `close()`,
       чтобы гарантировать полное закрытие драйвера и освобождение всех связанных с ним ресурсов
         */
        DriverChrome.getChromeDriver().quit();
    }

}
