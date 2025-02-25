package org.example.service.csv_filter.csv;

import org.example.service.BasicLanguageManager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CsvRead extends BasicLanguageManager {

    public List<String[]> readCSV(String fileName, String encoding) {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName), Charset.forName(encoding)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] divideStr = line.split(";");
                rows.add(divideStr);
            }
        } catch (Exception e) {
            throw new RuntimeException(languageManager.get("main_messages", "file.no.find"));
        }
        return rows;
    }

}
