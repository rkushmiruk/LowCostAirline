package com.kushmiruk.exception;

/**
 * Base Dao exception
 */
public class DaoException extends Exception {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable source) {
        super(source);
    }

    public DaoException(String message, Throwable source) {
        super(message, source);
    }
}
