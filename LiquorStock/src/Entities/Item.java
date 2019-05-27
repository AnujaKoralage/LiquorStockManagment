package Entities;

public class Item extends SuperEntity{
    private int itemID;
    private int qty;
    private int supplierID;
    private String name;

    public Item() {
    }

    public Item(int itemID, int qty, int supplierID, String name) {
        this.itemID = itemID;
        this.qty = qty;
        this.supplierID = supplierID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item(int itemID, String name){
        this.itemID = itemID;
        this.name = name;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }
}
