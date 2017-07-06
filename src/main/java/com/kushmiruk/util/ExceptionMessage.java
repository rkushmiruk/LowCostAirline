package com.kushmiruk.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Util class for exception message constants
 */
public final class ExceptionMessage {
    public static final String ERROR_CONNECTION_INIT = "Error in database connection init";
    public static final String ERROR_PAGE = "Error page found ";
    public static final String MESSAGE_ERROR = "messageError";
    public static final String ERROR_DETAILS = "errorDetails";
    public static final String CITY_PATTERN_ERROR = "city.pattern.error";
    public static final String DATE_PATTERN_ERROR = "date.pattern.error";
    public static final String LOGIN_PATTERN_ERROR = "login.pattern.error";
    public static final String PASSWORD_PATTERN_ERROR = "password.pattern.error";
    public static final String NAME_PATTERN_ERROR = "name.pattern.error";
    public static final String EMAIL_PATTERN_ERROR = "email.pattern.error";
    public static final String EMAIL_EXIST_ERROR = "email.exist.error";
    public static final String FLIGHT_NOT_FOUND_ERROR = "flight.not.found.error";
    public static final String LOGIN_EXIST_ERROR = "login.exist.error";
    public static final String ID_NOT_FOUND_ERROR = "id.not.found.error";
    public static final String DELETE_ERROR = "delete.error";


    public static final Locale ENGLISH = new Locale("en", "US");
    public static final Locale UKRAINIAN = new Locale("uk", "UA");

    private static final String BUNDLE_NAME = "/i18n/exceptions";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, ENGLISH);

    public static void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
    }

    public static String getMessage(String key) {
        return resourceBundle.getString(key);
    }
}
