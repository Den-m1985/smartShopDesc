package org.example.enums;

public enum TextLinksSadovod {
    EMAIL("kashleva88@mail.ru"),
    ADDRESS("https://catalog-sadovod.ru/login"),
    BASKET_ADDRESS("https://catalog-sadovod.ru/cart/"),
    LOGIN_BUTTON_BY_EMAIL("li.ty-account-info__item.ty-dropdown-box__item.ty-account-info__name"),
    LOGIN_FIELD("login_email_main_login"),
    SEARCH_FIELD("search_input"),
    SEARCH_FIELD_POPUP("rf_popup-search-input"),
    COLOR_OPTION("Цвет"),
    SIZE_OPTION("Размер"),
    EMAIL_LOCATOR("ul.ty-tabs__list a.ty-tabs__a"),
    BY_EMAIL("По почте"),
    COUNT_PRODUCT_IN_CART("ty-minicart-count"),
    CLEAR_BASKET("a.ty-btn__tertiary[href*='checkout.clear']"),
    PRICE_LOCATOR("ty-price-num"),
    ALL_CARTS_LOCATOR("ty-column4"),
    COUNT_CARTS_LOCATOR("ut2-gl__item"),
    PRODUCT_PREVIEW_LOCATOR(".ut2-gl__item"),
    FILE_NAME_SAVE("Sadovod_Report");

    private final String string;

    TextLinksSadovod(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
