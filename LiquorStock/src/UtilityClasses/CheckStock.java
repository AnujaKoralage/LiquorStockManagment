package UtilityClasses;

public class CheckStock {
    private int liquorID;
    private String liquorName;
    private int qtyOnhand;
    private double sellingPrice;
    private double buyingPrince;

    public CheckStock() {
    }

    public CheckStock(int liquorID, String liquorName, int qtyOnhand, double sellingPrice, double buyingPrince) {
        this.liquorID = liquorID;
        this.liquorName = liquorName;
        this.qtyOnhand = qtyOnhand;
        this.sellingPrice = sellingPrice;
        this.buyingPrince = buyingPrince;
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

    public int getQtyOnhand() {
        return qtyOnhand;
    }

    public void setQtyOnhand(int qtyOnhand) {
        this.qtyOnhand = qtyOnhand;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getBuyingPrince() {
        return buyingPrince;
    }

    public void setBuyingPrince(double buyingPrince) {
        this.buyingPrince = buyingPrince;
    }
}
