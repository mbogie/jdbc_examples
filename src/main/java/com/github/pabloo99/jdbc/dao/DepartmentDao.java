package com.github.pabloo99.jdbc.dao;

import com.github.pabloo99.jdbc.connection.MySqlConnector;
import com.github.pabloo99.jdbc.entity.Employee;
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

    public Employee findManagerByDepartmentId(int departmentId) throws SQLException {

        String query = "SELECT e.* FROM departments d JOIN employees e ON d.manager_id = e.employee_id WHERE d.department_id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = MySqlConnector.getMySqlConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, departmentId);

            rs = statement.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();

                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhoneNumber(rs.getString("phone_number"));
                employee.setHireDate(rs.getDate("hire_date").toLocalDate());
                employee.setJobId(rs.getString("job_id"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setCommissionPct(rs.getDouble("commission_pct"));
                employee.setManagerId(rs.getInt("manager_id"));
                employee.setDepartmentId(rs.getInt("department_id"));

                return employee;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            rs.close();
            statement.close();
            connection.close();
        }
        return null;
    }
}
