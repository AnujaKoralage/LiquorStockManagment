package DAO.DAO.custom.DAO.custom.Impl;

import DAO.DAO.custom.Buyer_grnDAO;
import Entities.Buyer_GRN;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Buyer_grnImpl implements Buyer_grnDAO {
    @Override
    public boolean insert(Buyer_GRN entity) throws SQLException {
        String sql = "INSERT INTO buyer_grn VALUES(?,?,?)";
        return CRUDUtil.execute(sql,entity.getGrnID(),entity.getBuyerID(),entity.getDate());
    }

    @Override
    public boolean update(Buyer_GRN entity) throws SQLException {
        String sql = "UPDATE buyer_grn SET buyerID=?,date=? WHERE grnID=?";
        return CRUDUtil.execute(sql,entity.getBuyerID(),entity.getDate(),entity.getGrnID());
    }

    @Override
    public boolean delete(Integer entity) throws SQLException {
       String sql = "DELETE buyer_grn WHERE grnID=?";
       return CRUDUtil.execute(sql,entity);
    }

    @Override
    public List<Buyer_GRN> read() throws SQLException {
        String sql = "SELECT * FROM buyer_grn";
        ResultSet resultSet = CRUDUtil.execute(sql);

        List<Buyer_GRN> buyer_grns = new ArrayList<>();
        while (resultSet.next()){
            buyer_grns.add(new Buyer_GRN(resultSet.getInt("grnID"),
                    resultSet.getInt("buyerID"),
                    resultSet.getString("date")));
        }
        return buyer_grns;
    }

    @Override
    public int generateOrderID() throws SQLException {
        String sql = "SELECT grnID FROM buyer_grn ORDER BY grnID DESC LIMIT 1";
        ResultSet resultSet = CRUDUtil.execute(sql);

        if (resultSet.next()){
            return resultSet.getInt("grnID")+1;
        }
        return 1;
    }
}
