package org.example.enums;

public enum FileExtension {
    DOC("doc"),
    DOCX("docx"),
    XLS("xls"),
    XLSX("xlsx"),
    TXT("txt"),
    CSV("csv");

    private final String extension;

    FileExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
