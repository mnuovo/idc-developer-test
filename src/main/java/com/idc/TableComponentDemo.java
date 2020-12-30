package com.idc;

import com.idc.component.Row;
import com.idc.component.Table;
import com.idc.enumeration.Quarter;
import com.idc.enumeration.Sorter;
import com.idc.input.CsvRows;
import com.idc.loader.CsvMemoryLoader;
import com.idc.loader.Loader;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Demo class to demonstrate the functionalities implemented.
 */
public class TableComponentDemo {
    private static final String VENDOR_AT_POSITION = "%s is at position %d";

    public static void main(String[] args) throws FileNotFoundException {

        //a)Load the input file and transform it into an object
        System.out.println("*************** a) Load the input file and transform it into an object ***************");
        Loader<CsvRows> myLoader = new CsvMemoryLoader();

        final List<CsvRows> rowsList = myLoader.loadCsvToMemory("src/main/resources/data.csv");

        Table table = new Table(rowsList, Quarter.Q4_10);

        table.printTable();

        //b)Ascertain the units and share values for a given vendor
        System.out.println(" *************** b) Ascertain the units and share values for a given vendor ***************");
        Row acer = table.getVendorRow("Acer");
        Row dell = table.getVendorRow("Dell");
        Row notThere = table.getVendorRow("NotThere");

        System.out.printf("%s sold %,.0f units during the given quarter. Percentage share is %.1f%n", acer.getVendor(), acer.getUnits(), acer.getShare());

        System.out.printf("%s sold %,.0f units during the given quarter. Percentage share is %.1f%n", dell.getVendor(), dell.getUnits(), dell.getShare());

        if (notThere == null)
            System.out.println("Not such element");

        //c)Ascertain which row contains information about a given vendor
        System.out.println(" *************** c) Ascertain which row contains information about a given vendor ***************");
        System.out.printf((VENDOR_AT_POSITION) + "%n", table.getVendorRow("Acer").getVendor(), table.indexOfVendor("Acer"));
        System.out.printf((VENDOR_AT_POSITION) + "%n", table.getVendorRow("Dell"), table.indexOfVendor("Dell"));
        System.out.printf((VENDOR_AT_POSITION) + "%n", table.getVendorRow("Pluto"), table.indexOfVendor("Pluto"));

        //d)Sort the rows alphabetically (by vendor).
        System.out.println(" *************** d) Sort the rows alphabetically (by vendor) ***************");
        table.sortRowsBy(Sorter.SORT_BY_VENDOR);
        table.printTable();

        System.out.printf((VENDOR_AT_POSITION) + "%n", table.getVendorRow("Acer"), table.indexOfVendor("Acer"));
        System.out.printf((VENDOR_AT_POSITION) + "%n", table.getVendorRow("Dell"), table.indexOfVendor("Dell"));

        //e)Sort the rows by unit values
        System.out.println(" *************** e) Sort the rows by unit values ***************");
        table.sortRowsBy(Sorter.SORT_BY_UNITS);
        table.printTable();

        System.out.printf((VENDOR_AT_POSITION) + "%n", table.getVendorRow("Acer"), table.indexOfVendor("Acer"));
        System.out.printf((VENDOR_AT_POSITION) + "%n", table.getVendorRow("Dell"), table.indexOfVendor("Dell"));

        //f)Export the object structure to HTML
        System.out.println(" *************** f) Export the object structure to HTML ***************");
        table.exportToHtml();

        //g)Export to additional formats (Excel and CSV).
        System.out.println(" *************** g) Export to additional formats (Excel and CSV) ***************");
        table.exportToCsv();
        table.exportToExcel();

    }
}
