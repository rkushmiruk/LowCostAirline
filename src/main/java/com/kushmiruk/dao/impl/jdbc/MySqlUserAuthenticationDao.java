package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.UserAuthenticationDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.model.entity.user.UserAuthentication;
import com.kushmiruk.util.LoggerMessage;
import com.kushmiruk.util.QueryMessage;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * MySql implementation for UserAuthenticationDao interface
 */
public class MySqlUserAuthenticationDao extends EntityDao<UserAuthentication> implements UserAuthenticationDao {
    private static final Logger LOGGER = Logger.getLogger(MySqlUserAuthenticationDao.class);
    private static final String TABLE_NAME = "user_authentication";
    private static final String PARAMETER_ID = "id";
    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";
    private static final Integer PARAMETER_NUMBERS_WITHOUT_ID = 2;
    private static final Integer LOGIN_INDEX = 1;
    private static final Integer PASSWORD_INDEX = 2;
    private static final Integer ID_INDEX = 3;

    private MySqlUserAuthenticationDao() {
        super(TABLE_NAME);
    }

    private static class MySqlUserAuthenticationDaoHolder {
        private static final MySqlUserAuthenticationDao instance = new MySqlUserAuthenticationDao();
    }

    public static MySqlUserAuthenticationDao getInstance() {
        return MySqlUserAuthenticationDaoHolder.instance;
    }

    @Override
    public Optional<UserAuthentication> findOneByLogin(String login) {
        String query = selectQueryBuilder
                .addTable(tableName)
                .getAll()
                .from()
                .condition(QueryMessage.LOGIN)
                .build();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(LOGIN_INDEX, login);
            LOGGER.info(statement.toString());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    LOGGER.info(LoggerMessage.ITEM + tableName + LoggerMessage.WITH_ID + login +
                            LoggerMessage.FOUND_IN_TABLE);
                    return getEntityFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.DB_ERROR_SEARCH + tableName + LoggerMessage.ITEM_WITH_ID + login +
                    LoggerMessage.EXCEPTION_MESSAGE + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    protected Optional<UserAuthentication> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(PARAMETER_ID);
        String login = resultSet.getString(PARAMETER_LOGIN);
        String password = resultSet.getString(PARAMETER_PASSWORD);
        return Optional.of(new UserAuthentication(id, login, password));
    }

    @Override
    protected void setEntityToParameters(UserAuthentication entity, PreparedStatement statement) throws SQLException {
        statement.setString(LOGIN_INDEX, entity.getLogin());
        statement.setString(PASSWORD_INDEX, entity.getPassword());

        if (statement.getParameterMetaData().getParameterCount() == ID_INDEX) {
            statement.setLong(ID_INDEX, entity.getId());
        }
    }

    @Override
    protected String[] arrayOfEntityParameters(UserAuthentication entity) {
        String[] result = new String[PARAMETER_NUMBERS_WITHOUT_ID];
        result[0] = PARAMETER_LOGIN;
        result[1] = PARAMETER_PASSWORD;
        return result;
    }

}
