package DAO.DAO.custom;

import DAO.CRUDDAO;
import Entities.Item;

import java.sql.SQLException;

public interface ItemDAO extends CRUDDAO<Item,Integer> {
    public boolean updateQty(int itemID) throws Exception;
    public int getLastItemID() throws Exception;
    public int getQtyOnHand(int itemID) throws SQLException;
}
