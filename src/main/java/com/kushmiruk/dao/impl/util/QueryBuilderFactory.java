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

    public SelectQueryBuilder createSelectQueryBuilder() {
        return new SelectQueryBuilder();
    }

    public UpdateQueryBuilder createUpdateQueryBuilder() {
        return new UpdateQueryBuilder();
    }

    public InsertQueryBuilder createInsertQueryBuilder() {
        return new InsertQueryBuilder();
    }

    public DeleteQueryBuilder createDeleteQueryBuilder() {
        return new DeleteQueryBuilder();
    }

    public static QueryBuilderFactory getInstance() {
        return QueryBuilderFactoryHolder.instance;
    }
}
