package org.example.enums;

public enum TextLinks {

    OPEN_FILE_PATH("Desktop"),  // open the default folder
    SAVE_FILE_PATH("Downloads"),  // save report file
    COUNT_ROWS_CSV("количество товаров в csv: "),
    REPORT_SIZE("Размер отчета: "),
    WRONG_ARTICULAR("Неправильный артикул"),
    COMMON_ERROR("Общая ошибка"),
    MORE_THAN_1_ITEMS("Товаров больше чем 1"),
    SHEET("Sheet1"),
    NO_ARTICLE("Проверить эти товары"),
    PRODUCT_OUT_OF_STOCK("Товара нет в наличии"),
    DOCUMENTS("Documents"),
    FILE_LOGIN("smartShopDesc.txt"),
    TEXT_SAVE_FILE("Файл куда сохраняем ненайденные артикулы:"),
    TEXT_FILE_OPEN("Файл исходник:"),
    TEXT_FILE_OPEN_ERROR("Файл не выбран"),
    BASKET_EMPTY("Корзина пуста"),
    CSV("csv"),
    XLSX("xlsx"),
    TXT("txt"),
    ENCODING("windows-1251"),
    USER_HOME("user.home");

    private final String string;

    TextLinks(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
