package com.sbrf.reboot.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Account {
    @NonNull
    private Long clientId;

    private String number;
    private LocalDate createDate;
    private BigDecimal balance;

    public Account(Long clientId, String number) {
        this.clientId = clientId;
        this.number = number;
    }

    public Account(String number) {
        this.number = number;
    }
}
