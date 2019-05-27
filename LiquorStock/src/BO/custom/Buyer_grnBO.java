package BO.custom;

import BO.BO;
import DTO.Buyer_GRNDTO;

import java.sql.SQLException;

public interface Buyer_grnBO extends BO<Buyer_GRNDTO> {
    public int generateOrderID() throws SQLException;
}
