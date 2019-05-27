package DAO;

import Entities.Item;
import Entities.SuperEntity;

import java.sql.SQLException;
import java.util.List;

public interface CRUDDAO<T extends SuperEntity,ID> extends SuperDAO{
    public boolean insert(T entity) throws SQLException;
    public boolean update(T entity) throws SQLException;
    public boolean delete(ID entity) throws SQLException;
    public List<T> read() throws SQLException;
}
