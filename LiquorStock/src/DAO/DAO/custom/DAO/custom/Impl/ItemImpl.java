package DAO.DAO.custom.DAO.custom.Impl;

import DAO.DAO.custom.ItemDAO;
import Entities.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemImpl implements ItemDAO {
    @Override
    public boolean insert(Item item) throws SQLException {
        String sql = "INSERT INTO item VALUES(?,?,?,?)";
        return CRUDUtil.execute(sql,item.getItemID(),item.getName(),item.getQty(),item.getSupplierID());
    }

    @Override
    public boolean update(Item item) throws SQLException {
        String sql = "UPDATE item SET name=?,supplierID=? WHERE itemID=?";
        return CRUDUtil.execute(sql,item.getName(),item.getSupplierID(),item.getItemID());
    }

    @Override
    public boolean delete(Integer itemID) throws SQLException {
        String sql = "DELETE FROM item WHERE itemID=?";
        return CRUDUtil.execute(sql,itemID);
    }

    @Override
    public List<Item> read() throws SQLException {
        String sql = "SELECT * FROM item";
        ResultSet resultSet = CRUDUtil.execute(sql);
        List<Item> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(new Item(resultSet.getInt("itemID"),resultSet.getInt("qty"),resultSet.getInt("supplierID"),resultSet.getString("name")));
        }
        return list;
    }

    @Override
    public boolean updateQty(int itemID) throws Exception {
        String sql = "UPDATE item SET qty=? WHERE itemID=?";
        return CRUDUtil.execute(sql, itemID);
    }

    @Override
    public int getLastItemID() throws Exception {
        String sql = "SELECT itemID FROM item ORDER BY itemID DESC LIMIT 1";
        ResultSet resultSet = CRUDUtil.execute(sql);

        if (resultSet.next()){
            return resultSet.getInt("itemID");
        }
        return -99;
    }

    @Override
    public int getQtyOnHand(int itemID) throws SQLException {
        String sql = "SELECT SUM(qty) AS totalQty FROM supplier_item_details WHERE itemID=?";
        String sql2 = "SELECT SUM(qty) AS totalQty FROM buyer_item_details WHERE itemID=?";
        ResultSet resultSet = CRUDUtil.execute(sql,itemID);
        ResultSet resultSet2 = CRUDUtil.execute(sql2,itemID);

        if (resultSet.next() && resultSet2.next()){
            String seller = resultSet.getString("totalQty");
            String buyer = resultSet2.getString("totalQty");
            if (seller != null && buyer!= null){
                return Integer.parseInt(resultSet.getString("totalQty")) - Integer.parseInt(resultSet2.getString("totalQty"));
            }
            else if (buyer == null){
                return Integer.parseInt(resultSet.getString("totalQty"));
            }
        }
        return 0;
    }
}
