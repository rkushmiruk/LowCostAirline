package com.kushmiruk.dao.impl.util;

/**
 * Abstract sql builder
 */
public abstract class QueryBuilder {
    protected String tableName;
    protected static final int NUMBER_OF_FINISH_SYMBOLS_IN_QUERY = 2;

    /**
     * Build our finish query
     *
     * @return String query
     */
    public abstract String build();

    /**
     * Add table to our current query
     *
     * @param tableName
     */
    public abstract QueryBuilder addTable(String tableName);

}
