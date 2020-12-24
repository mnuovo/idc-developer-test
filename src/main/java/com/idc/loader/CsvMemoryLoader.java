package com.idc.loader;

import com.idc.input.CsvRows;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * Class representing a memory loader from a Csv file. The csv file is read and transformed into a List of CsvRows
 */
public class CsvMemoryLoader implements Loader<CsvRows> {

    @Override
    public List<CsvRows> loadCsvToMemory(String csvFileName) throws FileNotFoundException {
        return new CsvToBeanBuilder<CsvRows>(new FileReader(csvFileName))
                .withType(CsvRows.class)
                .build()
                .parse();
    }
}
