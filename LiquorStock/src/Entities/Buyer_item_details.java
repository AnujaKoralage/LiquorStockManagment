package Entities;

public class Buyer_item_details extends  SuperEntity{
    private Buyer_item_detailsPK buyer_item_detailsPK;
    private int qty;

    public Buyer_item_details() {
    }

    public Buyer_item_details(Buyer_item_detailsPK buyer_item_detailsPK, int qty) {
        this.buyer_item_detailsPK = buyer_item_detailsPK;
        this.qty = qty;
    }

    public Buyer_item_detailsPK getBuyer_item_detailsPK() {
        return buyer_item_detailsPK;
    }

    public void setBuyer_item_detailsPK(Buyer_item_detailsPK buyer_item_detailsPK) {
        this.buyer_item_detailsPK = buyer_item_detailsPK;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
