package BO.custom.Impl;

import BO.custom.SupplierBO;
import DAO.CRUDDAO;
import DAO.DAO.custom.SupplierDAO;
import DAO.DAOFactory;
import DAO.DAOTypes;
import DTO.ItemDTO;
import DTO.SupplierDTO;
import Entities.Item;
import Entities.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {
    public boolean insert(SupplierDTO supplierDTO) throws SQLException {
        CRUDDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.SUPPLIER);
        return dao.insert(new Supplier(supplierDTO.getId(), supplierDTO.getName(), supplierDTO.getAddress()));
    }

    public boolean update(SupplierDTO supplierDTO) throws SQLException {
        CRUDDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.SUPPLIER);
        return dao.update(new Supplier(supplierDTO.getId(), supplierDTO.getName(), supplierDTO.getAddress()));
    }

    public boolean delete(SupplierDTO supplierDTO) throws SQLException {
        CRUDDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.SUPPLIER);
        return dao.delete(supplierDTO.getId());
    }

    public List<SupplierDTO> read() throws SQLException {
        CRUDDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.SUPPLIER);
        List<Supplier> read = dao.read();

        List<SupplierDTO> list = new ArrayList<>();
        for (Supplier supplier:read) {
            list.add(new SupplierDTO(supplier.getId(),supplier.getName(),supplier.getAddress()));
        }
        return list;
    }

    public List<ItemDTO> getSupplierItems(int supplierID) throws Exception {
        SupplierDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.SUPPLIER);
        List<Item> allSuppliersDetails = dao.getAllSuppliersDetails(supplierID);

        List<ItemDTO> list = new ArrayList<>();
        for (Item item:allSuppliersDetails) {
            list.add(new ItemDTO(item.getItemID(),item.getQty(),item.getSupplierID(),item.getName()));
        }
        return list;
    }

    public int genarateSupplierID() throws Exception {
        SupplierDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.SUPPLIER);
        int lastSupplierID = dao.getLastSupplierID();
        return ++lastSupplierID;
    }

    public boolean checkSupplirExsistence(int supplierID) throws Exception {
        SupplierDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.SUPPLIER);
        return  dao.checkSupplierExsistence(supplierID);
    }
}
