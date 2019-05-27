package BO.custom.Impl;

import BO.custom.BuyerBO;
import DAO.DAO.custom.BuyerDAO;
import DAO.DAOFactory;
import DAO.DAOTypes;
import DTO.BuyerDTO;
import Entities.Buyer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyerBOImpl implements BuyerBO {

    BuyerDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.BUYER);
    @Override
    public boolean insert(BuyerDTO entity) throws SQLException {
        return dao.insert(new Buyer(entity.getBuyerID(),entity.getName(),entity.getAddress()));
    }

    @Override
    public boolean update(BuyerDTO entity) throws SQLException {
        return dao.update(new Buyer(entity.getBuyerID(),entity.getName(),entity.getAddress()));
    }

    @Override
    public boolean delete(BuyerDTO entity) throws SQLException {
        return dao.delete(entity.getBuyerID());
    }

    @Override
    public List<BuyerDTO> read() throws SQLException {
        List<Buyer> read = dao.read();
        List<BuyerDTO> buyerDTOList = new ArrayList<>();
        for (Buyer buyer:read) {
            buyerDTOList.add(new BuyerDTO(buyer.getBuyerID(),buyer.getName(),buyer.getAddress()));
        }
        return buyerDTOList;
    }

    @Override
    public int genarateBuyerID() throws Exception {
        BuyerDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.BUYER);
        int lastBuyerID = dao.getLastBuyerID();
        return ++lastBuyerID;
    }
}
