package com.kushmiruk.exception;

/**
 * Base application exception
 */
public class AppException extends Exception {

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}

