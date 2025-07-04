package org.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigLoader {

    private ConfigLoader() {
        // Приватный конструктор предотвращает создание экземпляров utility-класса
        throw new UnsupportedOperationException("Это utility-класс и не может быть инстанциирован");
    }

    public static String getAESCode() {
        Properties properties = new Properties();
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException("Файл config.properties не найден!");
            }
            properties.load(input);
            return properties.getProperty("AES_CODE");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки конфигурации", e);
        }
    }

}
