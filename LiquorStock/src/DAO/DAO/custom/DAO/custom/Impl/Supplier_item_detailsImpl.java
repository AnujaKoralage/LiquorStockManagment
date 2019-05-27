package DAO.DAO.custom.DAO.custom.Impl;

import DAO.DAO.custom.Supplier_item_detailsDAO;
import Entities.Supplier_item_details;
import Entities.Supplier_item_detailsPK;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Supplier_item_detailsImpl implements Supplier_item_detailsDAO {
    @Override
    public boolean insert(Supplier_item_details entity) throws SQLException {
        String sql = "INSERT INTO supplier_item_details VALUES(?,?,?)";
        return CRUDUtil.execute(sql,entity.getSupplier_item_detailsPK().getItemID(),entity.getSupplier_item_detailsPK().getSupGRNid(),entity.getQty());
    }

    @Override
    public boolean update(Supplier_item_details entity) throws SQLException {
        String sql = "UPDATE supplier_item_details SET qty=? WHERE supGRNid=? AND itemID=?";
        return CRUDUtil.execute(sql,entity.getQty(),entity.getSupplier_item_detailsPK().getSupGRNid(),entity.getSupplier_item_detailsPK().getItemID());
    }

    @Override
    public boolean delete(Integer entity) throws SQLException {
        String sql = "DELETE supplier_item_details WHERE supGRNid=? ";
        return CRUDUtil.execute(sql,entity);
    }

    @Override
    public List<Supplier_item_details> read() throws SQLException {
        String sql = "SELECT * FROM supplier_item_details";
        ResultSet resultSet = CRUDUtil.execute(sql);
        List<Supplier_item_details> supplier_item_details = new ArrayList<>();

        while (resultSet.next()){
            supplier_item_details.add(new Supplier_item_details(new Supplier_item_detailsPK(resultSet.getInt("itemID"),resultSet.getInt("supGRNid")),resultSet.getInt("qty")));
        }
        return supplier_item_details;
    }
}
