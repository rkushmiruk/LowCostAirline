package com.kushmiruk.util;

/**
 * Util class for queries part for sql builders and sql queries
 */
public final class QueryMessage {
    public static final String SELECT = "SELECT ";
    public static final String UPDATE = "UPDATE ";
    public static final String INSERT = "INSERT INTO ";
    public static final String DELETE = "DELETE FROM";
    public static final String SIGN_ALL=" *";
    public static final String ID = "id";
    public static final Character DOT = '.';
    public static final Character COMMA = ',';
    public static final String FROM = "FROM  ";
    public static final String SEMICOLON = "; ";
    public static final String JOIN = " JOIN ";
    public static final String ON = " ON ";
    public static final String EQUAL = " = ";
    public static final String WHERE = " WHERE ";
    public static final String LIMIT = " LIMIT ";
    public static final String QUESTION_MARK = "?";
    public static final String SPACE = " ";
    public static final String CLOSE_PARENTHESIS = ")";
    public static final String OPEN_PARENTHESIS = "(";
    public static final String VALUES = "VALUES";
    public static final String SET = "SET";
    public static final String LOGIN = "login";
    
    public static final String FIND_FLIGHTS = "Select * from flight \n" +
"  inner join airport dep on departure_airport_id =  dep.id\n" +
"  inner join airport des on destination_airport_id  = des.id\n" +
"  inner join city departure on dep.city_id = departure.id\n" +
"  inner join city destination on des.city_id = destination.id\n" +
"  where departure.name = ? AND destination.name=? AND DATE(departure_datetime) = ?";

    
    public static final String FIND_TICKET_ORDERS = "Select * from user_authentication\n" +
"  inner join user On user_authentication.id = auth_id\n" +
"  inner join ticket_order On user.id = user_id\n" +
"  inner join ticket On ticket_order.id = order_id\n" +
"  inner join flight On flight_id = flight.id\n" +
"  inner join airport dep on departure_airport_id =  dep.id\n" +
"  inner join airport des on destination_airport_id  = des.id\n" +
"  inner join city departure on dep.city_id = departure.id\n" +
"  inner join city destination on des.city_id = destination.id\n" +
"  where user_authentication.LOGIN = ?";
}
