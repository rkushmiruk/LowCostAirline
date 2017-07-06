package com.kushmiruk.dao.daointerface;

import com.kushmiruk.model.entity.user.UserAuthentication;

import java.util.Optional;

/**
 * DAO interface for CRUD operations with entity UserAuthentication
 */
public interface UserAuthenticationDao extends GenericDao<UserAuthentication, Long> {

    /**
     * Retrieve  entity user from database identified by LOGIN.
     *
     * @param login identifier of user
     * @return optional, which contains entity User or null
     */
    Optional<UserAuthentication> findOneByLogin(String login);

    /**
     * Find entity id with common LOGIN
     *
     * @param login
     * @return entity id
     */
    Optional<Long> findId(String login);
}
