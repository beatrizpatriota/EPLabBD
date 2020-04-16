package config;

import utils.PropertyUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConfig {

    private static Connection connection = null;


    public static synchronized Connection getConnection() {
        PropertyUtils propertyUtils = PropertyUtils.getInstance();
        String url = propertyUtils.getProperty("datasource.url");
        String user = propertyUtils.getProperty("datasource.user");
        String password = propertyUtils.getProperty("datasource.password");
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
