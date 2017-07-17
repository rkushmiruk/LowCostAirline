package com.kushmiruk.dao.impl.util;

import com.kushmiruk.util.QueryMessage;

/**
 * Util class for build Delete queries
 */
public class DeleteQueryBuilder extends QueryBuilder {
    private StringBuilder query;

    public DeleteQueryBuilder() {
        queryInit();
    }

    public DeleteQueryBuilder table(String tableName) {
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
        StringBuilder tmp = query;
        queryInit();
        return tmp.toString();
    }

    private void queryInit() {
        query = new StringBuilder();
        query.append(QueryMessage.DELETE);
    }

}
