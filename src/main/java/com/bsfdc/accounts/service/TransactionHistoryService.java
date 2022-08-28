package com.bsfdc.accounts.service;

import com.bsfdc.accounts.dto.TransactionHistoryDto;

import java.util.List;

public interface TransactionHistoryService {

    List<TransactionHistoryDto> fetchTransactionHistory(String accountId);
}
