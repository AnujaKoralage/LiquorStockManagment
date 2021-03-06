package DTO;

import java.util.Date;

public class Supplier_order_grnDTO extends SuperDTO{
    private int grnID;
    private int supplierID;
    private String date;

    public Supplier_order_grnDTO() {
    }

    public Supplier_order_grnDTO(int grnID, int supplierID, String date) {
        this.grnID = grnID;
        this.supplierID = supplierID;
        this.date = date;
    }

    public int getGrnID() {
        return grnID;
    }

    public void setGrnID(int grnID) {
        this.grnID = grnID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
