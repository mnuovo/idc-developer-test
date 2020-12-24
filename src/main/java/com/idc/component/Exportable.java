package com.idc.component;

/**
 * Interface for exporting object in different formats
 */
public interface Exportable {

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
