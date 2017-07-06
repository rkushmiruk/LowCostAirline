package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.UserDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.exception.DaoException;
import com.kushmiruk.model.entity.user.User;
import com.kushmiruk.model.entity.user.UserAuthentication;
import com.kushmiruk.util.ExceptionMessage;
import com.kushmiruk.util.RegexPattern;

/**
 * Service for interact with DAO layer interface UserDao
 */
public class UserService {
    private static final DaoFactory daoFactory = DaoFactory.getDaoFactory();
    private static UserDao userDao = daoFactory.createUserDao();

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
     * @param userFromRequest
     * @throws DaoException if operation is not successful
     */
    public boolean save(User userFromRequest) throws DaoException {
        checkName(userFromRequest.getFirstName());
        checkName(userFromRequest.getLastName());
        checkEmail(userFromRequest.getEmail());
        boolean value = userDao.insert(userFromRequest);
        if (!value) {
            throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.EMAIL_EXIST_ERROR));
        }
        return value;
    }
    
    /**
     * Check name with regex
     * if operation is not successful throw new DaoException
     *
     * @param name
     */
    private void checkName(String name) {
        if (!name.matches(RegexPattern.NAME_PATTERN)) {
            throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.NAME_PATTERN_ERROR));
        }
    }

    /**
     * Check EMAIL with regex
     * if operation is not successful throw new DaoException
     *
     * @param email
     */
    private void checkEmail(String email) {
        if (!email.matches(RegexPattern.EMAIL_PATTERN)) {
            throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.EMAIL_PATTERN_ERROR));
        }
    }


}
