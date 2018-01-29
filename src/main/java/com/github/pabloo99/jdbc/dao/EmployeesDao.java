package com.github.pabloo99.jdbc.dao;

import com.github.pabloo99.jdbc.connection.MySqlConnector;
import com.github.pabloo99.jdbc.entity.Employee;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDao {
    private final static Logger logger = Logger.getLogger(EmployeesDao.class);

    public List<Employee> findAll() throws SQLException {

        List<Employee> allEmployees = new ArrayList<>();

        String query = "SELECT * FROM employees";

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {

            connection = MySqlConnector.getMySqlConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

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

                employee.setCommissionPct(rs.getDouble("commission_Pct"));

                employee.setManagerId(rs.getInt("manager_id"));

                employee.setDepartmentId(rs.getInt("department_Id"));


                allEmployees.add(employee);
                //logger.info(employee.getEmployeeId());
            }

        } catch (SQLException e) {

            logger.error(e.getMessage(), e);

        } finally {

            rs.close();
            statement.close();
            connection.close();

        }


        return allEmployees;
    }

    public Employee findById(Integer employeeId) throws SQLException {
        String query = "SELECT * FROM employees WHERE employee_id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Employee employee = null;

        try {

            connection = MySqlConnector.getMySqlConnection();

            statement = connection.prepareStatement(query);
            statement.setInt(1, employeeId);

            rs = statement.executeQuery();

            while (rs.next()) {

                employee = new Employee();

                employee.setEmployeeId(rs.getInt("employee_id"));

                employee.setFirstName(rs.getString("first_name"));

                employee.setLastName(rs.getString("last_name"));

                employee.setEmail(rs.getString("email"));

                employee.setPhoneNumber(rs.getString("phone_number"));

                employee.setHireDate(rs.getDate("hire_date").toLocalDate());

                employee.setJobId(rs.getString("job_id"));

                employee.setSalary(rs.getDouble("salary"));

                employee.setCommissionPct(rs.getDouble("commission_Pct"));

                employee.setManagerId(rs.getInt("manager_id"));

                employee.setDepartmentId(rs.getInt("department_Id"));
            }

        } catch (SQLException e) {

            logger.error(e.getMessage(), e);

        } finally {

            rs.close();
            statement.close();
            connection.close();
        }

        return employee;
    }

    public int save(Employee emp) throws SQLException {
        String query = "INSERT INTO employees(employee_id, first_name, last_name," +
                " hire_date, email, phone_number,job_id, manager_id, " +
                "department_id, salary,commission_pct) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = MySqlConnector.getMySqlConnection();

            statement = connection.prepareStatement(query);
            statement.setInt(1, emp.getEmployeeId());
            statement.setString(2, emp.getFirstName());
            statement.setString(3, emp.getLastName());
            statement.setDate(4, Date.valueOf(emp.getHireDate()));
            statement.setString(5, emp.getEmail());
            statement.setString(6, emp.getPhoneNumber());
            statement.setString(7, emp.getJobId());
            statement.setInt(8, emp.getManagerId());
            statement.setInt(9, emp.getDepartmentId());
            statement.setDouble(10, emp.getSalary());
            statement.setDouble(11, emp.getCommissionPct());

            int result = statement.executeUpdate();

            return result;

        } catch (SQLException e) {

            logger.error(e.getMessage(), e);

        } finally {
            statement.close();
            connection.close();
        }

        return 0;
    }

    public int delete(int employeeId) throws SQLException {
        String query = "DELETE FROM employees WHERE employee_id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = MySqlConnector.getMySqlConnection();

            statement = connection.prepareStatement(query);
            statement.setInt(1, employeeId);

            int result = statement.executeUpdate();

            return result;

        } catch (SQLException e) {

            logger.error(e.getMessage(), e);

        } finally {
            statement.close();
            connection.close();
        }

        return 0;
    }

    public void updateSalary(int id, double salary) throws SQLException {

        String query = "update employees set SALARY = ? where employee_id = ?";


        Connection connection = null;

        PreparedStatement statement = null;


        try {

            connection = MySqlConnector.getMySqlConnection();

            statement = connection.prepareStatement(query);

            statement.setInt(2, id);

            statement.setDouble(1, salary);

            statement.executeUpdate();


        } catch (SQLException e) {

            logger.error(e.getMessage(), e);

        } finally {

            statement.close();

            connection.close();

        }
    }

}
