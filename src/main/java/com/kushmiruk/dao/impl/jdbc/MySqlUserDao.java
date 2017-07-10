package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.UserDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.dao.impl.util.JoinType;
import com.kushmiruk.model.entity.user.User;
import com.kushmiruk.model.entity.user.UserAuthentication;
import com.kushmiruk.model.entity.user.UserRole;
import com.kushmiruk.util.LoggerMessage;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * MySql implementation for UserDao interface
 */
public class MySqlUserDao extends EntityDao<User> implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(MySqlUserDao.class);
    private static final String TABLE_NAME = "user";
    private static final String PARAMETER_ID = "id";
    private static final String PARAMETER_FIRST_NAME = "first_name";
    private static final String PARAMETER_LAST_NAME = "last_name";
    private static final String PARAMETER_EMAIL = "email";
    private static final String PARAMETER_ROLE_ID = "role_id";
    private static final String PARAMETER_USER_AUTH = "auth_id";
    private static final String PARAMETER_ROLE = "role";
    private static final String ROLE_TABLE = "user_role";
    private static final Integer PARAMETER_NUMBERS_WITHOUT_ID = 5;
    private static final Integer FIRST_NAME_INDEX = 1;
    private static final Integer LAST_NAME_INDEX = 2;
    private static final Integer EMAIL_INDEX = 3;
    private static final Integer USER_AUTHENTICATION_INDEX = 4;
    private static final Integer USER_ROLE_INDEX = 5;
    private static final Integer USER_ID_INDEX = 6;
    private static final Integer ID_INDEX = 1;

    private MySqlUserDao(Connection connection) {
        super(TABLE_NAME, connection);
    }

    private static class MySqlUserDaoHolder {
        private static MySqlUserDao instance(Connection connection) {
            return new MySqlUserDao(connection);
        }
    }

    public static MySqlUserDao getInstance(Connection connection) {
        return MySqlUserDaoHolder.instance(connection);
    }

    @Override
    public Optional<String> findUserRole(Long id) {
        String query = selectQueryBuilder
                .table(TABLE_NAME)
                .field(PARAMETER_ROLE)
                .from()
                .join(JoinType.INNER, ROLE_TABLE, PARAMETER_ROLE_ID, PARAMETER_ID)
                .condition(PARAMETER_ID)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(ID_INDEX, id);
            LOGGER.info(statement.toString());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    LOGGER.info(LoggerMessage.ITEM + ROLE_TABLE + LoggerMessage.WITH_ID + id +
                            LoggerMessage.FOUND_IN_TABLE);
                    return Optional.of(resultSet.getString(PARAMETER_ROLE));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.DB_ERROR_SEARCH + ROLE_TABLE + LoggerMessage.ITEM_WITH_ID + id +
                    LoggerMessage.EXCEPTION_MESSAGE + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    protected Optional<User> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(PARAMETER_ID);
        String firstName = resultSet.getString(PARAMETER_FIRST_NAME);
        String lastName = resultSet.getString(PARAMETER_LAST_NAME);
        String email = resultSet.getString(PARAMETER_EMAIL);
        Optional<UserAuthentication> optionalUserAuthentication = MySqlUserAuthenticationDao.getInstance(connection).findById(resultSet.getLong(PARAMETER_USER_AUTH));
        UserAuthentication userAuthentication = null;
        if (optionalUserAuthentication.isPresent()) {
            userAuthentication = optionalUserAuthentication.get();
        }
        UserRole userRole = null;
        if (findUserRole((resultSet.getLong(PARAMETER_ROLE_ID))).isPresent()) {
            userRole = UserRole.valueOf(findUserRole(resultSet.getLong(PARAMETER_ROLE_ID)).get().toUpperCase());
        }

        return Optional.of(new User.Builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .userAuthentication(userAuthentication)
                .userRole(userRole)
                .build());
    }

    @Override
    protected void setEntityToParameters(User entity, PreparedStatement statement) throws SQLException {
        statement.setString(FIRST_NAME_INDEX, entity.getFirstName());
        statement.setString(LAST_NAME_INDEX, entity.getLastName());
        statement.setString(EMAIL_INDEX, entity.getEmail());
        statement.setLong(USER_AUTHENTICATION_INDEX, entity.getUserAuthentication().getId());
        statement.setLong(USER_ROLE_INDEX, entity.getUserRole().getId());
        if (statement.getParameterMetaData().getParameterCount() == USER_ID_INDEX) {
            statement.setLong(USER_ID_INDEX, entity.getId());
        }
    }

    @Override
    protected String[] arrayOfEntityParameters(User entity) {
        String[] result = new String[PARAMETER_NUMBERS_WITHOUT_ID];
        result[0] = PARAMETER_FIRST_NAME;
        result[1] = PARAMETER_LAST_NAME;
        result[2] = PARAMETER_EMAIL;
        result[3] = PARAMETER_USER_AUTH;
        result[4] = PARAMETER_ROLE_ID;
        return result;
    }

}
