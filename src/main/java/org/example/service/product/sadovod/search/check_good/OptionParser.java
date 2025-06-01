package org.example.service.product.sadovod.search.check_good;

import java.util.HashMap;
import java.util.Map;

public class OptionParser {

    public Map<String, String> parseOptionToMap(String optionStr) {
        Map<String, String> result = new HashMap<>();
        String cleaned = removeParentheses(optionStr);
        if (cleaned.contains("/")) {
            String[] parts = cleaned.split("/");
            result.put("Цвет", parts[0].trim().toLowerCase());
            result.put("Размер", parts[1].trim().toLowerCase());
        } else {
            result.put("Цвет", cleaned.toLowerCase());
            result.put("Размер", cleaned.toLowerCase());
        }
        return result;
    }

    public String removeParentheses(String str){
        return str.replaceAll("\\(.*?\\)", "").trim();
    }
}
