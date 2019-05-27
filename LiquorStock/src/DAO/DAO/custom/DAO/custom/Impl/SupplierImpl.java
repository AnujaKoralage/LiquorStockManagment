package DAO.DAO.custom.DAO.custom.Impl;

import DAO.DAO.custom.SupplierDAO;
import Entities.Item;
import Entities.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierImpl implements SupplierDAO {
    @Override
    public boolean insert(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO supplier VALUES(?,?,?)";
        return CRUDUtil.execute(sql,supplier.getId(),supplier.getName(),supplier.getAddress());
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET name=?,address=? WHERE id=?";
        return CRUDUtil.execute(sql,supplier.getName(),supplier.getAddress(),supplier.getId());
    }

    @Override
    public boolean delete(Integer supplierID) throws SQLException {
        String sql = "DELETE FROM supplier WHERE id=?";
        return CRUDUtil.execute(sql,supplierID);
    }

    @Override
    public List<Supplier> read() throws SQLException {
        String sql = "SELECT * FROM supplier";
        ResultSet resultSet = CRUDUtil.execute(sql);
        List<Supplier> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(new Supplier(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("address")));
        }
        return list;
    }

    @Override
    public List<Item> getAllSuppliersDetails(int supplierID) throws Exception {
        String sql = "SELECT itemID,name FROM item WHERE supplierID=?";
        ResultSet resultSet = CRUDUtil.execute(sql,supplierID);
        List<Item> items = new ArrayList<>();
        while (resultSet.next()){
            items.add(new Item(resultSet.getInt("itemID"),resultSet.getString("name")));
        }
        return items;
    }

    @Override
    public int getLastSupplierID() throws Exception {
        String sql = "SELECT id FROM supplier ORDER BY id DESC LIMIT 1";
        ResultSet resultSet = CRUDUtil.execute(sql);

        if (resultSet.next()){
            return resultSet.getInt("id");
        }
        return -99;
    }

    @Override
    public boolean checkSupplierExsistence(int supplierID) throws Exception {
        String sql = "SELECT * FROM supplier supplier_order_grn WHERE supplierID=?";
        ResultSet resultSet = CRUDUtil.execute(sql, supplierID);
        if (resultSet.next()){
            return false;
        }
        return true;
    }
}
