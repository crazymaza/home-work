package com.sbrf.reboot.utils;

import com.sbrf.reboot.dao.Account;

import java.util.Comparator;
import java.util.List;

public class AccountUtils {
    public static void sortedById(List<Account> accounts) {
        accounts.sort(Comparator.comparing(Account::getNumber));
    }

    public static void sortedByIdDate(List<Account> accounts) {
        sortedById(accounts);
        accounts.sort((o1, o2) -> o1.getCreateDate().isBefore(o2.getCreateDate()) ? 1 : 0);
    }

    public static void sortedByIdDateBalance(List<Account> accounts) {
        sortedByIdDate(accounts);
        accounts.sort((o1, o2) -> o1.getBalance().compareTo(o2.getBalance()) == 0 ? 1 : 0);
    }
}
