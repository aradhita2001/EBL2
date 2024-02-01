package com.wecp.progressive.exception;

public class OutOfBalanceException extends Exception{

    public OutOfBalanceException() {
    }

    public OutOfBalanceException(String message) {
        super(message);
    }

    public OutOfBalanceException(Throwable cause) {
        super(cause);
    }

    public OutOfBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfBalanceException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}