package DAO.DAO.custom;

import DAO.SuperDAO;
import Entities.Search;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    public List<Search> searchLiquorID(int ID) throws SQLException;
    public List<Search> searchLiquorName(String name) throws SQLException;
}
