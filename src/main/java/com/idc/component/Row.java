package com.idc.component;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Class representing a Row from the Table object
 */
public class Row {
    private String vendor;
    private Double units;
    private Double share;

    /**
     * Constructor of the class by setting all the parameters
     * @param vendor String representing a vendor name
     * @param units Double representing number of units
     * @param share Double representing percentage of units sold compared to the total units
     */
    public Row(String vendor, Double units, Double share){
        this.vendor = vendor;
        this.units = units;
        this.share = share;
    }

    /**
     * Construct a Row by the Vendor name. Other attribute are set to 0.0 as default.
     * @param vendor String representing the vendor name
     */
    public Row(String vendor){
        this.vendor = vendor;
        this.units = 0.0;
        this.share = 0.0;
    }

    /**
     * Return a String representing a vendor name
     * @return String
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * Set the name of the vendor
     * @param vendor String
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     * Return a Double value representing the amount of units sold
     * @return Double
     */
    public Double getUnits() {
        return units;
    }

    /**
     * Set the amount of units sold
     * @param units Double
     */
    public void setUnits(Double units) {
        this.units = units;
    }

    /**
     * Return a Double representing the percentage of unit sold compared to the total units
     * @return Double
     */
    public Double getShare() {
        return share;
    }

    /**
     * Set the percentage of unit sold compared to the total units
     * @param share Double
     */
    public void setShare(Double share) {
        this.share = share;
    }

    @Override
    public String toString() {
        return "Row{" +
                "vendor='" + vendor + '\'' +
                ", units=" + new DecimalFormat("#.##").format(units) +
                ", share=" + new DecimalFormat("#.##").format(share) +
                '}';
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
        return Objects.hash(vendor, units, share);
    }
}
