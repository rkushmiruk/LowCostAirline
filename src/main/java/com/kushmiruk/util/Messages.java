package com.kushmiruk.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Util class for i18n messages to user on jsp
 */
public final class Messages {
    public static final String successMessage = "Registration success: ";

    public static final Locale ENGLISH = new Locale("en", "US");
    public static final Locale UKRAINIAN = new Locale("uk", "UA");

    private static final String BUNDLE_NAME = "/i18n/messages";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, ENGLISH);
    
    public static void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
    }

    public static String getMessage(String key) {
        return resourceBundle.getString(key);
    }
}
