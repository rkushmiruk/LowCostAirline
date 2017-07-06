package com.kushmiruk.util;

/**
 * Util class for regex pattern to validate our inputs
 */
public final class RegexPattern {
    public static final String CITY_PATTERN = "[A-Za-z]{3,30}";
    public static final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";
    public static final String LOGIN_PATTERN = "[A-Za-z0-9]{2,30}";
    public static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,30})";
    public static final String NAME_PATTERN = "[A-Za-z]{2,30}";
    public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
}
