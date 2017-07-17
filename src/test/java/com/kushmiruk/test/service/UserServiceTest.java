package com.kushmiruk.test.service;

import com.kushmiruk.dao.daointerface.UserAuthenticationDao;
import com.kushmiruk.dao.daointerface.UserDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.model.entity.user.User;
import com.kushmiruk.model.entity.user.UserAuthentication;
import com.kushmiruk.service.UserService;
import com.kushmiruk.service.factory.ServiceFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;

import javax.sql.DataSource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    ServiceFactory serviceFactory;
    UserService userService;
    DaoFactory daoFactory;
    Connection connection;
    UserDao userDao;
    User user;
    DataSource dataSource;
    UserAuthentication userAuthentication;
    UserAuthenticationDao userAuthenticationDao;

    private void doInitialization() {
        serviceFactory = mock(ServiceFactory.class);
        userDao = mock(UserDao.class);
        connection = mock(Connection.class);
        daoFactory = mock(DaoFactory.class);
        user = mock(User.class);
        dataSource = mock(DataSource.class);
        userAuthentication = mock(UserAuthentication.class);
        userAuthenticationDao = mock(UserAuthenticationDao.class);
        userService = serviceFactory.createUserService();
    }

    private void stubDao() throws SQLException {
        when(dataSource.getConnection()).thenReturn(connection);
        when(daoFactory.createUserDao()).thenReturn(userDao);
        when(daoFactory.createUserAuthenticationDao()).thenReturn(userAuthenticationDao);
    }

    @Test
//    @Ignore
    public void testSave() throws SQLException {
        doInitialization();
        stubDao();

        when(userDao.findByLogin(anyString())).thenReturn(Optional.empty());
        System.out.println(user);
        boolean result = userService.save(user);
        assertTrue(result);

        verify(dataSource).getConnection();
        verify(connection).close();
    }
}
