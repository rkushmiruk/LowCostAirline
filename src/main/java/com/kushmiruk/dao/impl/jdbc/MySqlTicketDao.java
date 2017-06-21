package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.TicketDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.model.entity.order.Ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MySqlTicketDao extends EntityDao<Ticket> implements TicketDao {
    @Override
    protected Optional<Ticket> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    protected void setEntityToParameters(Ticket entity, PreparedStatement statementExternalId) throws SQLException {

    }
}
