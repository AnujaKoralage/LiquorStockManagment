package UtilityClasses;

public class SupplierUtil {
    private int supplierid;
    private String supplierName;
    private String address;

    public SupplierUtil() {
    }

    public SupplierUtil(int supplierid, String supplierName, String address) {
        this.supplierid = supplierid;
        this.supplierName = supplierName;
        this.address = address;
    }

    public int getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
