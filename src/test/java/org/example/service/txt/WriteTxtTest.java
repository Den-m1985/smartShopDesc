package org.example.service.txt;

import org.example.enums.NameProducts;
import org.example.service.file_work.txt.WriteTxt;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WriteTxtTest {

    @Test
    public void testConvertListToStringWithEmptyList() {
        WriteTxt writeTxt = new WriteTxt();
        List<String> data = List.of();

        String result = writeTxt.convertListToString(data);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void testConvertListToStringWithSingleElement() {
        WriteTxt writeTxt = new WriteTxt();
        List<String> data = new ArrayList<>();
        data.add(NameProducts.BOLSHE_PODARKOV.name() + ";encryptedLogin" + ";encryptedPassword" + ";secretKey");

        String result = writeTxt.convertListToString(data);
        String expected = "BOLSHE_PODARKOV;encryptedLogin;encryptedPassword;secretKey" + System.lineSeparator();
        assertEquals(expected, result);
    }

    @Test
    public void testConvertListToStringWithTwoElement() {
        WriteTxt writeTxt = new WriteTxt();
        List<String> data = new ArrayList<>();
        data.add(NameProducts.BOLSHE_PODARKOV.name() + ";encryptedLogin" + ";encryptedPassword" + ";secretKey");
        data.add(NameProducts.ALFA_812.name() + ";encryptedLogin" + ";encryptedPassword" + ";secretKey");
        String result = writeTxt.convertListToString(data);

        String expected = "BOLSHE_PODARKOV;encryptedLogin;encryptedPassword;secretKey" +
                System.lineSeparator() +
                "ALFA_812;encryptedLogin;encryptedPassword;secretKey" +
                System.lineSeparator();
        assertEquals(expected, result);
    }

    @Test
    public void testConvertListToStringWithThreeElement() {
        WriteTxt writeTxt = new WriteTxt();
        List<String> data = new ArrayList<>();
        data.add(NameProducts.BOLSHE_PODARKOV.name() + ";encryptedLogin" + ";encryptedPassword" + ";secretKey");
        data.add(NameProducts.ALFA_812.name() + ";encryptedLogin" + ";encryptedPassword" + ";secretKey");
        data.add(NameProducts.COMPARE_2_FILES.name() + ";encryptedLogin" + ";encryptedPassword" + ";secretKey");
        String result = writeTxt.convertListToString(data);

        String expected = "BOLSHE_PODARKOV;encryptedLogin;encryptedPassword;secretKey" +
                System.lineSeparator() +
                "ALFA_812;encryptedLogin;encryptedPassword;secretKey" +
                System.lineSeparator() +
                "COMPARE_2_FILES;encryptedLogin;encryptedPassword;secretKey" +
                System.lineSeparator();
        assertEquals(expected, result);
    }

}
