package BO.custom.Impl;

import BO.custom.Supplier_item_detailsBO;
import DAO.CRUDDAO;
import DAO.DAO.custom.DAO.custom.Impl.CRUDUtil;
import DAO.DAO.custom.Supplier_item_detailsDAO;
import DAO.DAOFactory;
import DAO.DAOTypes;
import DTO.Supplier_item_detailsDTO;
import Entities.Supplier_item_details;
import Entities.Supplier_item_detailsPK;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Supplier_item_detailsBOImpl implements Supplier_item_detailsBO {
    Supplier_item_detailsDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.SUPPLIERITEMDETAILS);

    @Override
    public boolean insert(Supplier_item_detailsDTO entity) throws SQLException {
        return dao.insert(new Supplier_item_details(new Supplier_item_detailsPK(entity.getItemID(),entity.getSupGrnID()),entity.getQty()));
    }

    @Override
    public boolean update(Supplier_item_detailsDTO entity) throws SQLException {
        return dao.update(new Supplier_item_details(new Supplier_item_detailsPK(entity.getItemID(),entity.getSupGrnID()),entity.getQty()));
    }

    @Override
    public boolean delete(Supplier_item_detailsDTO entity) throws SQLException {
        return dao.delete(entity.getItemID());
    }

    @Override
    public List<Supplier_item_detailsDTO> read() throws SQLException {
        List<Supplier_item_details> read = dao.read();
        List<Supplier_item_detailsDTO> supplier_item_detailsDTOS = new ArrayList<>();

        for (Supplier_item_details supplier_item_details:read) {
            supplier_item_detailsDTOS.add(new Supplier_item_detailsDTO(supplier_item_details.getSupplier_item_detailsPK().getItemID(),supplier_item_details.getSupplier_item_detailsPK().getSupGRNid(),supplier_item_details.getQty()));

        }
        return supplier_item_detailsDTOS;
    }
}
