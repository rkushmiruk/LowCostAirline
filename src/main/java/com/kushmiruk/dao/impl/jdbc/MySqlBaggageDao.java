package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.BaggageDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.model.entity.order.Baggage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MySqlBaggageDao extends EntityDao<Baggage> implements BaggageDao {
    @Override
    protected Optional<Baggage> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
