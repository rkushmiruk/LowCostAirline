package com.kushmiruk.dao.datasource;

import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.naming.NamingException;

/**
 * DataSource connection pool
 */
public class ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private ConnectionPool() {
    }

    public static DataSource getDataSource() {
        return initDataSource();
    }

    private static DataSource initDataSource() {
        try {
            InitialContext ic = new InitialContext();
            DataSource dateSource = (DataSource) ic.lookup("java:comp/env/jdbc/Airline");
            return dateSource;
        } catch (NamingException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}
