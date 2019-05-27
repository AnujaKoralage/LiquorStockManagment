package DAO.DAO.custom;

import DAO.CRUDDAO;
import Entities.Item;
import Entities.Supplier;

import java.util.List;

public interface SupplierDAO extends CRUDDAO<Supplier,Integer> {
    public List<Item> getAllSuppliersDetails(int supplierID) throws Exception;
    public int getLastSupplierID() throws Exception;
    public boolean checkSupplierExsistence(int supplierID) throws Exception;
}
