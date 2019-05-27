package Entities;

public class Buyer_order_confermation extends SuperEntity{
    private int grnID;
    private String confirmation;

    public Buyer_order_confermation() {
    }

    public Buyer_order_confermation(int grnID, String confirmation) {
        this.grnID = grnID;
        this.confirmation = confirmation;
    }

    public int getGrnID() {
        return grnID;
    }

    public void setGrnID(int grnID) {
        this.grnID = grnID;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }
}
