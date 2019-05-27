package UtilityClasses;

public class QuantityOnHand {

    private int itemID;
    private int qty;

    public QuantityOnHand() {
    }

    public QuantityOnHand(int itemID, int qty) {
        this.itemID = itemID;
        this.qty = qty;
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
}
