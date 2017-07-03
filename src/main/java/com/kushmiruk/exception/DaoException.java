package com.kushmiruk.exception;

/**
 * Base Dao exception
 */
public class DaoException extends AppException {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable source) {
        super(message, source);
    }
}
