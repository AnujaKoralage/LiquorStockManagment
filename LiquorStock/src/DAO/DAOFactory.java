package DAO;

import DAO.DAO.custom.DAO.custom.Impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public <T extends CRUDDAO> T getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case ITEM:
                return (T)new ItemImpl();
            case SUPPLIER:
                return (T) new SupplierImpl();
            case BUYER:
                return (T) new BuyerImpl();
            case SUPPLIERORDERGRN:
                return (T) new Supplier_order_grnImpl();
            case SUPPLIERITEMDETAILS:
                return (T) new Supplier_item_detailsImpl();
            case ITEMPRICE:
                return (T) new Item_priceDAOImpl();
            case BUYERGRN:
                return (T) new Buyer_grnImpl();
            case BUYERITEMDETAILS:
                return (T) new Buyer_item_detailsIML();
                default:
                    return null;
        }

    }
    public static DAOFactory getInstance(){
        if (daoFactory == null)
            daoFactory = new DAOFactory();
        return daoFactory;
    }

}
