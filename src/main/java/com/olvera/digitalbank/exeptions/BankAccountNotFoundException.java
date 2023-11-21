package com.olvera.digitalbank.exeptions;

public class BankAccountNotFoundException extends Exception {

    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
