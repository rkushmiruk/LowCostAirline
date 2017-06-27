package com.kushmiruk.dao.impl.util;

import com.kushmiruk.util.LoggerMessage;
import com.kushmiruk.util.QueryMessage;
import org.apache.log4j.Logger;

/**
 * Util class for build Select queries
 */
public class SelectQueryBuilder extends QueryBuilder {
    private final Logger LOGGER = Logger.getLogger(SelectQueryBuilder.class.getName());
    private StringBuilder query;

    public SelectQueryBuilder() {
        queryInit();
    }

    public SelectQueryBuilder addTable(String tableName) {
        this.tableName = tableName.toLowerCase();
        return this;
    }

    public SelectQueryBuilder addField(String field) {
        query
                .append(field)
                .append(QueryMessage.SPACE)
                .append(QueryMessage.COMMA)
                .append(QueryMessage.SPACE);
        return this;
    }

    public SelectQueryBuilder getAll() {
        query.append(QueryMessage.SIGN_ALL);
        return this;
    }

    public SelectQueryBuilder from() {
        query.deleteCharAt(query.length() - NUMBER_OF_FINISH_SYMBOLS_IN_QUERY);
        query
                .append('\n')
                .append(QueryMessage.FROM)
                .append(tableName);
        return this;
    }

    public SelectQueryBuilder join(JoinType type, String joinTableName, String joinField, String similarField) {
        query
                .append(QueryMessage.SPACE)
                .append(type)
                .append(QueryMessage.JOIN)
                .append(joinTableName)
                .append(QueryMessage.ON)
                .append(tableName)
                .append(QueryMessage.DOT)
                .append(joinField)
                .append(QueryMessage.EQUAL)
                .append(joinTableName)
                .append(QueryMessage.DOT)
                .append(similarField);
        return this;
    }

    public SelectQueryBuilder condition(String field) {
        query
                .append(QueryMessage.WHERE)
                .append(tableName)
                .append(QueryMessage.DOT)
                .append(field)
                .append(QueryMessage.EQUAL + QueryMessage.QUESTION_MARK);
        return this;
    }

    public SelectQueryBuilder limit(int start, int end) {
        query
                .deleteCharAt(query.length() - NUMBER_OF_FINISH_SYMBOLS_IN_QUERY)
                .append(QueryMessage.LIMIT)
                .append(start)
                .append(QueryMessage.COMMA)
                .append(end)
                .append(QueryMessage.SEMICOLON);
        return this;
    }

    public SelectQueryBuilder limit(int total) {
        query
                .deleteCharAt(query.length() - NUMBER_OF_FINISH_SYMBOLS_IN_QUERY)
                .append(QueryMessage.LIMIT)
                .append(total)
                .append(QueryMessage.SEMICOLON);
        return this;

    }

    public SelectQueryBuilder orderBy(String column){
        query
                .append(QueryMessage.SPACE)
                .append("ORDER BY")
                .append(QueryMessage.SPACE)
                .append(column)
                .append(QueryMessage.SPACE)
                .append("ASC");
        return this;
    }

    public String build() {
        LOGGER.info(LoggerMessage.BUILD_SELECT_QUERY + query.toString());
        StringBuilder tmp = query;
        queryInit();
        if (tmp.toString().contains(QueryMessage.WHERE)
                || tmp.charAt(tmp.length() - NUMBER_OF_FINISH_SYMBOLS_IN_QUERY) != QueryMessage.COMMA) {
            return tmp.toString();
        } else {
            return tmp
                    .deleteCharAt(tmp.length() - NUMBER_OF_FINISH_SYMBOLS_IN_QUERY)
                    .toString();

        }
    }

    private void queryInit() {
        query = new StringBuilder();
        query.append(QueryMessage.SELECT);
    }

}

