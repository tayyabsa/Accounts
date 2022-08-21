package com.bsfdc.accounts.repository;

import com.bsfdc.accounts.entity.Accounts;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class AccountsRepositoryTest {

    @Autowired
    private AccountsRepository accountsRepository;

    @Test
    public void findByAccountIdTest() {
        Optional<Accounts> byAccountId = accountsRepository.findByAccountId("f12c1e4d-9838-4ca3-b5d7-b38b4bc4f5ee");
        Assert.assertEquals(Long.valueOf(1), byAccountId.get().getUserId());
        Assert.assertEquals(new BigDecimal("100.00"), byAccountId.get().getBalance());
    }
}
