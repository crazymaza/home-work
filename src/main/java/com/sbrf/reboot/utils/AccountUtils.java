package com.sbrf.reboot.utils;

import com.sbrf.reboot.dao.Account;

import java.util.Comparator;
import java.util.List;

public class AccountUtils {
    public static void sortedById(List<Account> accounts) {
        accounts.sort(Comparator.comparing(Account::getId));
    }

    public static void sortedByIdDate(List<Account> accounts) {
        accounts.sort((o1, o2) -> {
            int compare = o1.getCreateDate().compareTo(o2.getCreateDate());
            return compare != 0
                    ? compare
                    : -o1.getId().compareTo(o2.getId());
        });
    }

    public static void sortedByIdDateBalance(List<Account> accounts) {
        accounts.sort((o1, o2) -> {
            int compareBal = o1.getBalance().compareTo(o2.getBalance());
            if (compareBal == 0) {
                int compareDate = o1.getCreateDate().compareTo(o2.getCreateDate());
                if (compareDate == 0) {
                    return o1.getId().compareTo(o2.getId());
                }
                return compareDate;
            }
            return compareBal;
        });
    }
}
