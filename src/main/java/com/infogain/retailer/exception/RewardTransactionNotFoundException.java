package com.infogain.retailer.exception;

public class RewardTransactionNotFoundException extends Exception{
    private static final long serialVersionUID = -3370756120904647667L;

    public RewardTransactionNotFoundException() {
        super();
    }

    public RewardTransactionNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RewardTransactionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RewardTransactionNotFoundException(String message) {
        super(message);
    }

    public RewardTransactionNotFoundException(Throwable cause) {
        super(cause);
    }
}
