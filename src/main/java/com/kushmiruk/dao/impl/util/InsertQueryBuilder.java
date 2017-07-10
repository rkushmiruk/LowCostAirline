package com.kushmiruk.dao.impl.util;

import com.kushmiruk.util.LoggerMessage;
import com.kushmiruk.util.QueryMessage;
import org.apache.log4j.Logger;

/**
 * Util class for build Insert queries
 */
public class InsertQueryBuilder extends QueryBuilder {
    private final Logger LOGGER = Logger.getLogger(InsertQueryBuilder.class.getName());
    private StringBuilder query;
    private int count = 0;

    public InsertQueryBuilder() {
        queryInit();
    }

    public InsertQueryBuilder table(String tableName) {
        this.tableName = tableName.toLowerCase();
        query
                .append(this.tableName)
                .append(QueryMessage.SPACE)
                .append(QueryMessage.OPEN_PARENTHESIS);
        return this;
    }

    public InsertQueryBuilder addValues(String[] values) {
        for (String value : values) {
            query
                    .append(value)
                    .append(QueryMessage.COMMA)
                    .append(QueryMessage.SPACE);
        }
        query.deleteCharAt(query.length() - NUMBER_OF_FINISH_SYMBOLS_IN_QUERY);
        query
                .append(QueryMessage.CLOSE_PARENTHESIS)
                .append(QueryMessage.SPACE)
                .append(QueryMessage.VALUES)
                .append(QueryMessage.SPACE)
                .append(QueryMessage.OPEN_PARENTHESIS);
        for (String value : values) {
            query
                    .append(QueryMessage.QUESTION_MARK)
                    .append(QueryMessage.COMMA);
        }
        query.deleteCharAt(query.length() - 1);
        query.append(QueryMessage.CLOSE_PARENTHESIS);
        return this;
    }

    public InsertQueryBuilder addValue(String value) {
        count++;
        query.deleteCharAt(query.length() - NUMBER_OF_FINISH_SYMBOLS_IN_QUERY);
        query
                .append(value)
                .append(QueryMessage.COMMA)
                .append(QueryMessage.CLOSE_PARENTHESIS)
                .append(QueryMessage.SPACE);
        return this;
    }

    public InsertQueryBuilder Values() {
        query.deleteCharAt(query.length() - 1);
        query.deleteCharAt(query.length() - NUMBER_OF_FINISH_SYMBOLS_IN_QUERY);
        query
                .append(QueryMessage.SPACE)
                .append(QueryMessage.VALUES)
                .append(QueryMessage.SPACE)
                .append(QueryMessage.OPEN_PARENTHESIS);
        for (int i = 0; i < count; i++) {
            query
                    .append(QueryMessage.QUESTION_MARK)
                    .append(QueryMessage.COMMA + " ");
        }
        query.deleteCharAt(query.length() - NUMBER_OF_FINISH_SYMBOLS_IN_QUERY);
        query.append(QueryMessage.CLOSE_PARENTHESIS);
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
        query.append(QueryMessage.INSERT);
    }


}
