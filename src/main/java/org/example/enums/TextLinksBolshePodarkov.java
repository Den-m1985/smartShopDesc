package org.example.enums;

public enum TextLinksBolshePodarkov {
    ADDRESS("https://bolshepodarkov.ru/personal/profile/auth/login.php"), // login
    BASKET_ADDRESS("https://bolshepodarkov.ru/personal/basket/"),  // field open basket.
    LOGIN_FIELD("input[name='USER_LOGIN'][type='email']"),
    PASSWORD_FIELD("input[name='USER_PASSWORD'][type='password']"),
    BUTTON_ENTER("input[name='Login'][type='submit']"),
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
    PRODUCT_OUT_OF_STOCK("Товара нет в наличии"),
    FILE_NAME_SAVE("BolshePodarkov_Report"),
    BASKET_EMPTY("Корзина пуста");


    private final String string;

    TextLinksBolshePodarkov(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
