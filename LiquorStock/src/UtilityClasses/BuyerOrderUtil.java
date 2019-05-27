package UtilityClasses;

public class BuyerOrderUtil {
    private int liquorID;
    private String liquorName;
    private int qty;
    private double price;

    public BuyerOrderUtil() {
    }

    public BuyerOrderUtil(int liquorID, String liquorName, int qty, double price) {
        this.liquorID = liquorID;
        this.liquorName = liquorName;
        this.qty = qty;
        this.price = price;
    }

    public int getLiquorID() {
        return liquorID;
    }

    public void setLiquorID(int liquorID) {
        this.liquorID = liquorID;
    }

    public String getLiquorName() {
        return liquorName;
    }

    public void setLiquorName(String liquorName) {
        this.liquorName = liquorName;
    }


    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
