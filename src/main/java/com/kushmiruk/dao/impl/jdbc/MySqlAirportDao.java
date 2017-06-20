package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.AirportDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.dao.impl.util.SelectQueryBuilder;
import com.kushmiruk.model.entity.location.Airport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MySqlAirportDao extends EntityDao<Airport> implements AirportDao {
    private MySqlAirportDao() {
        entityName = Airport.class.getSimpleName();
        selectQueryBuilder = new SelectQueryBuilder(entityName);
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

}
