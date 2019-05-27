package BO.custom.Impl;

import BO.custom.Item_priceBO;
import DAO.DAO.custom.Item_priceDAO;
import DAO.DAOFactory;
import DAO.DAOTypes;
import DTO.Item_priceDTO;
import Entities.Item_price;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Item_priceBOImpl implements Item_priceBO {
    private Item_priceDAO item_priceDAO = DAOFactory.getInstance().getDAO(DAOTypes.ITEMPRICE);
    @Override
    public boolean insert(Item_priceDTO entity) throws SQLException {
        return item_priceDAO.insert(new Item_price(entity.getItemID(),entity.getBuyingPrice(),entity.getSellingPrice()));
    }

    @Override
    public boolean update(Item_priceDTO entity) throws SQLException {
        return item_priceDAO.update(new Item_price(entity.getItemID(),entity.getBuyingPrice(),entity.getSellingPrice()));
    }

    @Override
    public boolean delete(Item_priceDTO entity) throws SQLException {
        return item_priceDAO.delete(entity.getItemID());
    }

    @Override
    public List<Item_priceDTO> read() throws SQLException {
        List<Item_price> read = item_priceDAO.read();
        List<Item_priceDTO> item_priceDTOS = new ArrayList<>();

        for (Item_price item_price:read) {
            item_priceDTOS.add(new Item_priceDTO(item_price.getItemID(),item_price.getBuyingPrice(),item_price.getSellingPrice()));
        }
        return item_priceDTOS;
    }
}
