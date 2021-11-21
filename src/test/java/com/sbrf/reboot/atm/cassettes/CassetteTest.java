package com.sbrf.reboot.atm.cassettes;

import com.sbrf.reboot.atm.Banknote;
import com.sbrf.reboot.atm.Cassette;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CassetteTest {

    static class OneHundred extends Banknote {
    }

    static class OneThousand extends Banknote {
    }

    @Test
    void getCountOneHundredBanknotes() {
        Cassette<OneHundred> cassette = new Cassette<>(new ArrayList<OneHundred>() {{
            add(new OneHundred());
        }});

        Assertions.assertEquals(1, cassette.getCountBanknotes());
    }

    @Test
    void getCountOneThousandBanknotes() {
        Cassette<OneThousand> cassette = new Cassette<>(new ArrayList<OneThousand>() {{
            add(new OneThousand());
        }});

        Assertions.assertEquals(1, cassette.getCountBanknotes());
    }

    @Test
    void getCountBanknotes() {
        Cassette<Banknote> cassette = new Cassette<>(new ArrayList<Banknote>() {{
            add(new Banknote());
            add(new OneThousand());
            add(new OneHundred());
        }});

        Assertions.assertEquals(3, cassette.getCountBanknotes());
    }
}
