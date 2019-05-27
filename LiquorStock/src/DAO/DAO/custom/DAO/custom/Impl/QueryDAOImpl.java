package DAO.DAO.custom.DAO.custom.Impl;

import DAO.DAO.custom.QueryDAO;
import Entities.Search;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<Search> searchLiquorID(int ID) throws SQLException {
        String sql = "SELECT item.name,item.itemID,item_price.buyingPrice,item_price.dellingPrice FROM item,item_price WHERE item.itemID=item_price.itemID AND item.itemID LIKE ?";
        String id = "%"+Integer.toString(ID);
        ResultSet resultSet = CRUDUtil.execute(sql,id);
        List<Search> searches = new ArrayList<>();

        while (resultSet.next()){
            searches.add(new Search(resultSet.getInt("itemID"),resultSet.getString("name"),resultSet.getDouble("buyingPrice"),resultSet.getDouble("dellingPrice")));
        }
        return searches;
    }

    @Override
    public List<Search> searchLiquorName(String name) throws SQLException {
        String sql = "SELECT item.name,item.itemID,item_price.buyingPrice,item_price.dellingPrice FROM item,item_price WHERE item.itemID=item_price.itemID AND item.name LIKE ?";
        name = "%"+name;
        ResultSet resultSet = CRUDUtil.execute(sql,name);
        List<Search> searches = new ArrayList<>();

        while (resultSet.next()){
            searches.add(new Search(resultSet.getInt("itemID"),resultSet.getString("name"),resultSet.getDouble("buyingPrice"),resultSet.getDouble("dellingPrice")));
        }
        return searches;
    }

}
