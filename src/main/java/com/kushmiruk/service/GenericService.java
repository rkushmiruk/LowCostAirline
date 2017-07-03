package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.GenericDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.exception.DaoException;
import com.kushmiruk.model.entity.Entity;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Represents abstract Service for interact with DAO layer interfaces
 *
 * @param <T> entity type
 */
public abstract class GenericService<T extends Entity> {
    private static final Logger LOGGER = Logger.getLogger(GenericService.class);
    protected static DaoFactory daoFactory = DaoFactory.getDaoFactory();
    /* Abstract DAO interface */
    protected GenericDao<T, Long> dao;

    public GenericService(GenericDao<T, Long> dao) {
        this.dao = dao;
    }

    /**
     * Retrieves entity from database identified by id.
     *
     * @param id identifier of entity.
     * @return optional, which contains entity or null
     * @throws DaoException if operation is not successful
     */
    public Optional<T> findById(Long id) throws DaoException {
        Optional<T> entity = dao.findById(id);
        if (!entity.isPresent()) {
            String message = ";";
            throw new DaoException(message);
        }
        return entity;
    }

    /**
     * Retrieves all entities from database.
     *
     * @return List of entities
     */
    List<T> findAll() throws DaoException {
        List<T> value = dao.findAll();
        if (value.isEmpty()) {
            throw new DaoException("");
        }
        return value;
    }

    /**
     * Insert entity to a database.
     *
     * @param entity entity to insert
     * @return true if operation succeed
     */
    boolean save(T entity) throws DaoException {
        boolean value = dao.insert(entity);
        if (!value) {
            throw new DaoException("");
        }
        return value;
    }

    /**
     * Update entity in a database.
     *
     * @param entity entity to update
     * @return true if operation succeed
     */
    boolean update(T entity) throws DaoException {
        boolean value = dao.update(entity);
        if (!value) {
            throw new DaoException("");
        }
        return value;
    }

    /**
     * Delete certain entity, identified by id, from database.
     *
     * @param id identifier of the object.
     * @return true if operation succeed
     */
    boolean delete(Long id) throws DaoException {
        boolean value = dao.delete(id);
        if (!value) {
            throw new DaoException("");
        }
        return value;
    }


}
