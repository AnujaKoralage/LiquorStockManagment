package Entities;

public class Supplier_item_details extends SuperEntity{
    private Supplier_item_detailsPK supplier_item_detailsPK;
    private int qty;


    public Supplier_item_details() {
    }

    public Supplier_item_details(Supplier_item_detailsPK supplier_item_detailsPK, int qty) {
        this.supplier_item_detailsPK = supplier_item_detailsPK;
        this.qty = qty;
    }

    public Supplier_item_detailsPK getSupplier_item_detailsPK() {
        return supplier_item_detailsPK;
    }

    public void setSupplier_item_detailsPK(Supplier_item_detailsPK supplier_item_detailsPK) {
        this.supplier_item_detailsPK = supplier_item_detailsPK;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
