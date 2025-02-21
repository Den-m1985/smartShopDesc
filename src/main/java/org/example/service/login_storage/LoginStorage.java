package org.example.service.login_storage;

import org.example.enums.NameProducts;
import org.example.service.BasicLanguageManager;
import org.example.service.txt.ReadTxtFile;
import org.example.service.txt.WriteTxt;

import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.security.Key;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.example.config.ConfigLoader.getAESCode;

public class LoginStorage extends BasicLanguageManager {
    private final String ALGORITHM = "AES";
    private final String filePath;
    private final int byteLength = 16;
    private final NameProducts product;
    private final ReadTxtFile readTxtFile;

    /**
     * для работы с файлами и шифрованием данных описаны в документации Oracle:
     * - Описание класса `FileInputStream`: <a href="https://docs.oracle.com/javase/8/docs/api/java/io/FileInputStream.html">...</a>
     * - Описание класса `FileOutputStream`: <a href="https://docs.oracle.com/javase/8/docs/api/java/io/FileOutputStream.html">...</a>
     * - Описание класса `Cipher`: <a href="https://docs.oracle.com/javase/8/docs/api/javax/crypto/Cipher.html">...</a>
     * - Описание класса `SecretKeySpec`: <a href="https://docs.oracle.com/javase/8/docs/api/javax/crypto/spec/SecretKeySpec.html">...</a>
     * - Описание класса `IvParameterSpec`: <a href="https://docs.oracle.com/javase/8/docs/api/javax/crypto/spec/IvParameterSpec.html">...</a>
     * Документация по JCE (Java Cryptography Extension),
     * который используется для шифрования и дешифрования данных:
     * <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/security/crypto/CryptoSpec.html">...</a>
     */

    public LoginStorage(NameProducts product) {
        readTxtFile = new ReadTxtFile();
        this.product = product;
        this.filePath = System.getProperty(
                languageManager.get("main_messages", "default.user.home")) + File.separator +
                languageManager.get("main_messages", "default.documents") + File.separator +
                languageManager.get("main_messages", "file.login");
    }


    public void saveToFile(String login, String password) throws Exception {
        String secretKey = createSecretKey();
        Key key = byteKey(secretKey);
        LoginCrypt storage = new LoginCrypt();
        String encryptedLogin = storage.encrypt(login, key, ALGORITHM);
        String encryptedPassword = storage.encrypt(password, key, ALGORITHM);

        List<String> lines = readTxtFile.readTxtFile(filePath);
        lines.removeIf(line -> line.startsWith(product.name() + ";"));
        String dataToWrite = product.name() + ";" + encryptedLogin + ";" + encryptedPassword + ";" + secretKey;
        lines.add(dataToWrite);

        new WriteTxt(filePath, lines);
    }

    public String[] readFromFile() throws Exception {
        List<String> lines = readTxtFile.readTxtFile(filePath);
        for (String line : lines) {
            String[] parts = line.split(";");
            if (parts[0].equals(product.name())) {
                String encryptedLogin = parts[1];
                String encryptedPassword = parts[2];
                String secretKey = parts[3];

                Key key = byteKey(secretKey);
                LoginCrypt storage = new LoginCrypt();
                String decryptedLogin = storage.decrypt(encryptedLogin, key, ALGORITHM);
                String decryptedPassword = storage.decrypt(encryptedPassword, key, ALGORITHM);

                return new String[]{decryptedLogin, decryptedPassword};
            }
        }
        return null;
    }

    public Key byteKey(String secretKey) {
        //Длина ключа для AES должна составлять 16, 24 или 32 байт
        byte[] keyBytes = secretKey.getBytes();
        byte[] newKeyBytes = Arrays.copyOf(keyBytes, byteLength);
        return new SecretKeySpec(newKeyBytes, ALGORITHM);
    }

    private String createSecretKey() {
        String characters = getAESCode();
        StringBuilder sb = new StringBuilder(24);
        Random random = new Random();
        for (int i = 0; i < 23; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        sb.append("=");
        return sb.toString();
    }

}
