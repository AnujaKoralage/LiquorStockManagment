package BO.custom;

import BO.BO;
import DTO.Supplier_order_grnDTO;
import Entities.Supplier_order_grn;

public interface Supplier_order_grnBO extends BO<Supplier_order_grnDTO> {
    public int generateSuppliergrnID() throws Exception;
}
