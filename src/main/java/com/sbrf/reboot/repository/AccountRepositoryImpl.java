package com.sbrf.reboot.repository;

import com.sbrf.reboot.dao.Account;

import java.util.HashSet;
import java.util.Set;

public class AccountRepositoryImpl implements AccountRepository {

    @Override
    public Set<Account> getAllAccountsByClientId(long clientId) {
        Account account = new Account("ACC1234NUM");
        Set<Account> accounts = new HashSet();
        accounts.add(account);
        return accounts;
    }
}
