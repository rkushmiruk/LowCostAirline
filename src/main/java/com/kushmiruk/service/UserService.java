package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.UserDao;
import com.kushmiruk.model.entity.user.User;

import java.sql.Connection;

public class UserService extends GenericService<User> {
    private static UserDao userDao = daoFactory.createUserDao();

    private UserService() {
        super(userDao);
    }

    private static class UserServiceHolder {
        private static final UserService instance = new UserService();
    }

    public static UserService getInstance() {
        return UserServiceHolder.instance;
    }


}
