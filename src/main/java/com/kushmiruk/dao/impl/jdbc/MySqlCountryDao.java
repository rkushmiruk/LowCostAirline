package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.CountryDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.model.entity.location.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MySqlCountryDao extends EntityDao<Country> implements CountryDao {
    @Override
    protected Optional<Country> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
