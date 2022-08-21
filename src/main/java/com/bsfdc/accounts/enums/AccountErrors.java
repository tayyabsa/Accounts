package com.bsfdc.accounts.enums;

import lombok.Getter;

@Getter
public enum AccountErrors {

    ACCOUNT_NOT_FOUND("404", "Account not found"),
    INSUFFICIENT_BALANCE("400", "Insufficient balance in source account"),
    UNABLE_TO_ACQUIRE_LOCK("4000", "Unable to Acquire Lock");

    private final String code;
    private final String msg;

    AccountErrors(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}