package com.kushmiruk.dao.impl.util;

import com.kushmiruk.util.LoggerMessage;
import com.kushmiruk.util.QueryMessage;
import org.apache.log4j.Logger;

/**
 * Util class for build Select queries
 */
public class SelectQueryBuilder {
    private final Logger LOGGER = Logger.getLogger(SelectQueryBuilder.class.getName());
    private static final int NUMBER_OF_FINISH_SYMBOLS_IN_QUERY = 2;
    private StringBuilder query;
    private String tableName;


    public SelectQueryBuilder(String tableName) {
        queryInit();
        this.tableName = tableName.toLowerCase();
    }

    public SelectQueryBuilder addField(String field) {
        query
                .append(tableName)
                .append(QueryMessage.DOT)
                .append(field)
                .append(QueryMessage.COMMA);

        return this;
    }

    public SelectQueryBuilder getAll() {
        query.append(QueryMessage.SIGH_ALL);
        return this;
    }

    public SelectQueryBuilder from() {
        query.deleteCharAt(query.length() - NUMBER_OF_FINISH_SYMBOLS_IN_QUERY);
        query
                .append('\n')
                .append(QueryMessage.FROM)
                .append(tableName)
                .append(QueryMessage.SEMICOLON);
        return this;
    }

    public SelectQueryBuilder join(JoinType type, Class<?> classFrom, String joinField, String similarField) {
        query.deleteCharAt(query.length() - NUMBER_OF_FINISH_SYMBOLS_IN_QUERY);
        query
                .append('\n')
                .append(type)
                .append(QueryMessage.JOIN)
                .append(classFrom.getSimpleName())
                .append(QueryMessage.ON)
                .append(tableName)
                .append(QueryMessage.DOT)
                .append(joinField)
                .append(QueryMessage.EQUAL)
                .append(classFrom.getSimpleName())
                .append(QueryMessage.DOT)
                .append(similarField)
                .append(QueryMessage.SEMICOLON);
        return this;
    }

    public SelectQueryBuilder where() {
        query.deleteCharAt(query.length() - NUMBER_OF_FINISH_SYMBOLS_IN_QUERY);
        query
                .append('\n')
                .append(QueryMessage.WHERE);
        return this;
    }

    public SelectQueryBuilder condition(String field) {
        query
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

    public String build() {
        LOGGER.info(LoggerMessage.BUILD_SELECT_QUERY + query.toString());
        StringBuilder tmp = query;
        queryInit();
        if (tmp.toString().contains(QueryMessage.WHERE)
                || tmp.charAt(tmp.length() - NUMBER_OF_FINISH_SYMBOLS_IN_QUERY) != QueryMessage.COMMA) {
            return tmp.toString();
        } else {
            return query
                    .deleteCharAt(tmp.length() - NUMBER_OF_FINISH_SYMBOLS_IN_QUERY)
                    .toString();

        }
    }

    private void queryInit() {
        query = new StringBuilder();
        query.append(QueryMessage.SELECT);
    }

}

