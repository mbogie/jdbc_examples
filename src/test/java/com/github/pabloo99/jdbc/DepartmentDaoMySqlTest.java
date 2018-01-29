package com.github.pabloo99.jdbc;

import com.github.pabloo99.jdbc.dao.DepartmentDao;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;

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
}
