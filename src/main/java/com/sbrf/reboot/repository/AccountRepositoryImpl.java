package com.sbrf.reboot.repository;

import com.sbrf.reboot.dao.Account;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final String path;

    @Override
    public Set<Account> getAllAccountsByClientId(long clientId) throws IOException {
        StringBuilder line = readingFromFile();
        List<String> sortedStringList = sortedBuilderLine(line);
        List<Account> accountList = createAccountList(sortedStringList);
        return getAccountSet(clientId, accountList);
    }

    private StringBuilder readingFromFile() throws IOException {
        StringBuilder finalLine = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String readingLine;

            while ((readingLine = bufferedReader.readLine()) != null) {
                finalLine.append(readingLine);
            }
        }
        return finalLine;
    }

    private List<String> sortedBuilderLine(StringBuilder line) {
        String[] splitStringArray = line.toString()
                .split("\\[|\\]|\\{|\\}");
        List<String> afterSortedList = new ArrayList<>();

        for (String str : splitStringArray) {
            if (!str.trim().isEmpty() && !str.trim().equals(",")) {
                afterSortedList.add(str.trim());
            }
        }
        return afterSortedList;
    }

    private List<Account> createAccountList(List<String> sortedList) {
        List<Account> accountList = new ArrayList<>();
        for (String str : sortedList) {
            accountList.add(new Account(str.split(",\\s+")[1].replace("\"number\":", "").replace("\"", "").trim(),
                    Long.parseLong(str.split(",\\s+")[0].replace("\"clientId\":", "").replace("\"", "").trim())));
        }
        return accountList;
    }

    private Set<Account> getAccountSet(long clientId, List<Account> accountList) {
        if (accountList.isEmpty()) return null;
        Set<Account> accountSet = new HashSet<>();
        for (Account account : accountList) {
            if (account.getId() == clientId) {
                accountSet.add(account);
            }
        }
        return accountSet;
    }
}
