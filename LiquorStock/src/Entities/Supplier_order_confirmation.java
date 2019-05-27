package Entities;

public class Supplier_order_confirmation extends SuperEntity{
    private int grnID;
    private String confirmation;

    public Supplier_order_confirmation() {
    }

    public Supplier_order_confirmation(int grnID, String confirmation) {
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
