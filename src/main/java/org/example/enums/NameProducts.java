package org.example.enums;

public enum NameProducts {

    BOLSHE_PODARKOV("Больше подарков"),
    ALFA_812("Alfa 812"),
    COMPARE_2_FILES("Сравниваем 2 файла"),
    SADOVOD("Садовод");

    private final String string;

    NameProducts(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
