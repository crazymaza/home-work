package com.sbrf.reboot.atm;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Cassette<T extends Banknote> {
    private final List<T> banknotesList;

    public int getCountBanknotes() {
        return banknotesList.size();
    }
}
