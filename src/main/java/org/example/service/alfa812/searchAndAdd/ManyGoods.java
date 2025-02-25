package org.example.service.alfa812.searchAndAdd;

import org.example.service.csv_filter.csv.StructureCSV;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ManyGoods {

    public ManyGoods(List<WebElement> products, StructureCSV goods) {
        /*
        Не удается скикнуть по товару если их более 1шт. Найти и сравнить название получается.
         */

//        List<WebElement> products2 = driver.findElements(By.className("product_info"));  // product_info
//        System.out.println("products2.size()------" + products2.size());
//        for (WebElement y:products2) {
//
//            String textFromProduct = y.getText();
//            String res = new AnalyzeText().splitNameArticuleSize(goodsName, textFromProduct);  // работает
//            //System.out.println("y.getText()-----" + res);
//            //System.out.println("textFromProduct---" + textFromProduct);
//            if (res.equals(goodsName)){
//                //System.out.println("good");
//
//                String lin = "title=";
//                y.findElement(By.className(lin)).click();
//                y.findElement(By.name(goodsName)).click();
//                y.findElement(By.linkText(lin)).click();
//                y.click();
//            }
//        }



        // Находим все карточки товаров на странице
//        WebElement productCard = null;
//        for (WebElement card : driver.findElements(By.xpath("//div[@class='product-card']"))) {
//            if (card.findElement(By.xpath(".//div[@class='product-title']"))
//                    .getText().equals(goodsName)) {
//                productCard = card;
//                break;
//            }
//        }
//
//        // Если карточка была найдена, кликаем на нее
//        if (productCard != null) {
//            productCard.click();
//        }

        /*
        // Находим нужный товар
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//div[@class='product-card']//div[@class='product-title' and text()='" + goodsName + "']")));
        // Кликаем на товар для открытия страницы продукта
        product.click();
        */



    }
}
