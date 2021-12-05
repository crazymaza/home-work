package com.sbrf.reboot.utils;

import com.sbrf.reboot.dao.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class AccountUtilsTest {

    @Test
    void sortedByid() {
        List<Account> accounts = new ArrayList<Account>() {{
            add(Account.builder().id(3L).createDate(LocalDate.now().minusDays(2)).balance(new BigDecimal("123")).clientId("").build());
            add(Account.builder().id(1L).createDate(LocalDate.now().minusDays(3)).balance(new BigDecimal("321")).clientId("").build());
            add(Account.builder().id(3L).createDate(LocalDate.now().minusDays(1)).balance(BigDecimal.TEN).clientId("").build());
            add(Account.builder().id(2L).createDate(LocalDate.now()).balance(BigDecimal.ONE).clientId("").build());
        }};

        AccountUtils.sortedById(accounts);

        Assertions.assertEquals(1L, accounts.get(0).getId());
        Assertions.assertEquals(2L, accounts.get(1).getId());
        Assertions.assertEquals(3L, accounts.get(2).getId());
        Assertions.assertEquals(3L, accounts.get(3).getId());

    }

    @Test
    void sortedByIdDate() {
        List<Account> accounts = new ArrayList<Account>() {{
            add(Account.builder().id(1L).createDate(LocalDate.now().minusDays(4)).balance(BigDecimal.TEN).clientId("").build());
            add(Account.builder().id(3L).createDate(LocalDate.now().minusDays(3)).balance(BigDecimal.TEN).clientId("").build());
            add(Account.builder().id(3L).createDate(LocalDate.now().minusDays(1)).balance(BigDecimal.TEN).clientId("").build());
            add(Account.builder().id(2L).createDate(LocalDate.now()).balance(BigDecimal.TEN).clientId("").build());
        }};

        AccountUtils.sortedByIdDate(accounts);

        Assertions.assertEquals(1L, accounts.get(0).getId());
        Assertions.assertEquals(3L, accounts.get(1).getId());
        Assertions.assertEquals(LocalDate.now().minusDays(3), accounts.get(1).getCreateDate());
        Assertions.assertEquals(LocalDate.now().minusDays(1), accounts.get(2).getCreateDate());
    }

    @Test
    void sortedByIdDateAndBalanceAsc() {
        List<Account> accounts = new ArrayList<Account>() {{
            add(Account.builder().id(1L).createDate(LocalDate.now()).balance(BigDecimal.TEN).clientId("").build());
            add(Account.builder().id(1L).createDate(LocalDate.now().minusDays(1)).balance(BigDecimal.TEN).clientId("").build());
            add(Account.builder().id(3L).createDate(LocalDate.now()).balance(new BigDecimal("9")).clientId("").build());
            add(Account.builder().id(3L).createDate(LocalDate.now().minusDays(2)).balance(new BigDecimal("11")).clientId("").build());
            add(Account.builder().id(2L).createDate(LocalDate.now()).balance(new BigDecimal("1")).clientId("").build());
            add(Account.builder().id(2L).createDate(LocalDate.now().minusDays(1)).balance(new BigDecimal("2")).clientId("").build());
        }};

        AccountUtils.sortedByIdDateBalance(accounts);

        Assertions.assertEquals(1L, accounts.get(0).getBalance().longValueExact());
        Assertions.assertEquals(2L, accounts.get(1).getBalance().longValueExact());
        Assertions.assertEquals(LocalDate.now().minusDays(1), accounts.get(3).getCreateDate());
        Assertions.assertEquals(LocalDate.now(), accounts.get(4).getCreateDate());
    }
}