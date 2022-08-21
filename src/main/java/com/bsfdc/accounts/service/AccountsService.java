package com.bsfdc.accounts.service;

import com.bsfdc.accounts.dto.AccountTransferRequestDto;
import com.bsfdc.accounts.dto.AccountsDto;
import org.springframework.stereotype.Service;

import java.util.UUID;


public interface AccountsService {
    AccountsDto getAccountDetails(String userId);

    AccountsDto transferAmount(AccountTransferRequestDto accountTransferRequest);
}
