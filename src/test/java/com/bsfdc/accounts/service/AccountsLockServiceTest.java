package com.bsfdc.accounts.service;

import com.bsfdc.accounts.exception.ApplicationException;
import com.bsfdc.accounts.lock.LockManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class AccountsLockServiceTest {

    @Mock
    private LockManager lockManager;

    @InjectMocks
    private AccountsLockService accountsLockService;

    @Test
    public void lockTest(){

        Mockito.when(lockManager.lock(any(), any())).thenReturn(true);
        accountsLockService.lock("TEMP");
        Mockito.verify(lockManager).lock("account-service-lock::"+"TEMP",3000L);
    }

    @Test
    public void unableToAcquireLockTest(){

        Mockito.when(lockManager.lock(any(), any())).thenReturn(false);
        Assert.assertThrows(ApplicationException.class, () -> accountsLockService.lock("TEMP"));
    }

    @Test
    public void unlockTest() {

    }
}
