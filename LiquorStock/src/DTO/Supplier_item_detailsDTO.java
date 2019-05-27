package DTO;

public class Supplier_item_detailsDTO extends SuperDTO{
    private int itemID;
    private int supGrnID;
    private int qty;

    public Supplier_item_detailsDTO() {
    }

    public Supplier_item_detailsDTO(int itemID, int supGrnID, int qty) {
        this.itemID = itemID;
        this.supGrnID = supGrnID;
        this.qty = qty;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getSupGrnID() {
        return supGrnID;
    }

    public void setSupGrnID(int supGrnID) {
        this.supGrnID = supGrnID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
