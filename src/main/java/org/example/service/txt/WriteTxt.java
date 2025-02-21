package org.example.service.txt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteTxt {
    public WriteTxt() {
    }

    public WriteTxt(String path) {
        fileWriter(path, "");
    }

    public WriteTxt(String path, List<String> data) {
        fileWriter(path, convertListToString(data));
    }

    private void fileWriter(String filePath, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String convertListToString(List<String> data){
        StringBuilder sb = new StringBuilder();
        for (String str: data){
            sb.append(str).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
