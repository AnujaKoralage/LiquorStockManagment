package BO.custom;

import DTO.SearchDTO;

import java.sql.SQLException;
import java.util.List;

public interface QueryBO {
    public List<SearchDTO> searchLiquorID(int ID) throws SQLException;
    public List<SearchDTO> searchLiquorName(String name) throws SQLException;
}
