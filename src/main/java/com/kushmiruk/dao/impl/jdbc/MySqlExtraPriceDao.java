package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.ExtraPriceDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.model.entity.order.ExtraPrice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MySqlExtraPriceDao extends EntityDao<ExtraPrice> implements ExtraPriceDao {
    @Override
    protected Optional<ExtraPrice> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
