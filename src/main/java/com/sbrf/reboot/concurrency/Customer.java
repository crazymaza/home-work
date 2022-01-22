package com.sbrf.reboot.concurrency;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Getter
@ToString
@RequiredArgsConstructor
public class Customer {
    private final Integer age;
    private final String name;
    private final Set<Account> accounts;
}
