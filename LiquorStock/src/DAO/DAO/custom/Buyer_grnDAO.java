package DAO.DAO.custom;

import DAO.CRUDDAO;
import Entities.Buyer_GRN;

import java.sql.SQLException;

public interface Buyer_grnDAO extends CRUDDAO<Buyer_GRN,Integer> {
    public int generateOrderID() throws SQLException;
}
