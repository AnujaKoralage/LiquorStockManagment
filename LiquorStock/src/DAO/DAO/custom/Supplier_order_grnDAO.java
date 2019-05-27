package DAO.DAO.custom;

import DAO.CRUDDAO;
import Entities.Supplier_order_grn;

public interface Supplier_order_grnDAO extends CRUDDAO<Supplier_order_grn,Integer> {
    public int getLastGRNID() throws Exception;
}
