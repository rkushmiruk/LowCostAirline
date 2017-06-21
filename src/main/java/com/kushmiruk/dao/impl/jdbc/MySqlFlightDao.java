package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.FlightDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.model.entity.order.Flight;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MySqlFlightDao extends EntityDao<Flight> implements FlightDao {
    @Override
    protected Optional<Flight> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    protected void setEntityToParameters(Flight entity, PreparedStatement statementExternalId) throws SQLException {

    }
}
