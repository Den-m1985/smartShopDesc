package org.example.service.createPathFile;

import org.example.service.BasicLanguageManager;

import java.io.File;

public class CreatePathFile extends BasicLanguageManager {

    public String createPathFile(String fileName, String extension) {
        String date = new Date().currentDate();

        String defaultFolder = languageManager.get("main_messages", "default.save");
        String userHome = languageManager.get("main_messages", "default.user.home");

        String joinFileName = System.getProperty(userHome) + File.separator + defaultFolder + File.separator + fileName + "_" + date + "." + extension;

        return checkSameName(joinFileName);
    }


    private String checkSameName(String fileName) {
        File file = new File(fileName);
        String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1);

        int count = 1;
        while (file.exists()) {
            fileName = baseName + "(" + count + ")" + "." + extension;
            file = new File(fileName);
            count++;
        }
        return fileName;
    }

}
