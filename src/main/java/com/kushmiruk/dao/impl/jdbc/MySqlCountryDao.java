package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.CountryDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.model.entity.location.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * MySql implementation for CountryDao interface
 */
public class MySqlCountryDao extends EntityDao<Country> implements CountryDao {
    private static final String TABLE_NAME = "country";
    private static final String PARAMETER_ID = "id";
    private static final String PARAMETER_NAME = "name";
    private static final Integer PARAMETER_NUMBERS_WITHOUT_ID = 1;
    private static final Integer NAME_INDEX = 1;
    private static final Integer ID_INDEX = 2;

    private MySqlCountryDao() {
        super(TABLE_NAME);
    }

    private static class MySqlCountryDaoHolder {
        private final static MySqlCountryDao instance = new MySqlCountryDao();
    }

    public static MySqlCountryDao getInstance() {
        return MySqlCountryDaoHolder.instance;
    }

    @Override
    protected Optional<Country> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(PARAMETER_ID);
        String name = resultSet.getString(PARAMETER_NAME);

        return Optional.of(new Country(id, name));
    }

    @Override
    protected void setEntityToParameters(Country entity, PreparedStatement statement) throws SQLException {
        statement.setString(NAME_INDEX, entity.getName());
        if (statement.getParameterMetaData().getParameterCount() == ID_INDEX) {
            statement.setLong(ID_INDEX, entity.getId());
        }
    }

    @Override
    protected String[] arrayOfEntityParameters(Country entity) {
        String[] result = new String[PARAMETER_NUMBERS_WITHOUT_ID];
        result[0] = PARAMETER_NAME;
        return result;
    }
}
