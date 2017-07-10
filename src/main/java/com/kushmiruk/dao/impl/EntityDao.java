package com.kushmiruk.dao.impl;

import com.kushmiruk.dao.daointerface.GenericDao;
import com.kushmiruk.dao.factory.DataSourceFactory;
import com.kushmiruk.dao.impl.util.*;
import com.kushmiruk.model.entity.Entity;
import com.kushmiruk.util.LoggerMessage;
import com.kushmiruk.util.QueryMessage;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Abstract implementation of DAO interface
 *
 * @param <T> entity type
 */
public abstract class EntityDao<T extends Entity> implements GenericDao<T, Long> {
    private static final Logger LOGGER = Logger.getLogger(EntityDao.class);
    private static final int ID_INDEX = 1;

    private QueryBuilderFactory factory = QueryBuilderFactory.getInstance();
    private String query;
    protected String tableName;
    protected SelectQueryBuilder selectQueryBuilder = factory.createSelectQueryBuilder();
    protected InsertQueryBuilder insertQueryBuilder = factory.createInsertQueryBuilder();
    protected UpdateQueryBuilder updateQueryBuilder = factory.createUpdateQueryBuilder();
    protected DeleteQueryBuilder deleteQueryBuilder = factory.createDeleteQueryBuilder();
    protected Connection connection;

    public EntityDao(String tableName, Connection connection) {
        this.tableName = tableName;
        this.connection = connection;
    }

    @Override
    public Optional<T> findById(Long id) {
        query = selectQueryBuilder
                .table(tableName)
                .getAll()
                .from()
                .condition(QueryMessage.ID)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(ID_INDEX, id);
            LOGGER.info(statement.toString());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    LOGGER.info(LoggerMessage.ITEM + tableName + LoggerMessage.WITH_ID + id +
                            LoggerMessage.FOUND_IN_TABLE);
                    return getEntityFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.DB_ERROR_SEARCH + tableName + LoggerMessage.ITEM_WITH_ID + id +
                    LoggerMessage.EXCEPTION_MESSAGE + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<T> findAll() {
        List<T> result = new ArrayList<>();
        query = selectQueryBuilder
                .table(tableName)
                .getAll()
                .from()
                .orderBy(QueryMessage.ID)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            LOGGER.info(statement.toString());
            while (resultSet.next()) {
                if (getEntityFromResultSet(resultSet).isPresent()) {
                    result.add(getEntityFromResultSet(resultSet).get());
                }
            }
            LOGGER.info(LoggerMessage.ITEMS + tableName + LoggerMessage.FOUND_IN_TABLE);
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.DB_ERROR_SEARCH + tableName + LoggerMessage.EXCEPTION_MESSAGE + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean insert(T entity) {
        Objects.requireNonNull(entity);
        query = insertQueryBuilder
                .table(tableName)
                .addValues(arrayOfEntityParameters(entity))
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            setEntityToParameters(entity, statement);
            LOGGER.info(statement.toString());
            statement.executeUpdate();
            LOGGER.info(entity.toString() + LoggerMessage.INSERT_INTO_TABLE + tableName);
            return true;
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.DB_ERROR_CONNECTION + LoggerMessage.EXCEPTION_MESSAGE + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(T entity) {
        Objects.requireNonNull(entity);
        query = updateQueryBuilder
                .table(tableName)
                .addValues(arrayOfEntityParameters(entity))
                .condition(QueryMessage.ID)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            setEntityToParameters(entity, statement);
            statement.executeUpdate();
            LOGGER.info(statement.toString());
            int rowUpdated = statement.getUpdateCount();
            connection.commit();
            LOGGER.info(rowUpdated + " row(s) updated");
            return rowUpdated > 0;
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.DB_ERROR_SEARCH + tableName + LoggerMessage.EXCEPTION_MESSAGE + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        query = deleteQueryBuilder
                .table(tableName)
                .condition(QueryMessage.ID)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(ID_INDEX, id);
            LOGGER.info(statement.toString());
            int rowUpdated = statement.executeUpdate();
            connection.commit();
            LOGGER.info(rowUpdated + " row(s) deleted");
            return statement.getUpdateCount() > 0;
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.DB_ERROR_SEARCH + tableName + LoggerMessage.EXCEPTION_MESSAGE + e.getMessage());
        }
        return false;
    }

    @Override
    public String getName() {
        return tableName;
    }


    /**
     * retrieves entity from result set
     *
     * @param resultSet resultSet from statement
     * @return Optional entity
     * @throws SQLException if some error occurs in resultSet
     */
    protected abstract Optional<T> getEntityFromResultSet(ResultSet resultSet) throws SQLException;

    /**
     * sets entity properties to prepare statement parameters
     *
     * @param entity    our entity in query
     * @param statement current statement
     * @throws SQLException if some error occurs while performing some logic
     */
    protected abstract void setEntityToParameters(T entity, PreparedStatement statement)
            throws SQLException;

    /**
     * return array of all entity parameters without id parameter
     *
     * @param entity our entity in query
     * @return String[] array of parameters
     */
    protected abstract String[] arrayOfEntityParameters(T entity);
}
