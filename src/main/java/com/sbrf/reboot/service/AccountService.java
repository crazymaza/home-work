package com.sbrf.reboot.service;

import com.sbrf.reboot.dao.Account;
import com.sbrf.reboot.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Set;

@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository repository;

    public boolean isAccountExist(long clientId, Account account) throws IOException {
        Set<Account> accounts = repository.getAllAccountsByClientId(clientId);
        return accounts.contains(account);
    }
}
