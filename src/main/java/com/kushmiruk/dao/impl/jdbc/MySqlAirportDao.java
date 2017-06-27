package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.AirportDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.model.entity.location.Airport;
import com.kushmiruk.model.entity.location.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * MySql implementation for CountryDao interface
 */
public class MySqlAirportDao extends EntityDao<Airport> implements AirportDao {
    private static final String TABLE_NAME = "airport";
    private static final String PARAMETER_ID = "id";
    private static final String PARAMETER_NAME = "name";
    private static final String PARAMETER_CITY = "city_id";
    private static final Integer PARAMETER_NUMBERS_WITHOUT_ID = 2;
    private static final Integer NAME_INDEX = 1;
    private static final Integer CITY_INDEX = 2;
    private static final Integer ID_INDEX = 3;

    private MySqlAirportDao() {
        super(TABLE_NAME);
    }

    private static class MySqlAirportDaoHolder {
        private final static MySqlAirportDao instance = new MySqlAirportDao();
    }

    public static MySqlAirportDao getInstance() {
        return MySqlAirportDaoHolder.instance;
    }

    @Override
    protected Optional<Airport> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(PARAMETER_ID);
        String name = resultSet.getString(PARAMETER_NAME);
        City city = null;
        if (MySqlCityDao.getInstance().findById(resultSet.getLong(PARAMETER_CITY)).isPresent()) {
            city = MySqlCityDao.getInstance().findById(resultSet.getLong(PARAMETER_CITY)).get();
        }

        return Optional.of(new Airport(id, name, city));
    }

    @Override
    protected void setEntityToParameters(Airport entity, PreparedStatement statement) throws SQLException {
        statement.setString(NAME_INDEX, entity.getName());
        statement.setLong(CITY_INDEX, entity.getCity().getId());
        if (statement.getParameterMetaData().getParameterCount() == ID_INDEX) {
            statement.setLong(ID_INDEX, entity.getId());
        }
    }

    @Override
    protected String[] arrayOfEntityParameters(Airport entity) {
        String[] result = new String[PARAMETER_NUMBERS_WITHOUT_ID];
        result[0] = PARAMETER_NAME;
        result[1] = PARAMETER_CITY;
        return result;
    }
}
