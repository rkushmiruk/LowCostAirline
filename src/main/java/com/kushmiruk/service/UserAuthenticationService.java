package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.UserAuthenticationDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.exception.DaoException;
import com.kushmiruk.model.entity.user.UserAuthentication;
import com.kushmiruk.util.ExceptionMessage;
import com.kushmiruk.util.RegexPattern;

import java.util.Optional;


/**
 * Service for interact with DAO layer interface UserAuthenticationDao
 */
public class UserAuthenticationService {
    private static final DaoFactory daoFactory = DaoFactory.getDaoFactory();
    private static UserAuthenticationDao userAuthenticationDao = daoFactory.createUserAuthenticationDao();

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
        Optional<UserAuthentication> value = userAuthenticationDao.findOneByLogin(userAuthenticationFromRequest.getLogin());
        if (!value.isPresent()) {
            throw new DaoException("Wrong Login");
        }
        if(!value.get().getPassword().equals(userAuthenticationFromRequest.getPassword())){
            throw new DaoException("Wrong PASSWORD");
        }
        
        return value;
    }

    /**
     * saves LOGIN and PASSWORD to database
     *
     * @param userAuthentication
     * @throws DaoException if operation is not successful
     */
    public boolean save(UserAuthentication userAuthentication) throws DaoException {
        boolean value = userAuthenticationDao.insert(userAuthentication);
        checkLogin(userAuthentication.getLogin());
        checkPassword(userAuthentication.getPassword());
        if (!value) {
            throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.LOGIN_EXIST_ERROR));
        }
        return value;
    }

    /**
     * Find user authentication id with common LOGIN
     *
     * @param login
     * @return user authentication id
     */
    public Long findId(String login) {
        Optional<Long> value = userAuthenticationDao.findId(login);
        if (!value.isPresent()) {
            throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.ID_NOT_FOUND_ERROR));
        }
        return value.get();
    }

    /**
     * deletes LOGIN and PASSWORD from database
     *
     * @param id entity id
     * @throws DaoException if operation is not successful
     */
    public boolean delete(Long id) throws DaoException {
        boolean value = userAuthenticationDao.delete(id);
        if (!value) {
            throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.DELETE_ERROR));
        }
        return value;
    }

    /**
     * Check LOGIN with regex
     * if operation is not successful throw new DaoException
     *
     * @param login
     */
    private void checkLogin(String login) {
        if (!login.matches(RegexPattern.LOGIN_PATTERN)) {
            throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.LOGIN_PATTERN_ERROR));
        }
    }

    /**
     * Check PASSWORD with regex
     * if operation is not successful throw new DaoException
     *
     * @param password
     */
    private void checkPassword(String password) {
        if (!password.matches(RegexPattern.PASSWORD_PATTERN)) {
            throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.PASSWORD_PATTERN_ERROR));
        }
    }


}
