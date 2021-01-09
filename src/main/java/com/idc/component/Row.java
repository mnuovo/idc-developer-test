package com.idc.component;

import java.util.Objects;

/**
 * Class representing a Row from the Table object
 */
public class Row {
    private final String vendor;
    private final double units;

    /**
     * Constructor of the class by setting all the parameters
     * @param vendor String representing a vendor name
     * @param units Double representing number of units
     */
    public Row(String vendor, Double units){
        this.vendor = vendor;
        this.units = units;
    }

    /**
     * Construct a Row by the Vendor name. Other attribute are set to 0.0 as default.
     * @param vendor String representing the vendor name
     */
    public Row(String vendor){
        this(vendor, 0.0);
    }

    /**
     * Return a String representing a vendor name
     * @return String
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * Return a Double value representing the amount of units sold
     * @return Double
     */
    public Double getUnits() {
        return units;
    }

    /**
     * Return a Double representing the percentage of unit sold compared to the total units
     * @return Double
     */
    public Double getShare(double totalUnits) {
        return (units*100)/totalUnits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Row row = (Row) o;
        return Objects.equals(vendor, row.vendor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendor, units);
    }
}
