package com.kushmiruk.dao.impl.util;

import org.apache.log4j.Logger;

/**
 * Util class for build Delete queries
 */
public class DeleteQueryBuilder {
    private final Logger LOGGER = Logger.getLogger(DeleteQueryBuilder.class.getName());
    private StringBuilder query;
    private String tableName;

    public DeleteQueryBuilder(String tableName){
        queryInit();
        this.tableName = tableName.toLowerCase();
    }

    private void queryInit() {
        query = new StringBuilder();
        query.append("Update ");
    }
}
