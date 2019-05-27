package DAO.DAO.custom.DAO.custom.Impl;

import DAO.DAO.custom.Buyer_item_detailsDAO;
import Entities.Buyer_item_details;
import Entities.Buyer_item_detailsPK;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Buyer_item_detailsIML implements Buyer_item_detailsDAO {

    @Override
    public boolean insert(Buyer_item_details entity) throws SQLException {
        String sql = "INSERT INTO buyer_item_details VALUES(?,?,?)";
        return CRUDUtil.execute(sql,entity.getBuyer_item_detailsPK().getItemID(),
                entity.getBuyer_item_detailsPK().getBuyerGrnID(),
                entity.getQty());
    }

    @Override
    public boolean update(Buyer_item_details entity) throws SQLException {
        String sql = "UPDATE buyer_item_details SET qty=? WHERE buyerGrnID=? AND itemID=?";
        return CRUDUtil.execute(sql,
                entity.getQty(),
                entity.getBuyer_item_detailsPK().getBuyerGrnID(),
                entity.getBuyer_item_detailsPK().getItemID());
    }

    @Override
    public boolean delete(Integer entity) throws SQLException {
        String sql = "DELETE buyer_item_details WHERE buyerGrnID=?";
        return CRUDUtil.execute(sql,entity);
    }

    @Override
    public List<Buyer_item_details> read() throws SQLException {
        String sql = "SELECT * FROM buyer_item_details";
        ResultSet resultSet = CRUDUtil.execute(sql);

        List<Buyer_item_details> buyer_items = new ArrayList<>();
        while (resultSet.next()){
            buyer_items.add(new Buyer_item_details(new Buyer_item_detailsPK(resultSet.getInt("itemID"),
                    resultSet.getInt("buyerGrnID")),
                    resultSet.getInt("qty")));
        }
        return buyer_items;
    }
}
