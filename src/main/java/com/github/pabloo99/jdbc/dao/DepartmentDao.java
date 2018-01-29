package com.github.pabloo99.jdbc.dao;

import com.github.pabloo99.jdbc.connection.MySqlConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDao {

    private final Logger logger = Logger.getLogger(DepartmentDao.class);

    public int countEmployeesByDepartmentId(int departmentId) throws SQLException {
        String query = "SELECT COUNT(*) employees_count FROM employees " +
                "WHERE department_id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {

            connection = MySqlConnector.getMySqlConnection();

            statement = connection.prepareStatement(query);
            statement.setInt(1, departmentId);

            rs = statement.executeQuery();

            while (rs.next()) {
                return rs.getInt("employees_count");
            }

        } catch (SQLException e) {

            logger.error(e.getMessage(), e);

        } finally {

            rs.close();
            statement.close();
            connection.close();
        }

        return 0;
    }
}
