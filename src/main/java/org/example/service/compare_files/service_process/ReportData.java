package org.example.service.compare_files.service_process;

public class ReportData {
    public String matchPercent;
    public String priceCSV;
    public String itemCSV;
    public String nameCSV;

    public ReportData(String matchPercent, String priceCSV, String itemCSV, String nameCSV) {
        this.matchPercent = matchPercent;
        this.priceCSV = priceCSV;
        this.itemCSV = itemCSV;
        this.nameCSV = nameCSV;
    }

}
