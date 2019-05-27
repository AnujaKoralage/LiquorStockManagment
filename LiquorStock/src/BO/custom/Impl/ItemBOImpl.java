package BO.custom.Impl;

import BO.custom.ItemBO;
import DAO.CRUDDAO;
import DAO.DAO.custom.ItemDAO;
import DAO.DAOFactory;
import DAO.DAOTypes;
import DTO.ItemDTO;
import Entities.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    private ItemDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.ITEM);
    @Override
    public boolean insert(ItemDTO entity) throws SQLException {
        return dao.insert(new Item(entity.getItemID(),entity.getQty(),entity.getSupID(),entity.getName()));
    }

    @Override
    public boolean update(ItemDTO entity) throws SQLException {
        return dao.update(new Item(entity.getItemID(),entity.getQty(),entity.getSupID(),entity.getName()));
    }

    @Override
    public boolean delete(ItemDTO entity) throws SQLException {
        return dao.delete(entity.getItemID());
    }

    @Override
    public List<ItemDTO> read() throws SQLException {
        List<Item> read = dao.read();
        List<ItemDTO> list = new ArrayList<>();
        for (Item item:read) {
            list.add(new ItemDTO(item.getItemID(),item.getQty(),item.getSupplierID(),item.getName()));
        }
        return list;
    }

    @Override
    public int genarateItemID() throws Exception {
        int lastItemID = dao.getLastItemID();
        return ++lastItemID;
    }

    @Override
    public int getTotalQty(int itemID) throws SQLException {
        return dao.getQtyOnHand(itemID);
    }

    @Override
    public boolean updateQty(int itemID) throws Exception {
        return dao.updateQty(itemID);
    }
}
