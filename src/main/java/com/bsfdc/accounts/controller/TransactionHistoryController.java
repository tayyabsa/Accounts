package com.bsfdc.accounts.controller;

import com.bsfdc.accounts.dto.AccountsDto;
import com.bsfdc.accounts.dto.TransactionHistoryDto;
import com.bsfdc.accounts.entity.TransactionHistory;
import com.bsfdc.accounts.service.TransactionHistoryService;
import com.bsfdc.accounts.service.impl.TransactionHistoryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transaction-history")
@AllArgsConstructor
public class TransactionHistoryController {

    private final TransactionHistoryService transactionHistoryService;

    @GetMapping("/{id}")
    public ResponseEntity<List<TransactionHistoryDto>> getTransactionHistory(@PathVariable(name = "id") String accountId) {
        return ResponseEntity.ok(transactionHistoryService.fetchTransactionHistory(accountId));
    }

}
