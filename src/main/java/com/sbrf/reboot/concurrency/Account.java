package com.sbrf.reboot.concurrency;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Account {
    private final Long sum;
    private final String currency;
    private final LocalDate createdDate;
}

