package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.CityDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.model.entity.location.City;
import com.kushmiruk.model.entity.location.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * MySql implementation for CountryDao interface
 */
public class MySqlCityDao extends EntityDao<City> implements CityDao {
    private static final String TABLE_NAME = "city";
    private static final String PARAMETER_ID = "id";
    private static final String PARAMETER_NAME = "name";
    private static final String PARAMETER_TIMEZONE = "time_zone";
    private static final String PARAMETER_COUNTRY = "country_id";
    private static final Integer PARAMETER_NUMBERS_WITHOUT_ID = 3;
    private static final Integer NAME_INDEX = 1;
    private static final Integer TIME_ZONE_INDEX = 2;
    private static final Integer COUNTRY_INDEX = 3;
    private static final Integer ID_INDEX = 4;

    private MySqlCityDao() {
        super(TABLE_NAME);
    }

    private static class MySqlCityDaoHolder {
        private static final MySqlCityDao instance = new MySqlCityDao();
    }

    public static MySqlCityDao getInstance() {
        return MySqlCityDaoHolder.instance;
    }

    @Override
    protected Optional<City> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(PARAMETER_ID);
        String name = resultSet.getString(PARAMETER_NAME);
        Country country = null;
        if (MySqlCountryDao.getInstance().findById(resultSet.getLong(PARAMETER_COUNTRY)).isPresent()) {
            country = MySqlCountryDao.getInstance().findById(resultSet.getLong(PARAMETER_COUNTRY)).get();
        }
        Integer timeZone = resultSet.getInt(PARAMETER_TIMEZONE);

        return Optional.of(new City(id, name, timeZone, country));
    }

    @Override
    protected void setEntityToParameters(City entity, PreparedStatement statement) throws SQLException {
        statement.setString(NAME_INDEX, entity.getName());
        statement.setInt(TIME_ZONE_INDEX, entity.getTimeZone());
        statement.setLong(COUNTRY_INDEX, entity.getCountry().getId());

        if (statement.getParameterMetaData().getParameterCount() == ID_INDEX) {
            statement.setLong(ID_INDEX, entity.getId());
        }
    }

    @Override
    protected String[] arrayOfEntityParameters(City entity) {
        String[] result = new String[PARAMETER_NUMBERS_WITHOUT_ID];
        result[0] = PARAMETER_NAME;
        result[1] = PARAMETER_TIMEZONE;
        result[2] = PARAMETER_COUNTRY;
        return result;
    }
}
