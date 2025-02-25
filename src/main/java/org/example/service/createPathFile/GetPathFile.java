package org.example.service.createPathFile;

import org.example.service.BasicLanguageManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class GetPathFile extends BasicLanguageManager {

    public String getPathFile(String[] extension) {

        JFileChooser fileOpen = new JFileChooser();

        fileOpen.setPreferredSize(new Dimension(600, 500)); // window size
        FileNameExtensionFilter filter = new FileNameExtensionFilter("files " + extension[0], extension);
        fileOpen.setFileFilter(filter);

        String defaultFolder = languageManager.get("main_messages", "default.open");
        String userHome = languageManager.get("main_messages", "default.user.home");

        // open directory by default.
        fileOpen.setCurrentDirectory(new File(System.getProperty(userHome) + File.separator + defaultFolder));
        int ret = fileOpen.showDialog(null, languageManager.get("main_messages", "file.open"));
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileOpen.getSelectedFile();
            return file.getAbsolutePath();
        }
        return null;
    }

}
