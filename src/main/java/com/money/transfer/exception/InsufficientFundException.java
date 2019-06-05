package com.money.transfer.exception;

/**
 * Created by cheikhniass on 11/7/15.
 */
public class InsufficientFundException extends  Exception {
    public InsufficientFundException(String message) {
        super(message);
    }
}
