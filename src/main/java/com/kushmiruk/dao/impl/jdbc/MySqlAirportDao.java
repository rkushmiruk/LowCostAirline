package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.AirportDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.dao.impl.util.QueryBuilderFactory;
import com.kushmiruk.dao.impl.util.SelectQueryBuilder;
import com.kushmiruk.model.entity.location.Airport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MySqlAirportDao extends EntityDao<Airport> implements AirportDao {
    private MySqlAirportDao() {
        entityName = Airport.class.getSimpleName();
        QueryBuilderFactory factory = QueryBuilderFactory.getInstance();
        selectQueryBuilder = factory.createSelectQueryBuilder(entityName);
        insertQueryBuilder = factory.createInsertQueryBuilder(entityName);
        updateQueryBuilder = factory.createUpdateQueryBuilder(entityName);
        deleteQueryBuilder = factory.createDeleteQueryBuilder(entityName);
    }

    private static class MySqlAirportDaoHolder {
        private final static MySqlAirportDao instance = new MySqlAirportDao();
    }

    public static MySqlAirportDao getInstance() {
        return MySqlAirportDaoHolder.instance;
    }

    @Override
    protected Optional<Airport> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");

        return Optional.of(new Airport(id, name));
    }

    @Override
    protected void setEntityToParameters(Airport entity, PreparedStatement statement) throws SQLException {
        statement.setString(1,entity.getName());
        statement.setLong(2,entity.getCity().getId());
    }

}
