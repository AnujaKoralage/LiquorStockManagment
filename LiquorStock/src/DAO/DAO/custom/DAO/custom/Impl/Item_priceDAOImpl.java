package DAO.DAO.custom.DAO.custom.Impl;

import DAO.DAO.custom.Item_priceDAO;
import Entities.Item_price;
import Entities.SuperEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Item_priceDAOImpl implements Item_priceDAO {

    @Override
    public boolean insert(Item_price entity) throws SQLException {
        String sql = "INSERT INTO item_price VALUES(?,?,?)";
        return CRUDUtil.execute(sql,entity.getItemID(),entity.getBuyingPrice(),entity.getSellingPrice());
    }

    @Override
    public boolean update(Item_price entity) throws SQLException {
        String sql = "UPDATE item_price SET buyingPrice=?,dellingPrice=? WHERE itemID=?";
        return CRUDUtil.execute(sql,entity.getBuyingPrice(),entity.getSellingPrice(),entity.getItemID());
    }

    @Override
    public boolean delete(Integer entity) throws SQLException {
        String sql = "DELETE item_price WHERE itemID=?";
        return CRUDUtil.execute(sql,entity);
    }

    @Override
    public List<Item_price> read() throws SQLException {
        String sql = "SELECT * FROM item_price";
        ResultSet resultSet = CRUDUtil.execute(sql);

        List<Item_price> item_prices = new ArrayList<>();
        while (resultSet.next()){
            item_prices.add(new Item_price(resultSet.getInt("itemID"),resultSet.getDouble("buyingPrice"),resultSet.getDouble("dellingPrice")));
        }
        return item_prices;
    }
}
