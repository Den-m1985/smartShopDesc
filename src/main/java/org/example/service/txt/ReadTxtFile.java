package org.example.service.txt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadTxtFile {

    public List<String> readTxtFile(String filePath) {
        List<String> array = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                array.add(line);
            }
        } catch (Exception e) {
            new WriteTxt(filePath);
            return readTxtFile(filePath);
        }
        return array;
    }

}
