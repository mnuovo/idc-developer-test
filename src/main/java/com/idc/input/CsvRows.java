package com.idc.input;

import com.opencsv.bean.CsvBindByName;

/**
 * Class representing the data from the CSV. OpenCsv is used to bind attribute by Name
 */
public class CsvRows {
    @CsvBindByName
    private String country;

    @CsvBindByName
    private String timescale;

    @CsvBindByName
    private String vendor;

    @CsvBindByName
    private Double units;

    public String getCountry() {
        return country;
    }

    public String getTimescale() {
        return timescale;
    }

    public String getVendor() {
        return vendor;
    }

    public Double getUnits() {
        return units;
    }

    @Override
    public String toString() {
        return "Data{" +
                "country='" + country + '\'' +
                ", timescale='" + timescale + '\'' +
                ", vendor='" + vendor + '\'' +
                ", units=" + units +
                '}';
    }
}
