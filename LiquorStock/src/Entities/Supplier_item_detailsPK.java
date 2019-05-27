package Entities;

public class Supplier_item_detailsPK extends SuperEntity{
    private int itemID;
    private int supGRNid;

    public Supplier_item_detailsPK() {
    }

    public Supplier_item_detailsPK(int itemID, int supGRNid) {
        this.itemID = itemID;
        this.supGRNid = supGRNid;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getSupGRNid() {
        return supGRNid;
    }

    public void setSupGRNid(int supGRNid) {
        this.supGRNid = supGRNid;
    }
}
