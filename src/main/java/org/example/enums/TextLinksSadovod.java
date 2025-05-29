package org.example.enums;

import java.io.File;

public enum TextLinksSadovod {
    TITLE("   Садовод"),
    EMAIL("kashleva88@mail.ru"),
    ADDRESS("https://catalog-sadovod.ru/login"),
    BASKET_ADDRESS("https://catalog-sadovod.ru/cart/"),
    LOGIN_BUTTON_BY_EMAIL("li.ty-account-info__item.ty-dropdown-box__item.ty-account-info__name"),
    LOGIN_FIELD("login_email_main_login"),
    SEARCH_FIELD("search_input"),
    SEARCH_FIELD_POPUP("rf_popup-search-input"),
    COLOR_OPTION("Цвет"),
    SIZE_OPTION("Размер"),
    BUTTON_SEARCH("ty-search-magnifier"),
    GOODS_CART("ty-column4"),


    PASSWORD_FIELD("USER_PASSWORD"),  // name password login
    BUTTON_ENTER("Login"),  // name button enter account
    IS_ENTER_ACCOUNT("header-main__wrappersonal-container-personal-name"),// is entered account
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
    SHEET("Sheet1"),
    NO_ARTICLE("Проверить эти товары"),
    PRODUCT_OUT_OF_STOCK("Товара нет в наличии"),
    FILE_NAME_SAVE("Sadovod_Report"),
    AUTHORIZATION("Documents" + File.separator + "AuthBolshePodarkov.txt"), // file storage name with login
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

    TextLinksSadovod(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
