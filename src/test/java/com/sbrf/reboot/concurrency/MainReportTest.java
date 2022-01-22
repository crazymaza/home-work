package com.sbrf.reboot.concurrency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class MainReportTest {

    MainReport mainReport = new MainReport();
    Supplier<Stream<Customer>> supplier;

    @BeforeEach
    public void createAccountsStream() {
        Set<Account> accounts = new HashSet<Account>() {{
            add(new Account(123L, "EUR", LocalDate.of(2021, 7, 1)));
            add(new Account(124L, "EUR", LocalDate.of(2021, 7, 10)));
            add(new Account(125L, "EUR", LocalDate.of(2021, 8, 10)));
        }};

        Customer customer1 = new Customer(19, "Max", accounts);
        Customer customer2 = new Customer(18, "Man", accounts);
        Customer customer3 = new Customer(20, "Mad", accounts);
        Customer customer4 = new Customer(30, "Mag", accounts);
        supplier = () -> Stream.of(customer1, customer2, customer3, customer4);
    }

    @Test
    public void getTotalsWithCompletableFutureTest() {
        Assertions.assertEquals(248,
                mainReport.getTotalsWithCompletableFuture(supplier.get()));
    }

    @Test
    public void getTotalsWithReactTest() {
        Assertions.assertEquals(248,
                mainReport.getTotalsWithReact(supplier.get()));
    }
}
