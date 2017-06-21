package com.kushmiruk.dao.impl;

import com.kushmiruk.dao.daointerface.GenericDao;
import com.kushmiruk.dao.factory.DataSourceFactory;
import com.kushmiruk.dao.impl.util.DeleteQueryBuilder;
import com.kushmiruk.dao.impl.util.InsertQueryBuilder;
import com.kushmiruk.dao.impl.util.SelectQueryBuilder;
import com.kushmiruk.dao.impl.util.UpdateQueryBuilder;
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

    protected String entityName;
    protected SelectQueryBuilder selectQueryBuilder;
    protected InsertQueryBuilder insertQueryBuilder;
    protected UpdateQueryBuilder updateQueryBuilder;
    protected DeleteQueryBuilder deleteQueryBuilder;
    protected DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
    private String query;

    @Override
    public Optional<T> findById(Long id) {
        query = selectQueryBuilder
                .getAll()
                .from()
                .where()
                .condition(QueryMessage.ID)
                .build();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(ID_INDEX, id);
            LOGGER.info(statement.toString());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    LOGGER.info(LoggerMessage.ITEM + entityName + LoggerMessage.WITH_ID + id +
                            LoggerMessage.FOUND_IN_TABLE);
                    return getEntityFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.DB_ERROR_SEARCH + entityName + LoggerMessage.ITEM_WITH_ID + id +
                    LoggerMessage.EXCEPTION_MESSAGE + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<T> findAll() {
        List<T> result = new ArrayList<>();
        query = selectQueryBuilder
                .getAll()
                .from()
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
            LOGGER.error(LoggerMessage.DB_ERROR_SEARCH + entityName + LoggerMessage.EXCEPTION_MESSAGE + e.getMessage());
        }

        LOGGER.info(LoggerMessage.ITEMS + entityName + LoggerMessage.FOUND_IN_TABLE);
        return result;

    }

    @Override
    public boolean insert(T entity) {
        query = "INSERT INTO Airport (name,city_id) VALUES (?,?);";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setEntityToParameters(entity, statement);
            statement.execute();
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("This entity is already in database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(T entity) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public String getName() {
        return entityName;
    }

    protected abstract Optional<T> getEntityFromResultSet(ResultSet resultSet) throws SQLException;

    protected abstract void setEntityToParameters(T entity, PreparedStatement statement)
            throws SQLException;
}
