package com.kushmiruk.dao.daointerface;

import com.kushmiruk.model.entity.user.User;

import java.util.Optional;

/**
 * DAO interface for CRUD operations with entity User
 */
public interface UserDao extends GenericDao<User, Long> {

    /**
     * Retrieve user role from database identified by id.
     *
     * @param id identifier of user
     * @return user role
     */
    Optional<String> findUserRole(Long id);

    /**
     * Retrieves user from database identified by login.
     *
     * @param login login of user.
     * @return optional, which contains entity or null
     */
    Optional<User> findByLogin(String login);
}
