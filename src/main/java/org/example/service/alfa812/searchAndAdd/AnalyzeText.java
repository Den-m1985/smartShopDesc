package org.example.service.alfa812.searchAndAdd;

public class AnalyzeText {


    public String substituteValues(String goodsSize, String webSiteSize) {

        if (goodsSize.equals(webSiteSize)) {
            return goodsSize;
        }

        String goodsSizeTrim = goodsSize.trim();
        if (goodsSizeTrim.equals(webSiteSize.trim())) {
            return webSiteSize.trim();
        }

        // разделяем на слова. если слова одинаковые, но разные символы, то делим на слова и проверяем.
        String[] partsGoods = divideString(goodsSize);
        String[] partsSite = divideString(webSiteSize);
        String partsGoodsUpperCase = upperCase(partsGoods);
        // если есть переход на новую строку, то берем первую строку и работаем с ней
        if (webSiteSize.contains("\n")) {
            String[] str = webSiteSize.split("\n");
            for (String size : str) {
                String temp = size.trim();
                if (goodsSizeTrim.equals(temp)) {
                    return temp;
                }
                String[] webSiteDivided = divideString(temp);
                String webSiteSizeUpperCase = upperCase(webSiteDivided);
                if (webSiteSizeUpperCase.equals(partsGoodsUpperCase)) {
                    return temp;
                }
            }
        }

        int fromSpSize = partsGoods.length;
        int fromSiteSize = partsSite.length;
        // проверяем на совпадение каждого слова
        boolean check;
        check = checkSizeSpAndWebsite(partsGoods, partsSite);
        // если одинаковая длина и равным то возвращаем исходник
        if (fromSpSize == fromSiteSize && check) {
            return webSiteSize;
        }

        // здесь можно переделать: поднять/опустить регист во всех словах, вернуть оригинал.
        // если последний символ с сайта СП39 в нижнем регистре, то меняем его на верхний и проверяем с текстом с сайта.
        String str = upperCase(partsSite);
        if (partsGoodsUpperCase.equals(str)) {
            return webSiteSize.trim();
        }
        return goodsSize;
    }


    private String upperCase(String[] str) {
        for (int i = 0; i < str.length; i++) {
            str[i] = str[i].toUpperCase().trim();
        }
        return String.join(" ", str); // собираем в строку
    }

    private String[] divideString(String str) {
        // удаляем пробелы в начале и конце строки
        str = str.trim();
        str = str.replaceAll("[()\\-,./;:]", " "); // заменяем скобки и знаки дефиса и слеша на пробелы
        return str.split("\\s+"); // разбиваем строку на слова по пробелам
    }

    private boolean checkSizeSpAndWebsite(String[] fromSP, String[] fromSite) {
        int fromSpSize = fromSP.length;
        int fromSiteSize = fromSite.length;
        int k = 0;
        if (fromSpSize == fromSiteSize) {
            for (int i = 0; i < fromSpSize; i++) {
                if (fromSP[i].equals(fromSite[i])) {
                    //System.out.println("good");
                    k++;
                }
            }
        }
        if (k == fromSpSize)
            return true;

        return false;
    }

}
