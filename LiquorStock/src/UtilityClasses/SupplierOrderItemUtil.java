package UtilityClasses;

public class SupplierOrderItemUtil {

    private int liquorID;
    private String name;
    private int qty;
    private double price;

    public SupplierOrderItemUtil() {
    }

    public SupplierOrderItemUtil(int liquorID, String name, int qty, double price) {
        this.liquorID = liquorID;
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public int getLiquorID() {
        return liquorID;
    }

    public void setLiquorID(int liquorID) {
        this.liquorID = liquorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
