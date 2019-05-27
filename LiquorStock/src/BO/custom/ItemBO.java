package BO.custom;

import BO.BO;
import DTO.ItemDTO;

import java.sql.SQLException;

public interface ItemBO extends BO<ItemDTO> {
    public int genarateItemID() throws Exception;
    public int getTotalQty(int itemID) throws SQLException;
    public boolean updateQty(int itemID) throws Exception;
}
