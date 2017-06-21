package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.UserDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.model.entity.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MySqlUserDao extends EntityDao<User> implements UserDao {
    @Override
    public Optional<User> findOneByEmail(String email) {
        return null;
    }

    @Override
    public Optional<User> findOneByLogin(String login) {
        return null;
    }

    @Override
    protected Optional<User> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    protected void setEntityToParameters(User entity, PreparedStatement statementExternalId) throws SQLException {

    }
}
