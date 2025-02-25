package org.example.model;

import org.example.enums.FileExtension;

import java.util.HashMap;
import java.util.Map;

public class FilePathManager {

    private final Map<FileExtension, String> filePathsMap;

    public FilePathManager() {
        filePathsMap = new HashMap<>();
    }

    public void addFilePath(String filePath) {
        FileExtension extension = getFileExtension(filePath);
        if (extension != null) {
            filePathsMap.put(extension, filePath);
        }
    }

    public String getFilePaths(FileExtension extension) {
        return filePathsMap.get(extension);
    }

    private FileExtension getFileExtension(String filePath) {
        for (FileExtension extension : FileExtension.values()) {
            if (filePath.endsWith(extension.getExtension())) {
                return extension;
            }
        }
        return null;
    }

}
