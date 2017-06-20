package com.kushmiruk.dao.impl.util;

import org.apache.log4j.Logger;

/**
 * Util class for build Select queries
 */
public class SelectQueryBuilder {
    private final Logger LOGGER = Logger.getLogger(SelectQueryBuilder.class.getName());
    private StringBuilder query;
    public String tableName;

    public SelectQueryBuilder(String tableName) {
        queryInit();
        this.tableName = tableName.toLowerCase();
    }

    public SelectQueryBuilder addField(String field) {
        query
                .append(tableName)
                .append(".")
                .append(field)
                .append(", ");

        return this;
    }

    public SelectQueryBuilder from() {
        query.deleteCharAt(query.length() - 2);
        query
                .append('\n')
                .append("FROM")
                .append(" ")
                .append(tableName)
                .append("; ");
        return this;
    }

    public SelectQueryBuilder join(JoinType type, Class<?> classFrom, String joinField, String commonField2) {
        query.deleteCharAt(query.length() - 1);
        query
                .append('\n')
                .append(type)
                .append(" JOIN ")
                .append(classFrom.getSimpleName())
                .append(" ON ")
                .append(tableName)
                .append(".")
                .append(joinField)
                .append(" = ")
                .append(classFrom.getSimpleName())
                .append(".")
                .append(commonField2)
                .append("; ");
        return this;
    }

    public SelectQueryBuilder where() {
        query.deleteCharAt(query.length() - 2);
        query
                .append('\n')
                .append("where ");
        return this;
    }

    public SelectQueryBuilder condition(String field) {
        query
                .append(tableName)
                .append(".")
                .append(field)
                .append(" = ?");
        return this;
    }

    public SelectQueryBuilder limit(int start, int end) {
        query
                .deleteCharAt(query.length() - 2)
                .append(" Limit ")
                .append(start)
                .append(", ")
                .append(end)
                .append(";");
        return this;
    }

    public SelectQueryBuilder limit(int total) {
        query
                .deleteCharAt(query.length() - 2)
                .append(" Limit ")
                .append(total)
                .append(";");
        return this;

    }

    public String build() {
        StringBuilder tmp = query;
        queryInit();
        if (tmp.toString().contains("where") || tmp.charAt(tmp.length() - 2) != ',') {
            return tmp.toString();
        } else {
            return query
                    .deleteCharAt(tmp.length() - 2)
                    .toString();

        }
    }

    private void queryInit() {
        query = new StringBuilder();
        query.append("SELECT ");
    }

}

