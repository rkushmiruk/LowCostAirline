package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.UserAuthenticationDao;
import com.kushmiruk.exception.DaoException;
import com.kushmiruk.model.entity.user.UserAuthentication;

import java.util.Optional;

/**
 *  Service for interact with DAO layer interface UserAuthenticationDao
 */
public class UserAuthenticationService extends GenericService<UserAuthentication> {
    private static UserAuthenticationDao userAuthenticationDao = daoFactory.createUserAuthenticationDao();

    private UserAuthenticationService() {
        super(userAuthenticationDao);
    }

    private static class UserAuthenticationServiceHolder {
        private static final UserAuthenticationService instance = new UserAuthenticationService();
    }

    public static UserAuthenticationService getInstance() {
        return UserAuthenticationServiceHolder.instance;
    }

    /**
     * Retrieve  entity user from database identified by login.
     *
     * @param userAuthenticationFromRequest identifier of user
     * @return optional, which contains entity User or null
     */
    Optional<UserAuthentication> authentication(UserAuthentication userAuthenticationFromRequest) throws DaoException {
        Optional<UserAuthentication> userAuthentication =
                userAuthenticationDao.findOneByLogin(userAuthenticationFromRequest.getLogin());
//        Optional<User>
//        if (!value.isPresent()) {
//            throw new DaoException("");
//        }
//        return value;
        return userAuthentication;
    }
}
