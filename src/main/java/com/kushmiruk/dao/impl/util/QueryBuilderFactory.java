package com.kushmiruk.dao.impl.util;

/**
 * Abstract factory for different query builders
 */
public class QueryBuilderFactory {

    private QueryBuilderFactory() {

    }

    private static class QueryBuilderFactoryHolder {
        private static final QueryBuilderFactory instance = new QueryBuilderFactory();
    }

    public SelectQueryBuilder createSelectQueryBuilder(String tableName) {
        return new SelectQueryBuilder(tableName);
    }

    public UpdateQueryBuilder createUpdateQueryBuilder(String tableName) {
        return new UpdateQueryBuilder(tableName);
    }

    public InsertQueryBuilder createInsertQueryBuilder(String tableName) {
        return new InsertQueryBuilder(tableName);
    }

    public DeleteQueryBuilder createDeleteQueryBuilder(String tableName) {
        return new DeleteQueryBuilder(tableName);
    }

    public static QueryBuilderFactory getInstance() {
        return QueryBuilderFactoryHolder.instance;
    }
}
