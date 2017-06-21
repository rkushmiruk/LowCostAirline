package com.kushmiruk.dao.impl.jdbc;


import com.kushmiruk.dao.daointerface.TicketOrderDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.model.entity.order.TicketOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MySqlTicketOrderDao extends EntityDao<TicketOrder> implements TicketOrderDao {
    @Override
    protected Optional<TicketOrder> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    protected void setEntityToParameters(TicketOrder entity, PreparedStatement statementExternalId) throws SQLException {

    }
}
