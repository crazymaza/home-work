package com.sbrf.reboot.repository;

import com.sbrf.reboot.dao.Account;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
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
    public Set<Account> getAllAccountsByClientId(long clientId) {
        StringBuilder line = readingFromFile();
        List<String> sortedStringList = sortedBuilderLine(line);
        List<Account> accountList = createAccountList(sortedStringList);
        return getAccountSet(clientId, accountList);
    }

    private StringBuilder readingFromFile() {
        StringBuilder finalLine = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String readingLine;

            while ((readingLine = bufferedReader.readLine()) != null) {
                finalLine.append(readingLine);
            }
        } catch (IOException e) {
            System.out.printf("Can't reading file: %s.\n%s", path, e);
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
            if (account.getClientId() == clientId) {
                accountSet.add(account);
            }
        }
        return accountSet;
    }
}
