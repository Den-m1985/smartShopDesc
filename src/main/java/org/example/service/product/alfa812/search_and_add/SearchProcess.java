package org.example.service.product.alfa812.search_and_add;

import org.example.DTO.DtoError;
import org.example.service.BasicLanguageManager;
import org.example.service.csv_filter.csv.StructureCSV;
import org.example.service.util.WebElementsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchProcess extends BasicLanguageManager {
    private final List<DtoError> reportList;
    private final CloudWindow cloudWindow;
    private final WebElementsUtil webElementsUtil;
    private final GoodsSearcher searchGoods;
    private final CommandSelectSize commandSelectSize;
    private final CheckPrice checkPrice;
    private final AddGoods addGoods;

    public SearchProcess(WebElementsUtil webElementsUtil, List<DtoError> reportList) {
        this.reportList = reportList;
        this.webElementsUtil = webElementsUtil;
        this.cloudWindow = new CloudWindow(webElementsUtil);
        this.searchGoods = new GoodsSearcher(webElementsUtil);
        this.commandSelectSize = new CommandSelectSize(webElementsUtil);
        this.checkPrice = new CheckPrice(webElementsUtil);
        this.addGoods = new AddGoods(webElementsUtil);
    }

    public void executeProcess(StructureCSV goods) throws InterruptedException {

        String goodsName = goods.getName();
        String goodsSize = goods.getArticular();
        int goodsItem = goods.getItem();

        cloudWindow.closeModalWindow();

        // Search goods
        searchGoods.searchGoods(goodsName);

        cloudWindow.closeModalWindow();

        // TODO эта часть выглядит лишней
        // находим несколько имен в поисковике
        By productsLocator = By.className(languageManager.get("alfa812", "products"));
//        By productsLocator = By.cssSelector("ul.products li.product");
        List<WebElement> products = webElementsUtil.getAllProducts(productsLocator);

        // проверяем на наличие товара
//        List<WebElement> product = browserManager.getDriver().findElements(By.className(languageManager.get("alfa812", "product")));
        By productLocator = By.className(languageManager.get("alfa812", "product"));
        List<WebElement> product = webElementsUtil.getAllProducts(productLocator);

        // проверяем на наличие выбора размера
//        List<WebElement> size = browserManager.getDriver().findElements(By.className(languageManager.get("alfa812", "b1c.option")));
        By productSizeLocator = By.className(languageManager.get("alfa812", "b1c.option"));
        List<WebElement> size = webElementsUtil.getAllProducts(productSizeLocator);


        // если товара в поисковике более 1шт
        if (!products.isEmpty()) {
            // работаем с несколькими товарами
            new ManyGoods(products, goods);
            reportList.add(new DtoError(goodsName, goodsSize, languageManager.get("main_messages", "product.size")));

            // если товар есть
        } else if (!product.isEmpty()) {
            // если надо выбрать размер
            if (!size.get(0).getText().isEmpty()) {
                commandSelectSize.selectSize(goods);
                cloudWindow.closeModalWindow();

                if (commandSelectSize.getReportList() != null) {
                    reportList.add(commandSelectSize.getReportList());
                }

            } else {
                if (checkPrice.checkPrice(goods)) {
                    addGoods.addGoods(goodsItem);
                    cloudWindow.closeModalWindow();
                } else {
                    reportList.add(new DtoError(goodsName, goodsSize, checkPrice.getErrorPrice()));
                }
            }
        } else {
            reportList.add(new DtoError(goodsName, goodsSize, languageManager.get("main_messages", "product.out.stock")));
        }
    }

}
