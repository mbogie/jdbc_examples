package com.github.pabloo99.jdbc;

import com.github.pabloo99.jdbc.dao.DepartmentDao;
import com.github.pabloo99.jdbc.entity.Employee;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.testng.AssertJUnit.assertEquals;

public class DepartmentDaoMySqlTest {

    private static final Logger logger = Logger.getLogger(DepartmentDaoMySqlTest.class);

    @Test
    public void shouldCountEmployees() {
        DepartmentDao departmentDao = new DepartmentDao();

        int result = 0;

        try {
            result = departmentDao.countEmployeesByDepartmentId(20);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        Assert.assertTrue(result == 2);
    }

    @Test
    void shouldReturnDepartmentManager(){

        DepartmentDao departmentsDao = new DepartmentDao();
        Employee expected = new Employee();
        expected.setEmployeeId(200);
        expected.setFirstName("Jennifer");
        expected.setLastName("Whalen");
        expected.setEmail("JWHALEN");
        expected.setPhoneNumber("515.123.4444");
        expected.setHireDate(LocalDate.of(2003, 9, 17));
        expected.setJobId("AD_ASST");
        expected.setSalary(4400.00);
        expected.setCommissionPct(0.0);
        expected.setManagerId(101);
        expected.setDepartmentId(10);

        Employee result = null;
        try {
            result = departmentsDao.findManagerByDepartmentId(10);
        } catch (SQLException e){
            logger.error(e.getMessage(), e);
        }

        assertEquals(expected, result);
    }
}
