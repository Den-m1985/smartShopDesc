package org.example.service.login_storage;

import org.example.enums.NameProducts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginStorageTest {
    private LoginStorage loginStorage;
    private static final String TEST_LOGIN = "testUser";
    private static final String TEST_PASSWORD = "securePassword123";

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        String testFilePath = tempDir.resolve("test_logins.txt").toString();
        loginStorage = new LoginStorage(NameProducts.SADOVOD, testFilePath);
    }

    @Test
    void saveAndReadCredentials_ShouldWorkCorrectly() throws Exception {
        // Сохраняем тестовые данные
        loginStorage.saveToFile(TEST_LOGIN, TEST_PASSWORD);

        // Читаем сохраненные данные
        String[] credentials = loginStorage.readFromFile();

        // Проверяем результаты
        assertNotNull(credentials);
        assertEquals(2, credentials.length);
        assertEquals(TEST_LOGIN, credentials[0]);
        assertEquals(TEST_PASSWORD, credentials[1]);
    }

    @Test
    void readFromFile_WhenNoCredentials_ShouldReturnEmptyArray() throws Exception {
        String[] credentials = loginStorage.readFromFile();
        assertNotNull(credentials);
        assertEquals(0, credentials.length);
    }

    @Test
    void saveToFile_ShouldOverwriteExistingCredentials() throws Exception {
        // Первое сохранение
        loginStorage.saveToFile("oldLogin", "oldPassword");

        // Второе сохранение (перезапись)
        loginStorage.saveToFile(TEST_LOGIN, TEST_PASSWORD);

        // Проверяем что сохранились новые данные
        String[] credentials = loginStorage.readFromFile();
        assertArrayEquals(new String[]{TEST_LOGIN, TEST_PASSWORD}, credentials);
    }

    @Test
    void createKeyFromString_ShouldGenerateValidAESKey() {
        String testKey = "testKey1234567890"; // 16 символов
        var key = loginStorage.createKeyFromString(testKey);

        assertNotNull(key);
        assertEquals("AES", key.getAlgorithm());
        assertEquals(16, key.getEncoded().length); // Проверка длины ключа
    }

    @Test
    void createKeyFromString_ShouldHandleShortKeys() {
        String shortKey = "short";
        var key = loginStorage.createKeyFromString(shortKey);

        assertNotNull(key);
        assertEquals(16, key.getEncoded().length); // Проверка дополнения до 16 байт
    }

    @Test
    void createSecretKey_ShouldGenerateValidKey() {
        String secretKey = loginStorage.createSecretKey();
        assertNotNull(secretKey);
        assertTrue(secretKey.length() >= 16); // Проверка минимальной длины
        assertTrue(secretKey.endsWith("=")); // Проверка окончания
    }

    @Test
    void encryptionShouldBeReversible() throws Exception {
        String secretKey = loginStorage.createSecretKey();
        var key = loginStorage.createKeyFromString(secretKey);
        LoginCrypt storage = new LoginCrypt();

        String encrypted = storage.encrypt(TEST_LOGIN, key, "AES");
        String decrypted = storage.decrypt(encrypted, key, "AES");

        assertEquals(TEST_LOGIN, decrypted);
    }
}
