package org.example.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class LanguageManager {
    private static LanguageManager instance;
    private final Map<String, ResourceBundle> resourceBundles;

    private LanguageManager() {
        resourceBundles = new HashMap<>();
    }

    public static LanguageManager getInstance() {
        if (instance == null) {
            instance = new LanguageManager();
        }
        return instance;
    }

    public ResourceBundle loadBundle(String baseName) {
        String resourcePath = "language/" + baseName + ".properties";
        if (!resourceBundles.containsKey(baseName)) {
            try (InputStreamReader reader = new InputStreamReader(
                    Objects.requireNonNull(LanguageManager.class.getClassLoader().getResourceAsStream(resourcePath)),
                    StandardCharsets.UTF_8)) {
                ResourceBundle bundle = new PropertyResourceBundle(reader);
                resourceBundles.put(baseName, bundle);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load resource bundle: " + baseName, e);
            }
        }
        return resourceBundles.get(baseName);
    }

    public String get(String baseName, String key) {
        ResourceBundle bundle = loadBundle(baseName);
        return bundle.getString(key);
    }
}
