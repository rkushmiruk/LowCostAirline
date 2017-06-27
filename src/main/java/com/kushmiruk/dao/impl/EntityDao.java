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
    protected DataSource dataSource = DataSourceFactory.getInstance().getDataSource();

    public EntityDao(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public Optional<T> findById(Long id) {
        query = selectQueryBuilder
                .addTable(tableName)
                .getAll()
                .from()
                .condition(QueryMessage.ID)
                .build();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
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
                .addTable(tableName)
                .getAll()
                .from()
                .orderBy(QueryMessage.ID)
                .build();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            LOGGER.info(statement.toString());
            while (resultSet.next()) {
                if (getEntityFromResultSet(resultSet).isPresent()) {
                    result.add(getEntityFromResultSet(resultSet).get());
                }
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.DB_ERROR_SEARCH + tableName + LoggerMessage.EXCEPTION_MESSAGE + e.getMessage());
        }
        LOGGER.info(LoggerMessage.ITEMS + tableName + LoggerMessage.FOUND_IN_TABLE);
        return result;

    }

    @Override
    public boolean insert(T entity) {
        query = insertQueryBuilder
                .addTable(tableName)
                .addValue(arrayOfEntityParameters(entity))
                .build();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setEntityToParameters(entity, statement);
            LOGGER.info(statement.toString());
            statement.executeUpdate();
            LOGGER.info(entity.toString() + LoggerMessage.INSERT_INTO_TABLE + tableName);
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            LOGGER.error(LoggerMessage.DUPLICATE_ERROR + tableName + LoggerMessage.EXCEPTION_MESSAGE + e.getMessage());
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.DB_ERROR_INSERT + tableName + LoggerMessage.EXCEPTION_MESSAGE + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(T entity) {
        query = updateQueryBuilder
                .addTable(tableName)
                .addValues(arrayOfEntityParameters(entity))
                .condition(QueryMessage.ID)
                .build();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setEntityToParameters(entity, statement);
            statement.executeUpdate();
            LOGGER.info(statement.toString());
            int rowUpdated = statement.getUpdateCount();
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
                .addTable(tableName)
                .condition(QueryMessage.ID)
                .build();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(ID_INDEX, id);
            LOGGER.info(statement.toString());
            int rowUpdated = statement.executeUpdate();
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
     */
    protected abstract Optional<T> getEntityFromResultSet(ResultSet resultSet) throws SQLException;

    /**
     * sets entity properties to prepare statement parameters
     *
     * @param entity    our entity in query
     * @param statement current statement
     */
    protected abstract void setEntityToParameters(T entity, PreparedStatement statement)
            throws SQLException;

    /**
     * return array of all entity parameters without id parameter
     *
     * @param entity our entity in query
     */
    protected abstract String[] arrayOfEntityParameters(T entity);
}
