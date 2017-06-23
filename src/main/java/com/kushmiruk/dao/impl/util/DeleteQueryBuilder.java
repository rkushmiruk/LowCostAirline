package com.kushmiruk.dao.impl.util;

import com.kushmiruk.util.LoggerMessage;
import com.kushmiruk.util.QueryMessage;
import org.apache.log4j.Logger;

/**
 * Util class for build Delete queries
 */
public class DeleteQueryBuilder extends QueryBuilder {
    private final Logger LOGGER = Logger.getLogger(DeleteQueryBuilder.class.getName());
    private StringBuilder query;

    public DeleteQueryBuilder() {
        queryInit();
    }

    public DeleteQueryBuilder addTable(String tableName) {
        this.tableName = tableName.toLowerCase();
        query
                .append(QueryMessage.SPACE)
                .append(this.tableName);
        return this;
    }

    public DeleteQueryBuilder condition(String field) {
        query
                .append(QueryMessage.WHERE)
                .append(tableName)
                .append(QueryMessage.DOT)
                .append(field)
                .append(QueryMessage.EQUAL + QueryMessage.QUESTION_MARK);
        return this;
    }

    public String build() {
        LOGGER.info(LoggerMessage.BUILD_INSERT_QUERY + query.toString());
        StringBuilder tmp = query;
        queryInit();
        return tmp.toString();
    }

    private void queryInit() {
        query = new StringBuilder();
        query.append(QueryMessage.DELETE);
    }

}
