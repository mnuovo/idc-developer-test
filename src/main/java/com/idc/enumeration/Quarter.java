package com.idc.enumeration;

/**
 * Enumeration class representing distribution of the data by quarter time frame
 */
public enum Quarter{
    Q1_10("2010 Q1"), Q2_10("2010 Q2"), Q3_10("2010 Q3"), Q4_10("2010 Q4");

    private final String value;

    Quarter(String quarter) {
        this.value = quarter;
    }

    public String getQuarter() {
        return value;
    }
}
