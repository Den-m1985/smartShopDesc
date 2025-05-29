package org.example.service.create_path_file;

import org.example.enums.TextLinks;

import java.io.File;

public class CreatePathFile {

    public String createPathFile(String fileName, String extension) {
        String date = new Date().currentDate();

        String joinFileName = System.getProperty(TextLinks.USER_HOME.getString())
                + File.separator
                + TextLinks.SAVE_FILE_PATH.getString()
                + File.separator
                + fileName
                + "_"
                + date
                + "."
                + extension;
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
