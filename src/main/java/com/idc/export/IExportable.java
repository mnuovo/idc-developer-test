package com.idc.export;

/**
 * Interface for exporting object in different formats
 */
public interface IExportable {

    /**
     * Export to HTML
     */
    void exportToHtml();

    /**
     * Export to Csv
     */
    void exportToCsv();

    /**
     * Export to Excel
     */
    void exportToExcel();
}
