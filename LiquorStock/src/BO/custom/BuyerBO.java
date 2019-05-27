package BO.custom;

import BO.BO;
import DTO.BuyerDTO;

public interface BuyerBO extends BO<BuyerDTO> {
    public int genarateBuyerID() throws Exception;
}
