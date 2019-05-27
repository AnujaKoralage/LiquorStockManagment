package BO.custom;

import BO.BO;
import DTO.ItemDTO;
import DTO.SupplierDTO;

import java.util.List;

public interface SupplierBO extends BO<SupplierDTO> {
    public List<ItemDTO> getSupplierItems(int supplierID) throws Exception;
    public int genarateSupplierID() throws Exception;
    public boolean checkSupplirExsistence(int supplierID) throws Exception;
}
