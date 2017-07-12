package com.kushmiruk.dao.daointerface;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * Base interface for all dao.
 *
 * @param <Entity> represents type of entity
 * @param <ID>     represents type of identifier
 */
public interface GenericDao<Entity, ID> {
    /**
     * Retrieves entity from database identified by id.
     *
     * @param id identifier of entity.
     * @return optional, which contains entity or null
     */
    Optional<Entity> findById(ID id);

    /**
     * Retrieves all entities from database.
     *
     * @return List of entities
     */
    List<Entity> findAll();

    /**
     * Insert entity to a database.
     *
     * @param entity entity to insert
     * @return true if operation succeed
     */
    boolean insert(Entity entity);

    /**
     * Update entity in a database.
     *
     * @param entity entity to update
     * @return true if operation succeed
     */
    boolean update(Entity entity);

    /**
     * Delete certain entity, identified by id, from database.
     *
     * @param id identifier of the object.
     * @return true if operation succeed
     */
    boolean delete(ID id);

    /**
     * Find entity id
     *
     * @return entity id
     */
    Optional<Long> findId();


    /**
     * returns entity name
     *
     * @return entity name
     */
    String getName();
}
