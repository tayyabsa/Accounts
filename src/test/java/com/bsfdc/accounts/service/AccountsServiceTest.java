package com.bsfdc.accounts.service;

import com.bsfdc.accounts.dto.AccountTransferRequestDto;
import com.bsfdc.accounts.dto.AccountsDto;
import com.bsfdc.accounts.entity.Accounts;
import com.bsfdc.accounts.exception.ApplicationException;
import com.bsfdc.accounts.exception.NoDataFoundException;
import com.bsfdc.accounts.repository.AccountsRepository;
import com.bsfdc.accounts.repository.TransactionHistoryRepository;
import com.bsfdc.accounts.service.impl.AccountsServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
public class AccountsServiceTest {

    @Autowired
    private AccountsRepository accountsRepository;

    @Mock
    private AccountsLockService accountsLockService;

    @Mock
    private TransactionHistoryRepository transactionHistoryRepository;

    private AccountsServiceImpl accountsService;

    @Before
    public void beforeAll() {
        accountsService = new AccountsServiceImpl(accountsRepository, accountsLockService, transactionHistoryRepository);
    }

    @Test
    public void getAccountDetailsTest() {

        AccountsDto accountDetails = accountsService.getAccountDetails("f12c1e4d-9838-4ca3-b5d7-b38b4bc4f5ee");
        Assert.assertEquals(Long.valueOf(1), accountDetails.getUserId());
        Assert.assertEquals(new BigDecimal("100.00"), accountDetails.getBalance());
    }

    @Test
    public void getAccountDetailsWithNoAccountRecordTest() {
        Assert.assertThrows(NoDataFoundException.class, () -> accountsService.getAccountDetails("f12c1e4d-9838-4ca3-b5d7-b38b4bc4f5e1"));
    }

    @Test
    public void transferAmountTest() {

        doNothing().when(accountsLockService).lock(anyString());
        doNothing().when(accountsLockService).unlock(anyString());

        AccountTransferRequestDto accountTransferRequestDto = AccountTransferRequestDto.builder()
                .sourceAccountId("f12c1e4d-9838-4ca3-b5d7-b38b4bc4f5ee")
                .destinationAccountId("5b73c660-2f3e-4bcb-bc42-3202128a54eb")
                .amount(new BigDecimal("10.00"))
                .build();
        AccountsDto account = accountsService.transferAmount(accountTransferRequestDto);
        BigDecimal destinationBalance = accountsService.getAccountDetails("5b73c660-2f3e-4bcb-bc42-3202128a54eb").getBalance();
        Assert.assertEquals(new BigDecimal("90.00"), account.getBalance());
        Assert.assertEquals(new BigDecimal("120.00"), destinationBalance);
    }

    @Test
    public void transferAmountWithInsufficientBalance() {
        doNothing().when(accountsLockService).lock(anyString());
        doNothing().when(accountsLockService).unlock(anyString());
        AccountTransferRequestDto accountTransferRequestDto = AccountTransferRequestDto.builder()
                .sourceAccountId("f12c1e4d-9838-4ca3-b5d7-b38b4bc4f5ee")
                .destinationAccountId("5b73c660-2f3e-4bcb-bc42-3202128a54eb")
                .amount(new BigDecimal("1000.00"))
                .build();
        Assert.assertThrows(ApplicationException.class, () -> accountsService.transferAmount(accountTransferRequestDto));
    }
}
