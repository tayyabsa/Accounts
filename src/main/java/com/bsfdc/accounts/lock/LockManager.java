package com.bsfdc.accounts.lock;

public interface LockManager {

    boolean lock(String lockName, Long ttLockInMillis);
    boolean unlock(String lockName);
}
