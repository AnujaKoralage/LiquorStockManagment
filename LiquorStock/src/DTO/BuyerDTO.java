package DTO;

public class BuyerDTO extends SuperDTO{
    private int buyerID;
    private String name;
    private String address;

    public BuyerDTO() {
    }

    public BuyerDTO(int buyerID, String name, String address) {
        this.buyerID = buyerID;
        this.name = name;
        this.address = address;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
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
