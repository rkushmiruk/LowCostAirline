package com.kushmiruk.dao.impl.util;

import org.apache.log4j.Logger;

/**
 * Util class for build Update queries
 */
public class UpdateQueryBuilder {
    private final Logger LOGGER = Logger.getLogger(UpdateQueryBuilder.class.getName());
    private StringBuilder query;
    private String tableName;

    public UpdateQueryBuilder(String tableName){
        queryInit();
        this.tableName = tableName.toLowerCase();
    }

    private void queryInit() {
        query = new StringBuilder();
        query.append("Update ");
    }

}
