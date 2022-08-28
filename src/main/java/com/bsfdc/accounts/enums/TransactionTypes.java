package com.bsfdc.accounts.enums;

import lombok.Getter;

@Getter
public enum TransactionTypes {

    DEBIT(0),
    CREDIT(1);

    private Integer transactionType;

    TransactionTypes(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public static TransactionTypes getTransactionTypeEnum(Integer transactionType) {
        return transactionType == 0 ? TransactionTypes.DEBIT : TransactionTypes.CREDIT;
    }
}
