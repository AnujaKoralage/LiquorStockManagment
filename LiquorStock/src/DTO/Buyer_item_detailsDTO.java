package DTO;

public class Buyer_item_detailsDTO extends SuperDTO{
    private int itemID;
    private int buyerGrnID;
    private int qty;

    public Buyer_item_detailsDTO() {
    }

    public Buyer_item_detailsDTO(int itemID, int buyerGrnID, int qty) {
        this.itemID = itemID;
        this.buyerGrnID = buyerGrnID;
        this.qty = qty;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
