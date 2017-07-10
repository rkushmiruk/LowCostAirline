package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.UserAuthenticationDao;
import com.kushmiruk.dao.daointerface.UserDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.dao.factory.DataSourceFactory;
import com.kushmiruk.exception.DaoException;
import com.kushmiruk.model.entity.user.User;
import com.kushmiruk.util.ExceptionMessage;
import com.kushmiruk.util.RegexPattern;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * Service for interact with DAO layer interface UserDao
 */
public class UserService {

    private UserService() {
    }

    private static class UserServiceHolder {

        private static final UserService instance = new UserService();
    }

    public static UserService getInstance() {
        return UserServiceHolder.instance;
    }

    /**
     * save user to database
     *
     * @param userFromRequest user
     * @throws DaoException if operation is not successful
     */
    public boolean save(User userFromRequest) throws DaoException {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        boolean value;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            UserDao userDao = daoFactory.createUserDao();
            UserAuthenticationDao userAuthenticationDao = daoFactory.createUserAuthenticationDao();
            checkName(userFromRequest.getFirstName());
            checkName(userFromRequest.getLastName());
            checkEmail(userFromRequest.getEmail());
            checkLogin(userFromRequest.getUserAuthentication().getLogin());
            checkPassword(userFromRequest.getUserAuthentication().getPassword());
            boolean userAuthValue = userAuthenticationDao.insert(userFromRequest.getUserAuthentication());
            if (!userAuthValue) {
                connection.rollback();
                throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.LOGIN_EXIST_ERROR));
            }
            value = userDao.insert(userFromRequest);
            if (!value) {
                connection.rollback();
                throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.EMAIL_EXIST_ERROR));
            }
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return value;
    }

    /**
     * Check name with regex if operation is not successful throw new
     * DaoException
     *
     * @param name user name
     */
    private void checkName(String name) {
        if (!name.matches(RegexPattern.NAME_PATTERN)) {
            throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.NAME_PATTERN_ERROR));
        }
    }

    /**
     * Check EMAIL with regex if operation is not successful throw new
     * DaoException
     *
     * @param email user email
     */
    private void checkEmail(String email) {
        if (!email.matches(RegexPattern.EMAIL_PATTERN)) {
            throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.EMAIL_PATTERN_ERROR));
        }
    }

    /**
     * Check LOGIN with regex if operation is not successful throw new
     * DaoException
     *
     * @param login user login
     */
    private void checkLogin(String login) {
        if (!login.matches(RegexPattern.LOGIN_PATTERN)) {
            throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.LOGIN_PATTERN_ERROR));
        }
    }

    /**
     * Check PASSWORD with regex if operation is not successful throw new
     * DaoException
     *
     * @param password user password
     */
    private void checkPassword(String password) {
        if (!password.matches(RegexPattern.PASSWORD_PATTERN)) {
            throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.PASSWORD_PATTERN_ERROR));
        }
    }


}
