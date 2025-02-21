package org.example.service.txt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ReadTxtFileTest {

    @Test
    public void testReadTxtFile(@TempDir Path tempDir) throws IOException {
        // Создаем временный файл с тестовыми данными
        File testFile = tempDir.resolve("test.txt").toFile();
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("BOLSHE_PODARKOV;encryptedLogin;encryptedPassword;secretKey1\n");
            writer.write("ALFA_812;encryptedLogin;encryptedPassword;secretKey2354\n");
            writer.write("COMPARE_2_FILES;encryptedLogin;encryptedPassword;secretKey3684\n");
        }

        ReadTxtFile readTxtFile = new ReadTxtFile();
        List<String> result = readTxtFile.readTxtFile(testFile.getAbsolutePath());

        List<String> expected = List.of(
                "BOLSHE_PODARKOV;encryptedLogin;encryptedPassword;secretKey1",
                "ALFA_812;encryptedLogin;encryptedPassword;secretKey2354",
                "COMPARE_2_FILES;encryptedLogin;encryptedPassword;secretKey3684"
        );

        Assertions.assertEquals(expected, result);
    }

}
