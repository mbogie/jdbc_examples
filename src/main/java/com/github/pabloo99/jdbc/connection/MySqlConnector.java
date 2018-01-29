package com.github.pabloo99.jdbc.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnector {

    private final static Logger logger = Logger.getLogger(MySqlConnector.class);

    public static Connection getMySqlConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Missing MySQL driver");
            logger.error(e.getMessage(), e);
        }

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hr4?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "pmazur", "pmazur");

            connection.setAutoCommit(true);
            
            return connection;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }
}
