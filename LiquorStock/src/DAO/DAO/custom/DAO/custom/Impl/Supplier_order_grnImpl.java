package DAO.DAO.custom.DAO.custom.Impl;

import DAO.DAO.custom.Supplier_order_grnDAO;
import Entities.Supplier_order_grn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Supplier_order_grnImpl implements Supplier_order_grnDAO {
    @Override
    public boolean insert(Supplier_order_grn entity) throws SQLException {
        String sql = "INSERT INTO supplier_order_grn VALUES(?,?,?)";
        return CRUDUtil.execute(sql,entity.getGrnID(),entity.getSupplierID(),entity.getDate());
    }

    @Override
    public boolean update(Supplier_order_grn entity) throws SQLException {
        String sql = "UPDATE supplier_order_grn SET supplierID=? WHERE grnID=?";;
        return CRUDUtil.execute(sql,entity.getSupplierID(),entity.getGrnID());
    }

    @Override
    public boolean delete(Integer entity) throws SQLException {
        String sql = "DELETE supplier_order_grn WHERE genID=?";
        return CRUDUtil.execute(sql,entity);
    }

    @Override
    public List<Supplier_order_grn> read() throws SQLException {
        String sql = "SELECT * FROM supplier_order_grn";
        ResultSet resultSet = CRUDUtil.execute(sql);
        List<Supplier_order_grn> supplier_order_grns = new ArrayList<>();
        while (resultSet.next()){
            supplier_order_grns.add(new Supplier_order_grn(resultSet.getInt("grnID"),resultSet.getInt("supplierID"),resultSet.getString("date")));
        }
        return supplier_order_grns;
    }

    @Override
    public int getLastGRNID() throws Exception {
        String sql = "SELECT grnID FROM supplier_order_grn ORDER BY grnID DESC LIMIT 1";
        ResultSet resultSet = CRUDUtil.execute(sql);

        if (resultSet.next()){
            return resultSet.getInt("grnID");
        }
        return 0;
    }
}
