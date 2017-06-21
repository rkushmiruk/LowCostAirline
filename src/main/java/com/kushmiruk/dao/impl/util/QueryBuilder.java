package com.kushmiruk.dao.impl.util;

public abstract class QueryBuilder {
    protected StringBuilder query;
    protected String tableName;

    protected QueryBuilder(){
        this.tableName = tableName.toLowerCase();
    }
}
