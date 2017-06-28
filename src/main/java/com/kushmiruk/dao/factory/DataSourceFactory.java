package com.kushmiruk.dao.factory;

import com.kushmiruk.dao.datasource.ConnectionPool;
import com.kushmiruk.util.LoggerMessage;
import com.kushmiruk.util.ExceptionMessage;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

/**
 * DataSource Factory with Connection pool
 */
public class DataSourceFactory {
    private static final Logger LOGGER = Logger.getLogger(DataSourceFactory.class);
    private DataSource dataSource;

    private DataSourceFactory() {
        try {
            dataSource = ConnectionPool.getDataSource();
        } catch (Exception e) {
            LOGGER.error(LoggerMessage.DATASOURCE_INIT_ERROR + e.getMessage());
            throw new RuntimeException(ExceptionMessage.ERROR_CONNECTION_INIT);
        }
    }

    private static class DataSourceFactoryHolder {
        private static final DataSourceFactory instance = new DataSourceFactory();
    }

    public static DataSourceFactory getInstance() {
        return DataSourceFactoryHolder.instance;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
