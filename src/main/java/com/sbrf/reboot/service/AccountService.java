package com.sbrf.reboot.service;

import com.sbrf.reboot.dao.Account;
import com.sbrf.reboot.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository repository;

    public boolean isAccountExist(long clientId, Account account) throws IOException {
        Set<Account> accounts = repository.getAllAccountsByClientId(clientId);
        return accounts.contains(account);
    }

    public Account getMaxAccountBalance(Long id) throws IOException {
        return repository.getAllAccountsByClientId(id)
                .stream()
                .max(Comparator.comparing(Account::getBalance))
                .orElseGet(Account::new);
    }

    public Set<Account> getAllAccountsByDateMoreThen(Long id, LocalDate date) throws IOException {
        return repository.getAllAccountsByClientId(id)
                .stream()
                .filter(account ->
                        account.getCreateDate().isEqual(date)
                                || account.getCreateDate().isAfter(date))
                .collect(Collectors.toSet());
    }
}
