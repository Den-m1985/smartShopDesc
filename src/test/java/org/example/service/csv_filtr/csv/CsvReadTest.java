package org.example.service.csv_filtr.csv;

import org.example.service.BasicLanguageManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;

public class CsvReadTest extends BasicLanguageManager {
    String encoding = languageManager.get("main_messages", "encoding.windows");

    @Test
    public void testReadCSV(@TempDir Path tempDir) throws IOException {
        // Создаем временный файл с тестовыми данными
        File testFile = tempDir.resolve("test.csv").toFile();
        try (FileWriter writer = new FileWriter(testFile, Charset.forName(encoding))) {
            writer.write("НОВИНКИ;;;\n");
            writer.write("Название;Размер/Цвет;Цена;Количество\n");
            writer.write("FORZA Кольцо-подставка для смартфона, 6,5x3x0,1см, фэйс;(12-470-011);17;1\n");
            writer.write("INBLOOM  Заборчик декоративный Цветок 10шт, 15x33 см;(44-172-003);87;4\n");
            writer.write("INBLOOM Чехлы-бахилы на обувь силиконовые S, 21x12,5см (+-1см);(27-188-094);55;4\n");
            writer.write("Свисток спортивный на веревочке;(343-069);13;2\n");
        }
        List<String[]> result = new CsvRead().readCSV(testFile.getAbsolutePath(), encoding);
        List<String[]> expected = List.of(
                new String[]{"НОВИНКИ", "", "", ""},
                new String[]{"Название", "Размер/Цвет", "Цена", "Количество"},
                new String[]{"FORZA Кольцо-подставка для смартфона, 6,5x3x0,1см, фэйс", "(12-470-011)", "17", "1"},
                new String[]{"INBLOOM  Заборчик декоративный Цветок 10шт, 15x33 см", "(44-172-003)", "87", "4"},
                new String[]{"INBLOOM Чехлы-бахилы на обувь силиконовые S, 21x12,5см (+-1см)", "(27-188-094)", "55", "4"},
                new String[]{"Свисток спортивный на веревочке", "(343-069)", "13", "2"}
        );

        Assertions.assertEquals(expected.size(), result.size());
        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertArrayEquals(expected.get(i), result.get(i));
        }
    }

    @Test
    public void testReadCSV2EmptyFile(@TempDir Path tempDir) throws IOException {
        // Создаем пустой временный файл
        File emptyFile = tempDir.resolve("empty.csv").toFile();
        emptyFile.createNewFile();
        List<String[]> result = new CsvRead().readCSV(emptyFile.getAbsolutePath(), encoding);
        List<String[]> expected = List.of();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testReadCSV2FileNotFound() {
        CsvRead readCSV2 = new CsvRead();
        String nonExistentFile = "non_existent_file.csv";

        Assertions.assertThrows(RuntimeException.class, () -> {
            readCSV2.readCSV(nonExistentFile, encoding);
        });
    }

    @Test
    public void testReadCSV2EmptyLines(@TempDir Path tempDir) throws IOException {
        File emptyLinesFile = tempDir.resolve("empty_lines.csv").toFile();
        try (FileWriter writer = new FileWriter(emptyLinesFile, Charset.forName(encoding))) {
            writer.write("\n\n\n");
        }

        List<String[]> result = new CsvRead().readCSV(emptyLinesFile.getAbsolutePath(), encoding);

        // Ожидаемый результат - список с пустыми массивами
        List<String[]> expected = List.of(new String[]{""}, new String[]{""}, new String[]{""});

        // Проверяем, что результат соответствует ожидаемому
        Assertions.assertEquals(expected.size(), result.size());
        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertArrayEquals(expected.get(i), result.get(i));
        }
    }

    @Test
    public void testReadCSV2NoDelimiters(@TempDir Path tempDir) throws IOException {
        File noDelimitersFile = tempDir.resolve("no_delimiters.csv").toFile();
        try (FileWriter writer = new FileWriter(noDelimitersFile, Charset.forName(encoding))) {
            writer.write("НОВИНКИ\n");
            writer.write("НазваниеРазмер/ЦветЦенаКоличество\n");
        }

        List<String[]> result = new CsvRead().readCSV(noDelimitersFile.getAbsolutePath(), encoding);
        List<String[]> expected = List.of(
                new String[]{"НОВИНКИ"},
                new String[]{"НазваниеРазмер/ЦветЦенаКоличество"}
        );

        Assertions.assertEquals(expected.size(), result.size());
        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertArrayEquals(expected.get(i), result.get(i));
        }
    }

}

