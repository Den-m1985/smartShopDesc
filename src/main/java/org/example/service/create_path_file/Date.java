package org.example.service.create_path_file;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {

    public String currentDate() {

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd");

        return currentDate.format(formatter);
    }
}
