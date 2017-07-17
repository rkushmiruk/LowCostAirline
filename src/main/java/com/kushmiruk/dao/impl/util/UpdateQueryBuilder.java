package com.kushmiruk.dao.impl.util;

import com.kushmiruk.util.QueryMessage;

/**
 * Util class for build Update queries
 */
public class UpdateQueryBuilder extends QueryBuilder {
    private StringBuilder query;

    public UpdateQueryBuilder() {
        queryInit();
    }

    public UpdateQueryBuilder table(String tableName) {
        this.tableName = tableName.toLowerCase();
        query
                .append(this.tableName)
                .append(QueryMessage.SPACE)
                .append(QueryMessage.SET)
                .append(QueryMessage.SPACE);
        return this;
    }

    public UpdateQueryBuilder addValues(String[] values) {
        for (String value : values) {
            query
                    .append(value)
                    .append(QueryMessage.EQUAL)
                    .append(QueryMessage.QUESTION_MARK)
                    .append(QueryMessage.COMMA)
                    .append(QueryMessage.SPACE);
        }
        query.deleteCharAt(query.length() - NUMBER_OF_FINISH_SYMBOLS_IN_QUERY);
        return this;
    }

    public UpdateQueryBuilder condition(String field) {
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
        query.append(QueryMessage.UPDATE);
    }
}
