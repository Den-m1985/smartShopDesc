package org.example.service.sadovod.search.check_good;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionParserTest {
    OptionParser optionParser;

    @BeforeEach
    void init() {
        optionParser = new OptionParser();
    }


    @Test
    void testParseOptionWithColorAndSize() {
        String input = "БЕЗ ВЫБОРА/37 (0 руб.)";
        Map<String, String> result = optionParser.parseOptionToMap(input);

        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals("без выбора", result.get("Цвет")),
                () -> assertEquals("37", result.get("Размер"))
        );
    }

    @Test
    void testParseOptionWithOnlySize() {
        String input = "52 (0 руб.)";
        Map<String, String> result = optionParser.parseOptionToMap(input);

        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals("52", result.get("Цвет")),
                () -> assertEquals("52", result.get("Размер"))
        );
    }

    @Test
    void testParseOptionWithSpecialCharacter() {
        String input = "*";
        Map<String, String> result = optionParser.parseOptionToMap(input);

        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals("*", result.get("Цвет")),
                () -> assertEquals("*", result.get("Размер"))
        );
    }

    @Test
    void testParseOptionWithColorAndSizeNoSpaces() {
        String input = "ЧЕРНЫЙ/44(0 руб.)";
        Map<String, String> result = optionParser.parseOptionToMap(input);

        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals("черный", result.get("Цвет")),
                () -> assertEquals("44", result.get("Размер"))
        );
    }

    @Test
    void testParseOptionWithEmptyString() {
        String input = "";
        Map<String, String> result = optionParser.parseOptionToMap(input);

        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals("", result.get("Цвет")),
                () -> assertEquals("", result.get("Размер"))
        );
    }

}
