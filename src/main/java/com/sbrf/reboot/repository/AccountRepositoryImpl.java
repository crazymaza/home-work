package com.sbrf.reboot.repository;

import com.sbrf.reboot.dao.Account;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

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
    @SneakyThrows
    public Set<Account> getAllAccountsByClientId(long clientId) {
        StringBuilder line = readingFromFile();
        List<String> c = sortedBuilderLine(line);
        List<Account> d = createAccountList(c);
        return getAccountSet(clientId, d);
    }

    private StringBuilder readingFromFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        StringBuilder finalLine = new StringBuilder();
        String readingLine;

        while ((readingLine = bufferedReader.readLine()) != null) {
            finalLine.append(readingLine);
        }
        return finalLine;
    }

    private List<String> sortedBuilderLine(StringBuilder line) {
        String[] splitStringArray = line.toString()
                .split("\\[|\\]|\\{|\\}");
        List<String> afterSortedList = new ArrayList<>();

        for (String s : splitStringArray) {
            if (!s.trim().isEmpty() && !s.trim().equals(",")) {
                afterSortedList.add(s.trim());
            }
        }
        return afterSortedList;
    }

    private List<Account> createAccountList(List<String> sortedList) {
        List<Account> accountList = new ArrayList<>();
        for (String s : sortedList) {
            accountList.add(new Account(s.split(",\\s+")[1].replace("\"number\":", "").replace("\"", "").trim(),
                    Long.parseLong(s.split(",\\s+")[0].replace("\"clientId\":", "").replace("\"", "").trim())));
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
