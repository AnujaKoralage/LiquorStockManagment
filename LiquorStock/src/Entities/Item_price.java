package Entities;

public class Item_price extends SuperEntity{
    private int itemID;
    private double buyingPrice;
    private double sellingPrice;

    public Item_price() {
    }

    public Item_price(int itemID, double buyingPrice, double sellingPrice) {
        this.itemID = itemID;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
