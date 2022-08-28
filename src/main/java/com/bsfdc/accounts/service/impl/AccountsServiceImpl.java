package com.bsfdc.accounts.service.impl;

import com.bsfdc.accounts.dto.AccountTransferRequestDto;
import com.bsfdc.accounts.dto.AccountsDto;
import com.bsfdc.accounts.entity.Accounts;
import com.bsfdc.accounts.entity.TransactionHistory;
import com.bsfdc.accounts.enums.AccountErrors;
import com.bsfdc.accounts.enums.TransactionTypes;
import com.bsfdc.accounts.exception.ApplicationException;
import com.bsfdc.accounts.exception.NoDataFoundException;
import com.bsfdc.accounts.repository.AccountsRepository;
import com.bsfdc.accounts.repository.TransactionHistoryRepository;
import com.bsfdc.accounts.service.AccountsLockService;
import com.bsfdc.accounts.service.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.MathContext;
import java.math.RoundingMode;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;
    private final AccountsLockService accountsLockService;
    private final TransactionHistoryRepository transactionHistoryRepository;

    @Override
    public AccountsDto getAccountDetails(String accountId) {
        return accountsRepository.findByAccountId(accountId)
                .map(Accounts::toAccountsDto)
                .orElseThrow(() -> new NoDataFoundException(AccountErrors.ACCOUNT_NOT_FOUND.getCode(), AccountErrors.ACCOUNT_NOT_FOUND.getMsg()));
    }

    @Override
    @Transactional
    public AccountsDto transferAmount(AccountTransferRequestDto accountTransferRequest) {

        AccountsDto sourceAccountDetails;
        try {
            accountsLockService.lock(accountTransferRequest.getSourceAccountId());
            accountsLockService.lock(accountTransferRequest.getDestinationAccountId());
            sourceAccountDetails = getAccountDetails(accountTransferRequest.getSourceAccountId());
            if(sourceAccountDetails.getBalance().compareTo(accountTransferRequest.getAmount()) < 0) {
                throw new ApplicationException(AccountErrors.INSUFFICIENT_BALANCE.getCode(), AccountErrors.INSUFFICIENT_BALANCE.getMsg());
            }
            AccountsDto destinationAccountDetails = getAccountDetails(accountTransferRequest.getDestinationAccountId());

            TransactionHistory debitTransaction = TransactionHistory.builder()
                    .amount(accountTransferRequest.getAmount())
                    .accountId(accountTransferRequest.getSourceAccountId())
                    .transactionType(TransactionTypes.DEBIT.getTransactionType())
                    .build();

            TransactionHistory creditTransaction = TransactionHistory.builder()
                    .amount(accountTransferRequest.getAmount())
                    .accountId(accountTransferRequest.getDestinationAccountId())
                    .transactionType(TransactionTypes.CREDIT.getTransactionType())
                    .build();

            transactionHistoryRepository.save(debitTransaction);
            transactionHistoryRepository.save(creditTransaction);

            sourceAccountDetails.setBalance(sourceAccountDetails.getBalance().subtract(accountTransferRequest.getAmount()));
            destinationAccountDetails.setBalance(destinationAccountDetails.getBalance().add(accountTransferRequest.getAmount()));
            accountsRepository.save(sourceAccountDetails.toAccountsEntity());
            accountsRepository.save(destinationAccountDetails.toAccountsEntity());
        } finally {
            accountsLockService.unlock(accountTransferRequest.getSourceAccountId());
            accountsLockService.unlock(accountTransferRequest.getDestinationAccountId());
        }
        return sourceAccountDetails;
    }

    // Taking a pesemistic lock 
}
