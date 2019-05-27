package DTO;

import java.util.Date;

public class Buyer_GRNDTO extends SuperDTO{
    private int grnID;
    private int buyerID;
    private String date;

    public Buyer_GRNDTO() {
    }

    public Buyer_GRNDTO(int grnID, int buyerID, String date) {
        this.grnID = grnID;
        this.buyerID = buyerID;
        this.date = date;
    }

    public int getGrnID() {
        return grnID;
    }

    public void setGrnID(int grnID) {
        this.grnID = grnID;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
