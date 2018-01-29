package com.github.pabloo99.jdbc;

import com.github.pabloo99.jdbc.dao.EmployeesDao;
import com.github.pabloo99.jdbc.entity.Employee;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class EmployeeDaoMySqlTest {

    private static final Logger logger = Logger.getLogger(EmployeeDaoMySqlTest.class);

    @Test
    public void shouldReturnAllEmployees(){
        EmployeesDao employeesDao = new EmployeesDao();

        List<Employee> result = null;

        try {
            result = employeesDao.findAll();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        Assert.assertTrue(result.size() > 0);
    }

    @Test
    public void shouldReturnEmployeeDataForEmployeeWithId(){

        EmployeesDao employeesDao = new EmployeesDao();

        Employee result = null;

        try {
            result = employeesDao.findById(100);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        assertEquals(result.getFirstName(), "Steven");
        assertEquals(result.getLastName(), "King");
        assertEquals(result.getEmployeeId(), 100);
    }

}
