package DAO.DAO.custom.DAO.custom.Impl;

import UtilityClasses.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CRUDUtil {

    public static <T> T execute(String sql,Object...parms) throws SQLException {
        Connection connection = DBConnection.getInstnce().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i=0; i<parms.length;i++){
            preparedStatement.setObject(i+1, parms[i]);
        }

        if (sql.startsWith("SELECT")){
            return (T) preparedStatement.executeQuery();
        }
        else {
            return (T)(Boolean)(preparedStatement.executeUpdate()>0);
        }
    }
}
