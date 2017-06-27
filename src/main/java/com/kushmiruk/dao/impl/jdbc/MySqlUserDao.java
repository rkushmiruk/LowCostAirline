package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.UserDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.dao.impl.util.JoinType;
import com.kushmiruk.model.entity.user.User;
import com.kushmiruk.model.entity.user.UserAuthentication;
import com.kushmiruk.model.entity.user.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * MySql implementation for UserDao interface
 */
public class MySqlUserDao extends EntityDao<User> implements UserDao {
    private static final String TABLE_NAME = "user";
    private static final String PARAMETER_ID = "id";
    private static final String PARAMETER_FIRST_NAME = "first_name";
    private static final String PARAMETER_LAST_NAME = "last_name";
    private static final String PARAMETER_EMAIL = "email";
    private static final String PARAMETER_USER_AUTH = "auth_id";
    private static final String PARAMETER_ROLE_ID = "role_id";
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


    private MySqlUserDao() {
        super(TABLE_NAME);
    }

    private static class MySqlUserDaoHolder {
        private static final MySqlUserDao instance = new MySqlUserDao();
    }

    public static MySqlUserDao getInstance() {
        return MySqlUserDaoHolder.instance;
    }

    @Override
    public Optional<String> findUserRole(Long id) {
        String query = selectQueryBuilder
                .addTable(TABLE_NAME)
                .addField(PARAMETER_ROLE)
                .from()
                .join(JoinType.INNER, ROLE_TABLE, PARAMETER_ROLE_ID, PARAMETER_ID)
                .condition(PARAMETER_ID)
                .build();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(ID_INDEX, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(resultSet.getString(PARAMETER_ROLE));
                }
            }
        } catch (SQLException e) {
        }
        return Optional.empty();
    }

    @Override
    protected Optional<User> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(PARAMETER_ID);
        String firstName = resultSet.getString(PARAMETER_FIRST_NAME);
        String lastName = resultSet.getString(PARAMETER_LAST_NAME);
        String email = resultSet.getString(PARAMETER_EMAIL);
        UserAuthentication userAuthentication = null;
        if (MySqlUserAuthenticationDao.getInstance().findById(resultSet.getLong(PARAMETER_ROLE_ID)).isPresent()) {
            userAuthentication = MySqlUserAuthenticationDao.getInstance().findById(resultSet.getLong(PARAMETER_ROLE_ID)).get();
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
