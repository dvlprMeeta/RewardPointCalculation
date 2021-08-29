package com.infogain.retailer.exception;

public class UserNameNotFoundException extends Exception{
    private static final long serialVersionUID = -3370756120904647667L;

    public UserNameNotFoundException() {
        super();
    }

    public UserNameNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UserNameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameNotFoundException(String message) {
        super(message);
    }

    public UserNameNotFoundException(Throwable cause) {
        super(cause);
    }
}
