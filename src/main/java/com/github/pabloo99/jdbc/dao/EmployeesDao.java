package com.github.pabloo99.jdbc.dao;

import com.github.pabloo99.jdbc.connection.MySqlConnector;
import com.github.pabloo99.jdbc.entity.Employee;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDao {
    private final static Logger logger = Logger.getLogger(EmployeesDao.class);

    public List<Employee> findAll() {

        List<Employee> allEmployees = new ArrayList<>();

        String query = "Select * From employees";


        try {

            Connection connection = MySqlConnector.getMySqlConnection();

            Statement statment = connection.createStatement();

            ResultSet rs = statment.executeQuery(query);


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

                employee.setCommissionPct(rs.getDouble("ommission_Pct"));

                employee.setManagerId(rs.getInt("manager_id"));

                employee.setDepartmentId(rs.getInt("departmentId"));


                allEmployees.add(employee);

            }


        } catch (SQLException e) {

            logger.error(e.getMessage(), e);

        }


        return allEmployees;
    }
}
