package BO;

import BO.custom.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public <T extends BO> T getBO(BOTypes boTypes){
        switch (boTypes){
            case SUPPLIER:
                return (T)new SupplierBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case BUYER:
                return (T) new BuyerBOImpl();
            case SUPPLIERITEMDETAILS:
                return (T) new Supplier_item_detailsBOImpl();
            case SUPPLIERORDERDRN:
                return (T) new Supplier_order_grnBOImpl();
            case ITEMPRICE:
                return (T) new Item_priceBOImpl();
            case BUYERGRN:
                return (T) new Buyer_grnBOImpl();
            case BUYERGRNITEMS:
                return (T) new Buyer_item_detailsBOImpl();
                default:
                    return null;
        }
    }

    public static BOFactory getInstance(){
        if (boFactory == null)
            boFactory = new BOFactory();
        return boFactory;
    }
}
