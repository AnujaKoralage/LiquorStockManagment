package DTO;

public class ItemDTO extends SuperDTO{
    private int itemID;
    private int qty;
    private int supID;
    private String name;

    public ItemDTO() {
    }

    public ItemDTO(int itemID, int qty, int supID, String name) {
        this.itemID = itemID;
        this.qty = qty;
        this.supID = supID;
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

    public int getSupID() {
        return supID;
    }

    public void setSupID(int supID) {
        this.supID = supID;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
