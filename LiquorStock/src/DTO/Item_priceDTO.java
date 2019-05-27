package DTO;

public class Item_priceDTO extends SuperDTO{
    private int itemID;
    private double buyingPrice;
    private double sellingPrice;

    public Item_priceDTO() {
    }

    public Item_priceDTO(int itemID, double buyingPrice, double sellingPrice) {
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
