package Entities;

public class Buyer extends SuperEntity {
    private int BuyerID;
    private String name;
    private String address;

    public Buyer() {
    }

    public Buyer(int buyerID, String name, String address) {
        BuyerID = buyerID;
        this.name = name;
        this.address = address;
    }

    public int getBuyerID() {
        return BuyerID;
    }

    public void setBuyerID(int buyerID) {
        BuyerID = buyerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
