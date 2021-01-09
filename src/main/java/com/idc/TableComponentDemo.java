package com.idc;

import com.idc.component.Row;
import com.idc.component.Table;
import com.idc.enumeration.Quarter;
import com.idc.enumeration.Sorter;
import com.idc.input.CsvRows;
import com.idc.loader.CsvMemoryLoader;
import com.idc.loader.Loader;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Demo class to demonstrate the functionalities implemented.
 */
public class TableComponentDemo {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("** a) Load the input file and transform it into an object **");

        Loader<CsvRows> myLoader = new CsvMemoryLoader();
        final List<CsvRows> rowsList = myLoader.loadCsvToMemory("src/main/resources/data.csv");
        Table table = new Table(rowsList, Quarter.Q4_10);
        table.printTable();


        System.out.println(" ** b) Ascertain the units and share values for a given vendor **");

        List<String> vendors = Arrays.asList("Acer", "Dell", "NotThere");
        final double totalUnits = table.getRows().stream().mapToDouble(Row::getUnits).sum();

        vendors.forEach(s -> {
            final Optional<Row> vendorRow = table.getVendorRow(s);

            if (vendorRow.isPresent()){
                String vendor = vendorRow.get().getVendor();
                double units = vendorRow.get().getUnits();
                double share = vendorRow.get().getShare(totalUnits);

                System.out.printf("%s sold %,.0f units during the given quarter. Percentage share is %.1f%n", vendor, units, share);

            }else {
                System.out.println(s + " was not found in the table");
            }
        });


        //c)Ascertain which row contains information about a given vendor
        System.out.println(" ** c) Ascertain which row contains information about a given vendor **");

        vendors.forEach(s -> {

            int elementPosition = table.indexOfVendor(s);

            if (elementPosition > 0){
                System.out.printf("%s is at position %d%n", table.getVendorRow(s).get().getVendor(), elementPosition);
            }else {
                System.out.println(s + "was not found in the table");
            }

        });

        //d)Sort the rows alphabetically (by vendor).
        System.out.println(" ** d) Sort the rows alphabetically (by vendor) **");
        table.sortRowsBy(Sorter.SORT_BY_VENDOR);
        table.printTable();

        //e)Sort the rows by unit values
        System.out.println(" ** e) Sort the rows by unit values **");
        table.sortRowsBy(Sorter.SORT_BY_UNITS);
        table.printTable();

        //f)Export the object structure to HTML
        System.out.println(" ** f) Export the object structure to HTML **");
        table.exportToHtml();

        //g)Export to additional formats (Excel and CSV).
        System.out.println(" ** g) Export to additional formats (Excel and CSV) **");
        table.exportToCsv();
        table.exportToExcel();

    }
}
