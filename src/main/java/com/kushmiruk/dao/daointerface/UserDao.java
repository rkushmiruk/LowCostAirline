package com.kushmiruk.dao.daointerface;

import com.kushmiruk.model.entity.user.User;

import java.util.Optional;

/**
 * DAO interface for CRUD operations with entity User
 */
public interface UserDao extends GenericDao<User, Long> {
    /**
     * Retrieve  entity user from database identified by email.
     *
     * @param email identifier of user
     * @return optional, which contains entity User or null
     */
    Optional<User> findOneByEmail(String email);

    /**
     * Retrieve  entity user from database identified by login.
     *
     * @param login identifier of user
     * @return optional, which contains entity User or null
     */
    Optional<User> findOneByLogin(String login);
}
