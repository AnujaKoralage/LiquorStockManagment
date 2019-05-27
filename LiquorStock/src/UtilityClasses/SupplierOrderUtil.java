package UtilityClasses;

public class SupplierOrderUtil {

    private int orderID;
    private String orderDate;
    private int supplierID;
    private String suplierName;

    public SupplierOrderUtil() {
    }

    public SupplierOrderUtil(int orderID, String orderDate, int supplierID, String suplierName) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.supplierID = supplierID;
        this.suplierName = suplierName;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getSuplierName() {
        return suplierName;
    }

    public void setSuplierName(String suplierName) {
        this.suplierName = suplierName;
    }
}
