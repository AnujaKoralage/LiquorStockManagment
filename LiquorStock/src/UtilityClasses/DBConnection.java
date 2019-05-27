package UtilityClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.stream.Stream;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection(){
        try {
            File file = new File("resourses/DBConnection.propertie");
            FileInputStream fileInputStream = new FileInputStream(file);
            Properties dbPro = new Properties();
            dbPro.load(fileInputStream);

            String ip = dbPro.getProperty("ip");
            String port = dbPro.getProperty("port");
            String database = dbPro.getProperty("database");
            String username = dbPro.getProperty("user");
            String password = dbPro.getProperty("password");

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+database+"",username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstnce(){
        if (dbConnection == null)
            dbConnection = new DBConnection();
        return dbConnection;
    }
    public Connection getConnection(){
        return connection;
    }
}
