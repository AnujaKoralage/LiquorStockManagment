package UtilityClasses;

public class AllBuyerOrders {
    private int orderID;
    private String date;
    private int buyerID;
    private String buyerName;

    public AllBuyerOrders() {
    }

    public AllBuyerOrders(int orderID, String date, int buyerID, String buyerName) {
        this.orderID = orderID;
        this.date = date;
        this.buyerID = buyerID;
        this.buyerName = buyerName;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
}
