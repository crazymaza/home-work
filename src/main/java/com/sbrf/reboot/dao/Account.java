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
    private String clientId;

    private Long id;
    private LocalDate createDate;
    private BigDecimal balance;

    public Account(@NonNull String clientId, Long id) {
        this.clientId = clientId;
        this.id = id;
    }
}
