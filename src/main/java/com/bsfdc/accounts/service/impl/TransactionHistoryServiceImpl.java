package com.bsfdc.accounts.service.impl;

import com.bsfdc.accounts.dto.TransactionHistoryDto;
import com.bsfdc.accounts.entity.TransactionHistory;
import com.bsfdc.accounts.enums.TransactionTypes;
import com.bsfdc.accounts.repository.TransactionHistoryRepository;
import com.bsfdc.accounts.service.TransactionHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    private final TransactionHistoryRepository transactionHistoryRepository;

    @Override
    public List<TransactionHistoryDto> fetchTransactionHistory(String accountId) {
        return transactionHistoryRepository.findByAccountId(accountId)
                .stream().map(this::toTransactionHistoryDto)
                .collect(Collectors.toList());
    }


    public TransactionHistoryDto toTransactionHistoryDto(TransactionHistory transactionHistoryEntity){
        return TransactionHistoryDto.builder()
                .id(transactionHistoryEntity.getId())
                .accountId(transactionHistoryEntity.getAccountId())
                .amount(transactionHistoryEntity.getAmount())
                .transactionType(TransactionTypes.getTransactionTypeEnum(transactionHistoryEntity.getTransactionType())).build();
    }
}
