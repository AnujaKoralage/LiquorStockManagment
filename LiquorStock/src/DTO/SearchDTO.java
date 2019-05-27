package DTO;

public class SearchDTO {
    private int liquorID;
    private String liquorName;
    private double sellingPrice;
    private double buyingPrince;

    public SearchDTO() {
    }

    public SearchDTO(int liquorID, String liquorName, double sellingPrice, double buyingPrince) {
        this.liquorID = liquorID;
        this.liquorName = liquorName;
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
