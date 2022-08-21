package com.bsfdc.accounts.service;

import com.bsfdc.accounts.enums.AccountErrors;
import com.bsfdc.accounts.exception.ApplicationException;
import com.bsfdc.accounts.lock.LockManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountsLockService {

    private static final Long DEFAULT_TTL_IN_MILLIS = 3000L;
    private static final String LOCK_KEY_PREFIX = "account-service-lock::";

    private final LockManager lockManager;

    public AccountsLockService(@Qualifier("redissonLockManager") LockManager lockManager) {
        this.lockManager = lockManager;
    }

    public void lock(String lockKey) {
        log.info("Acquiring lock for lockKey = {}", lockKey);

        boolean locked = lockManager.lock(LOCK_KEY_PREFIX + lockKey, DEFAULT_TTL_IN_MILLIS);
        if(!locked) {
            throw new ApplicationException(AccountErrors.UNABLE_TO_ACQUIRE_LOCK.getCode(), AccountErrors.UNABLE_TO_ACQUIRE_LOCK.getMsg());
        }
    }

    public void unlock(String lockKey) {
        log.info("Releasing the lock for lockKey = {}", lockKey);
        lockManager.unlock(LOCK_KEY_PREFIX + lockKey);
    }

}
