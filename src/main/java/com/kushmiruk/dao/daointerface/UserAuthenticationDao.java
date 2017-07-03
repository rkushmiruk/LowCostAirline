package com.kushmiruk.dao.daointerface;

import com.kushmiruk.model.entity.user.UserAuthentication;

import java.util.Optional;

/**
 * DAO interface for CRUD operations with entity UserAuthentication
 */
public interface UserAuthenticationDao extends GenericDao<UserAuthentication, Long> {

    /**
     * Retrieve  entity user from database identified by login.
     *
     * @param login identifier of user
     * @return optional, which contains entity User or null
     */
    Optional<UserAuthentication> findOneByLogin(String login);
}
