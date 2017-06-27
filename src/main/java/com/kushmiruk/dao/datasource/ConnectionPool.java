package com.kushmiruk.dao.datasource;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.ResourceBundle;

/**
 * BasicDataSource connection pool
 */
public class ConnectionPool {
    private static final String DB_BUNDLE = "sql/jdbc";
    private static final String DB_URL = "jdbc.url";
    private static final String DB_DRIVER = "jdbc.driver";
    private static final String DB_USER = "jdbc.user";
    private static final String DB_PASSWORD = "jdbc.password";
    private static final String DB_POOL_SIZE = "jdbc.poolSize";

    private ConnectionPool() {
    }

    public static DataSource getDataSource() {
        return initDataSource();
    }

    private static DataSource initDataSource() {
        ResourceBundle bundle = ResourceBundle.getBundle(DB_BUNDLE);
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(bundle.getString(DB_DRIVER));
        dataSource.setUrl(bundle.getString(DB_URL));
        dataSource.setUsername(bundle.getString(DB_USER));
        dataSource.setPassword(bundle.getString(DB_PASSWORD));
        dataSource.setInitialSize(Integer.valueOf(bundle.getString(DB_POOL_SIZE)));
        return dataSource;

    }
}
