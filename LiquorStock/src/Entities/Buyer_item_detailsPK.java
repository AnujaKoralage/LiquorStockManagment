package Entities;

public class Buyer_item_detailsPK {
    private int itemID;
    private int buyerGrnID;

    public Buyer_item_detailsPK() {
    }

    public Buyer_item_detailsPK(int itemID, int buyerGrnID) {
        this.itemID = itemID;
        this.buyerGrnID = buyerGrnID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getBuyerGrnID() {
        return buyerGrnID;
    }

    public void setBuyerGrnID(int buyerGrnID) {
        this.buyerGrnID = buyerGrnID;
    }
}
