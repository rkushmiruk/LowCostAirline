package com.kushmiruk.util;

/**
 * Util class for queries part for sql builders and sql queries
 */
public final class QueryMessage {
    public static final String SELECT = "SELECT ";
    public static final String UPDATE = "UPDATE ";
    public static final String INSERT = "INSERT INTO ";
    public static final String DELETE = "DELETE FROM";
    public static final String SIGN_ALL = " *";
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


    public static final String FIND_TICKET_ORDERS = "  Select ticket_order.id, user_id,datetime, payment_method  from user_authentication\n" +
            "  inner join user On user_authentication.id = auth_id\n" +
            "  inner join ticket_order On user.id = user_id\n" +
            "  where user_authentication.LOGIN = ?";

    public static final String FIND_ID = "SELECT MAX(id) from ";

    public static final String ORDER_DETAILS = "Select ticket.id,order_id,flight_id,status_id,extra_price_id,baggage_id,passenger_first_name,passenger_last_name,"
            + "passenger_email,has_priority_registration, has_baggage, price, seat_number"
            + " from ticket inner join ticket_order on ticket_order.id = order_id where ticket_order.id = ?";

    public static final String FIND_TICKET_ID_BY_FLIGHT_AND_SEAT_NUMBER = "Select id from ticket where flight_id = ? and seat_number = ?";

    public static final String FIND_BY_LOGIN = "Select user.id ,first_name, last_name, email,role_id, auth_id  "
            + "from user inner join user_authentication on auth_id = user_authentication.id where login = ?";
    
    public static final String COUNT_TABLE_ROWS = "SELECT COUNT(id) as 'count' FROM ticket_order";
}


