package DAO.DAO.custom;

import DAO.CRUDDAO;
import Entities.Buyer;

public interface BuyerDAO extends CRUDDAO<Buyer,Integer> {
    public int getLastBuyerID() throws Exception;
}
