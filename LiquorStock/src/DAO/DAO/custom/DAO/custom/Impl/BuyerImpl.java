package DAO.DAO.custom.DAO.custom.Impl;

import DAO.DAO.custom.BuyerDAO;
import Entities.Buyer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyerImpl implements BuyerDAO {
    @Override
    public boolean insert(Buyer entity) throws SQLException {
        String sql = "INSERT INTO buyer VALUES(?,?,?)";
        return CRUDUtil.execute(sql,entity.getBuyerID(),entity.getName(),entity.getAddress());
    }

    @Override
    public boolean update(Buyer entity) throws SQLException {
        String sql = "UPDATE buyer SET name=?,address=? WHERE buyerID=?";
        return CRUDUtil.execute(sql,entity.getName(),entity.getAddress(),entity.getBuyerID());
    }

    @Override
    public boolean delete(Integer entity) throws SQLException {
         String sql = "DELETE FROM buyer WHERE buyerID=?";
         return CRUDUtil.execute(sql,entity);
    }

    @Override
    public List<Buyer> read() throws SQLException {
        String sql = "SELECT * FROM buyer";
        ResultSet resultSet = CRUDUtil.execute(sql);
        List<Buyer> buyers = new ArrayList<>();

        while (resultSet.next()){
            buyers.add(new Buyer(resultSet.getInt("buyerID"),resultSet.getString("name"),resultSet.getString("address")));
        }
        return buyers;
    }

    @Override
    public int getLastBuyerID() throws Exception {
        String sql = "SELECT buyerID FROM buyer ORDER BY buyerID DESC LIMIT 1";
        ResultSet resultSet = CRUDUtil.execute(sql);

        if (resultSet.next()){
            return resultSet.getInt("buyerID");
        }
        return -99;
    }
}
