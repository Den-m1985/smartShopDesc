package org.example.service.createPathFile;

import org.example.controller.TabController;
import org.example.enums.FileExtension;
import org.example.service.BasicLanguageManager;

public class FileChooserManager extends BasicLanguageManager {

    public FileChooserManager(TabController tabController) {
        for (FileExtension[] fileExtension: tabController.getExtension()){
            String[] array = new String[fileExtension.length];
            for (int i = 0; i < fileExtension.length; i++) {
                array[i] = fileExtension[i].getExtension();
            }
            String pathFile = new GetPathFile().getPathFile(array);
            if (pathFile != null) {
                tabController.getView().appendToTextArea("\n" + languageManager.get("main_messages", "file.source"));
                tabController.getView().appendToTextArea("\n" + pathFile);
                tabController.getFilePathManager().addFilePath(pathFile);
            } else {
                tabController.getView().appendToTextArea("\n" + languageManager.get("main_messages", "file.open.error"));
                throw new RuntimeException();
            }
        }
    }

}
