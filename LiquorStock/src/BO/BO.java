package BO;

import DTO.SuperDTO;

import java.sql.SQLException;
import java.util.List;

public interface BO<T extends SuperDTO> {
    public boolean insert(T entity) throws SQLException;
    public boolean update(T entity) throws SQLException;
    public boolean delete(T entity) throws SQLException;
    public List<T> read() throws SQLException;
}
