package com.wecp.progressive.exception;

public class WithdrawalLimitException extends RuntimeException{

    public WithdrawalLimitException() {
    }

    public WithdrawalLimitException(String message) {
        super(message);
    }

    public WithdrawalLimitException(Throwable cause) {
        super(cause);
    }

    public WithdrawalLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public WithdrawalLimitException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}