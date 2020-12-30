package com.idc.component;

import com.idc.enumeration.Quarter;
import com.idc.enumeration.Sorter;
import com.idc.export.IExportable;
import com.idc.input.CsvRows;
import com.idc.sort.ISortable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class representing a Table object containing the information elaborated from the data.csv file
 */
public class Table implements IExportable, ISortable {

    private final List<Row> rows = new ArrayList<>();
    private Double totalUnits = 0.0;


    /**
     * Constructor of the table object
     * @param rowsList list of CsvRows objects loaded from the csv file
     * @param quarter Enum representing the specific quarter to filter data
     */
    public Table(List<CsvRows> rowsList, Quarter quarter){

        Map<String, Double> dataMap = new LinkedHashMap<>();

        //Group the values by Vendor and Units
        rowsList.stream()
                .filter(csvRows -> csvRows.getTimescale().equals(quarter.getQuarter()))
                .collect(Collectors.groupingBy(CsvRows::getVendor, Collectors.summarizingDouble(CsvRows::getUnits)))
                .forEach((s, doubleSummaryStatistics) -> {
                    dataMap.put(s, doubleSummaryStatistics.getSum());
                    totalUnits += doubleSummaryStatistics.getSum();
                });

        //Create a list of Row representing the table
        for (Map.Entry<String, Double> entry : dataMap.entrySet()){

            String vendor = entry.getKey();
            Double units = entry.getValue();
            Double share = (entry.getValue()*100)/totalUnits;

            Row row = new Row(vendor, units, share);

            rows.add(row);
        }
    }

    @Override
    public void exportToHtml() {
        System.out.println("Export to HTML. To be implemented....");
    }

    @Override
    public void exportToCsv() {
        System.out.println("Export to CSV. To be implemented....");
    }

    @Override
    public void exportToExcel() {
        System.out.println("Export to Excel. To be implemented....");
    }

    /**
     * Ascertain which row contains information about a given vendor
     * @param vendor String representing the vendor name
     * @return the index of the first occurrence of the vendor in the table or -1 if the vendor does not occur.
     */
    public int indexOfVendor(String vendor){
        return (!rows.contains(new Row(vendor))) ? -1 : rows.indexOf(new Row(vendor))+1;
    }

    /**
     * Print the content of the table on console. Used for easy data visualization purposes.
     */
    public void printTable(){
        String line = "-----------------------------------------------------------------------------";
        System.out.println(line);
        System.out.printf("%20s %10s %10s", "VENDOR", "UNITS", "SHARE");
        System.out.println();
        System.out.println(line);

        rows.forEach(row -> {
            System.out.format("%20s %,10.0f %10.1f", row.getVendor(), row.getUnits(), row.getShare());
            System.out.println();
        });

        System.out.println(line);
        System.out.format("%20s %,10.0f %10.0f", "Total", totalUnits, 100.0);
        System.out.println();
        System.out.println(line);
    }

    /**
     * Return the total amount of units for the table
     * @return
     */
    public Double getTotalUnits() {
        return totalUnits;
    }

    /**
     * Return a table row representing a specific vendor
     * @param vendor String representing the vendor name
     * @return a Row object from the table
     */
    public Row getVendorRow(String vendor){
        return rows.stream().filter(row -> row.getVendor().equals(vendor)).findAny().orElse(null);
    }

    /**
     * Return a List of Row object representing the rows in the table.
     * @return List<Row>
     */
    public List<Row> getRows(){
        return this.rows;
    }

    @Override
    public void sortRowsBy(Sorter sorter) {
        switch (sorter){
            case SORT_BY_VENDOR: rows.sort(Comparator.comparing(Row::getVendor)); break;
            case SORT_BY_UNITS: rows.sort(Comparator.comparing(Row::getUnits)); break;
            default: break;
        }
    }
}
