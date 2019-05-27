package BO.custom.Impl;

import BO.custom.Supplier_order_grnBO;
import DAO.CRUDDAO;
import DAO.DAO.custom.Supplier_order_grnDAO;
import DAO.DAOFactory;
import DAO.DAOTypes;
import DTO.Supplier_order_grnDTO;
import Entities.Item;
import Entities.Supplier_order_grn;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Supplier_order_grnBOImpl implements Supplier_order_grnBO {
    private Supplier_order_grnDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.SUPPLIERORDERGRN);
    @Override
    public boolean insert(Supplier_order_grnDTO entity) throws SQLException {
        return dao.insert(new Supplier_order_grn(entity.getGrnID(),entity.getSupplierID(), entity.getDate()));
    }

    @Override
    public boolean update(Supplier_order_grnDTO entity) throws SQLException {
        return dao.update(new Supplier_order_grn(entity.getGrnID(),entity.getSupplierID(), entity.getDate()));
    }

    @Override
    public boolean delete(Supplier_order_grnDTO entity) throws SQLException {
        return dao.delete(entity.getGrnID());
    }

    @Override
    public List<Supplier_order_grnDTO> read() throws SQLException {
        List<Supplier_order_grn> read = dao.read();
        List<Supplier_order_grnDTO> supplier_order_grnDTOS = new ArrayList<>();
        for (Supplier_order_grn supplier_order_grn:read) {
            supplier_order_grnDTOS.add(new Supplier_order_grnDTO(supplier_order_grn.getGrnID(),supplier_order_grn.getSupplierID(),supplier_order_grn.getDate()));
        }
        return supplier_order_grnDTOS;
    }

    @Override
    public int generateSuppliergrnID() throws Exception {
        int lastGRNID = dao.getLastGRNID();
        return ++lastGRNID;
    }
}
