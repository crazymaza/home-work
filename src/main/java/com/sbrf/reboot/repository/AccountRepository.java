package com.sbrf.reboot.repository;

import com.sbrf.reboot.dao.Account;

import java.util.Set;

public interface AccountRepository {
    Set<Account> getAllAccountsByClientId(long clientId);
}
