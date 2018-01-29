package com.github.pabloo99.jdbc;

import com.github.pabloo99.jdbc.dao.EmployeesDao;
import com.github.pabloo99.jdbc.entity.Employee;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.time.LocalDate;
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

    @Test
    public void shouldSaveEmployeeAndDelete(){

        EmployeesDao employeesDao = new EmployeesDao();

        Employee employeeToSave = new Employee();
        employeeToSave.setEmployeeId(450);
        employeeToSave.setEmail("test2@gmail.com");
        employeeToSave.setLastName("Test Last Name");
        employeeToSave.setFirstName("Test First Name");
        employeeToSave.setHireDate(LocalDate.now());
        employeeToSave.setJobId("IT_PROG");
        employeeToSave.setDepartmentId(10);

        Employee employeeFromDb = null;
        int deletedRecords = 0;

        try {
            employeesDao.save(employeeToSave);

            employeeFromDb = employeesDao.findById(450);
            deletedRecords = employeesDao.delete(450);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        Assert.assertTrue(employeeToSave.getEmployeeId() == employeeFromDb.getEmployeeId());
        Assert.assertEquals(deletedRecords, 1);
    }

}
