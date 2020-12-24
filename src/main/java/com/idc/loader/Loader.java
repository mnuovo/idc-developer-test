package com.idc.loader;

import java.io.FileNotFoundException;
import java.util.List;

public interface Loader<T> {

    List<T> loadCsvToMemory(String csvFileName) throws FileNotFoundException;
}
