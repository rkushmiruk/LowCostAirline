package com.kushmiruk.test.service;

import com.kushmiruk.dao.daointerface.UserDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.dao.factory.DataSourceFactory;
import com.kushmiruk.model.entity.user.User;
import com.kushmiruk.service.UserService;
import java.sql.Connection;


import java.sql.SQLException;
import javax.sql.DataSource;


import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Ignore;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserService userService;
    private UserDao userDao;
    private DataSource dataSource;
    private Connection connection;
    private DaoFactory daoFactory;
    private DataSourceFactory dataSourceFactory;
    private User user;

    //TODO remake services for mocking dao for services;
    private void doInitialization() {
        userDao = mock(UserDao.class);
        dataSource = mock(DataSource.class);
        connection = mock(Connection.class);
        daoFactory = mock(DaoFactory.class);
        dataSourceFactory = mock(DataSourceFactory.class);
        user = mock(User.class);
        userService = UserService.getInstance();
    }
    
    private void stubDaoFactory() throws SQLException{
        when(dataSourceFactory.getDataSource()).thenReturn(dataSource);
        when(dataSource.getConnection()).thenReturn(connection);
        when(daoFactory.createUserDao()).thenReturn(userDao);
    }

    
    @Test
    @Ignore
    public void testFindUser() throws SQLException {
        doInitialization();
        stubDaoFactory();
        
        user = userService.findUserByLogin("Ivan");
        assertNotNull(user);
        assertEquals(user.getFirstName(), "Dima");
        assertNotEquals(user.getUserAuthentication().getPassword(), "222");
        
        
    }
}
