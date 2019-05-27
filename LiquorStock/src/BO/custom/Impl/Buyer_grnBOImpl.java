package BO.custom.Impl;

import BO.custom.Buyer_grnBO;
import DAO.CRUDDAO;
import DAO.DAO.custom.Buyer_grnDAO;
import DAO.DAOFactory;
import DAO.DAOTypes;
import DTO.Buyer_GRNDTO;
import Entities.Buyer_GRN;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Buyer_grnBOImpl implements Buyer_grnBO {
    private Buyer_grnDAO buyer_grnDAO = DAOFactory.getInstance().getDAO(DAOTypes.BUYERGRN);
    @Override
    public boolean insert(Buyer_GRNDTO entity) throws SQLException {
        return buyer_grnDAO.insert(new Buyer_GRN(entity.getGrnID(),entity.getBuyerID(),entity.getDate()));
    }

    @Override
    public boolean update(Buyer_GRNDTO entity) throws SQLException {
        return buyer_grnDAO.update(new Buyer_GRN(entity.getGrnID(),entity.getBuyerID(),entity.getDate()));
    }

    @Override
    public boolean delete(Buyer_GRNDTO entity) throws SQLException {
        return buyer_grnDAO.delete(entity.getGrnID());
    }

    @Override
    public List<Buyer_GRNDTO> read() throws SQLException {
        List<Buyer_GRN> read = buyer_grnDAO.read();
        List<Buyer_GRNDTO> buyer_grndtos = new ArrayList<>();

        for (Buyer_GRN buyer_grn:read) {
            buyer_grndtos.add(new Buyer_GRNDTO(buyer_grn.getGrnID(),buyer_grn.getBuyerID(),buyer_grn.getDate()));
        }

        return buyer_grndtos;
    }

    @Override
    public int generateOrderID() throws SQLException {
        return buyer_grnDAO.generateOrderID();
    }
}
