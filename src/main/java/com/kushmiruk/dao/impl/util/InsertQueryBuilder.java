package com.kushmiruk.dao.impl.util;

import com.kushmiruk.util.QueryMessage;
import org.apache.log4j.Logger;

/**
 * Util class for build Insert queries
 */
public class InsertQueryBuilder {
    private final Logger LOGGER = Logger.getLogger(InsertQueryBuilder.class.getName());
    private StringBuilder query;
    private String tableName;

    public InsertQueryBuilder(String tableName){
        this.tableName = tableName.toLowerCase();
        queryInit();
    }

    public InsertQueryBuilder addValue(String value){


        return this;
    }

    private void queryInit() {
        query = new StringBuilder();
        query.append(QueryMessage.INSERT);
        query.append(tableName);
    }


}
