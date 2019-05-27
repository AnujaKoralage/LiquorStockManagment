package BO.custom.Impl;

import BO.BO;
import BO.custom.Buyer_item_detailsBO;
import DAO.DAO.custom.Buyer_item_detailsDAO;
import DAO.DAOFactory;
import DAO.DAOTypes;
import DTO.Buyer_item_detailsDTO;
import Entities.Buyer_item_details;
import Entities.Buyer_item_detailsPK;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Buyer_item_detailsBOImpl implements Buyer_item_detailsBO {
    private Buyer_item_detailsDAO buyer_item_detailsDAO = DAOFactory.getInstance().getDAO(DAOTypes.BUYERITEMDETAILS);
    @Override
    public boolean insert(Buyer_item_detailsDTO entity) throws SQLException {
        return buyer_item_detailsDAO.insert(new Buyer_item_details(new Buyer_item_detailsPK(entity.getItemID(),entity.getBuyerGrnID()),entity.getQty()));
    }

    @Override
    public boolean update(Buyer_item_detailsDTO entity) throws SQLException {
        return buyer_item_detailsDAO.update(new Buyer_item_details(new Buyer_item_detailsPK(entity.getItemID(),entity.getBuyerGrnID()),entity.getQty()));
    }

    @Override
    public boolean delete(Buyer_item_detailsDTO entity) throws SQLException {
        return buyer_item_detailsDAO.delete(entity.getBuyerGrnID());
    }

    @Override
    public List<Buyer_item_detailsDTO> read() throws SQLException {
        List<Buyer_item_details> read = buyer_item_detailsDAO.read();
        List<Buyer_item_detailsDTO> buyer_item_detailsDTOS = new ArrayList<>();

        for (Buyer_item_details buyer_item_details:read) {
            buyer_item_detailsDTOS.add(new Buyer_item_detailsDTO(buyer_item_details.getBuyer_item_detailsPK().getItemID(),buyer_item_details.getBuyer_item_detailsPK().getBuyerGrnID(),buyer_item_details.getQty()));

        }
        return buyer_item_detailsDTOS;
    }
}
