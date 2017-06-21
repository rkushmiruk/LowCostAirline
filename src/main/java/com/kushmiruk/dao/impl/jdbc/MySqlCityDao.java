package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.CityDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.model.entity.location.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MySqlCityDao extends EntityDao<City> implements CityDao {
    @Override
    protected Optional<City> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    protected void setEntityToParameters(City entity, PreparedStatement statementExternalId) throws SQLException {

    }
}
