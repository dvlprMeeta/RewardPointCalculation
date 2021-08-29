package com.infogain.retailer.exception;

public class RetailerAuthenticationException extends RuntimeException{
    private static final long serialVersionUID = -3370756120904647667L;

    public RetailerAuthenticationException() {
        super();
    }

    public RetailerAuthenticationException(String message, Throwable cause, boolean enableSuppression,
                                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RetailerAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RetailerAuthenticationException(String message) {
        super(message);
    }

    public RetailerAuthenticationException(Throwable cause) {
        super(cause);
    }
}
