package com.kushmiruk.dao.impl;

import com.kushmiruk.dao.daointerface.GenericDao;
import com.kushmiruk.dao.factory.DataSourceFactory;
import com.kushmiruk.dao.impl.util.SelectQueryBuilder;
import com.kushmiruk.model.entity.Entity;
import com.kushmiruk.util.LoggerMessage;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    protected String entityName;
    protected SelectQueryBuilder selectQueryBuilder;
    protected DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
    private String query;

    @Override
    public Optional<T> findById(Long id) {
        query = selectQueryBuilder
                .addField("*")
                .from()
                .where()
                .condition("id")
                .build();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            LOGGER.info(statement.toString());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
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
                .addField("*")
                .from()
                .build();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                if (getEntityFromResultSet(resultSet).isPresent()) {
                    result.add(getEntityFromResultSet(resultSet).get());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    @Override
    public boolean insert(T entity) {
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
        return null;
    }

    protected abstract Optional<T> getEntityFromResultSet(ResultSet resultSet) throws SQLException;
}
