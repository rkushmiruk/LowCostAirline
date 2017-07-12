package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.UserAuthenticationDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.dao.factory.DataSourceFactory;
import com.kushmiruk.exception.DaoException;
import com.kushmiruk.model.entity.user.UserAuthentication;
import com.kushmiruk.util.ExceptionMessage;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Optional;
import javax.sql.DataSource;

/**
 * Service for interact with DAO layer interface UserAuthenticationDao
 */
public class UserAuthenticationService {
    private UserAuthenticationService() {
    }

    private static class UserAuthenticationServiceHolder {

        private static final UserAuthenticationService instance = new UserAuthenticationService();
    }

    public static UserAuthenticationService getInstance() {
        return UserAuthenticationServiceHolder.instance;
    }

    /**
     * Retrieve entity user from database identified by LOGIN.
     *
     * @param userAuthenticationFromRequest identifier of user
     * @return optional, which contains entity User or null
     */
    public Optional<UserAuthentication> authentication(UserAuthentication userAuthenticationFromRequest) throws DaoException {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        Optional<UserAuthentication> value;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            UserAuthenticationDao userAuthenticationDao = daoFactory.createUserAuthenticationDao();
            value = userAuthenticationDao.findOneByLogin(userAuthenticationFromRequest.getLogin());
            connection.commit();
            if (!value.isPresent()) {
                connection.rollback();
                throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.WRONG_LOGIN_ERROR));
            }
            if (!value.get().getPassword().equals(userAuthenticationFromRequest.getPassword())) {
                connection.rollback();
                throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.WRONG_PASSWORD_ERROR));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return value;
    }

    /**
     * Retrieve id from userAuthentication table
     *
     * @param login login of user
     * @return Long id
     */
    public Long getId(String login) {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        Optional<Long> value;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            UserAuthenticationDao userAuthenticationDao = daoFactory.createUserAuthenticationDao();
            value = userAuthenticationDao.findId(login);
            if (value.isPresent()) {
                connection.rollback();
                throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.ID_NOT_FOUND_ERROR));
            }
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return value.get();
    }

}
