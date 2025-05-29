package org.example.service.util;

public class NumberParser {

    public boolean isFigure(String str) {
        if (str == null) {
            return false;
        }
        try {
            stringToInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int stringToInt(String str) {
        if (str.contains(",")) {
            String str2 = str.replace(",", ".");
            return (int) Float.parseFloat(str2);
        }
        return (int) Float.parseFloat(str);
    }
}
