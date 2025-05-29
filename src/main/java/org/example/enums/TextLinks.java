package org.example.enums;

import java.io.File;

public enum TextLinks {
    TITLE("   Больше подарков"),
    ADDRESS("https://bolshepodarkov.ru/personal/profile/auth/login.php"), // login
    BASKET_ADDRESS("https://bolshepodarkov.ru/personal/basket/"),  // field open basket.
    LOGIN_FIELD("USER_LOGIN"),  // name email login
    PASSWORD_FIELD("USER_PASSWORD"),  // name password login
    BUTTON_ENTER("Login"),  // name button enter account
    IS_ENTER_ACCOUNT("header-main__wrappersonal-container-personal-name"),// is entered account
    SEARCH_FIELD("header-navigation__wrapsearch-container-form-input"),  // field search
    BUTTON_SEARCH("header-navigation__wrapsearch-container-form-button"),  // button field "search"
    CART_FIELD("catalog-section-item-wrapper"),
    TO_BASKET("calalog-quantity"),  // button field "add to basket or subscribe"
    PRICE("catalog-section-item-price-discount"),
    MIN_ITEM("intec-ui-part-input"),  // field to minimum to order
    ADD_ITEM("calalog-quantity-container-input"),  // field add item to basket
    BASKET_ITEM("basket-item"),  // field basket items
    CLEAR_BASKET("basket-items-panel-buttons"),// clear Basket
    SUBSCRIBE("Подписаться"),
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
    FILE_NAME_SAVE("BolshePodarkov_Report"),
    AUTHORIZATION("Documents"+ File.separator +"AuthBolshePodarkov.txt"), // file storage name with login
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
