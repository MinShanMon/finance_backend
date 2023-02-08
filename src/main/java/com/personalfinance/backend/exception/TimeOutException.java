package com.personalfinance.backend.exception;

public class TimeOutException  extends RuntimeException{
    public TimeOutException() {
        super();
    }

    public TimeOutException(String message) {
        super(message);
    }

    public TimeOutException(String message, Throwable cause) {
        super(message, cause);
    }
}

