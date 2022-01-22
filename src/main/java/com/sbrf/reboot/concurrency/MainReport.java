package com.sbrf.reboot.concurrency;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainReport {
    public long getTotalsWithCompletableFuture(Stream<Customer> stream) {
        CompletableFuture<List<Account>> completableFutureList = getCompletableFutureList(stream);
        List<Account> accountList = CompletableFuture.allOf(completableFutureList)
                .thenApply(v -> new ArrayList<>(completableFutureList.join()))
                .join();
        return getAccountsValuesSum(accountList);
    }

    public long getTotalsWithReact(Stream<Customer> stream) {
        Scheduler scheduler = Schedulers.newParallel("parallel-scheduler");

        Flux<Long> flux = Flux
                .just(getFilteredCustomers(stream))
                .mapNotNull(filteredCustomers -> {
                    List<Account> accounts = new ArrayList<>();
                    filteredCustomers.forEach(customer -> accounts.addAll(customer.getAccounts()));
                    return getAccountsValuesSum(accounts);
                })
                .subscribeOn(scheduler);
        flux.subscribe();

        return flux.blockFirst();
    }

    private CompletableFuture<List<Account>> getCompletableFutureList(Stream<Customer> stream) {
        return CompletableFuture.supplyAsync(() -> {
            List<Account> accountList = new ArrayList<>();
            getFilteredCustomers(stream)
                    .forEach(customer -> accountList.addAll(customer.getAccounts()));
            return accountList;
        });
    }

    private Set<Customer> getFilteredCustomers(Stream<Customer> customerStream) {
        return customerStream.filter(customer -> customer.getAge() > 18 && customer.getAge() < 30)
                .collect(Collectors.toSet());
    }

    private Stream<Account> getFilteredAccounts(List<Account> accounts) {
        return accounts.stream()
                .filter(account -> account
                        .getCreatedDate()
                        .isAfter(LocalDate.of(2021, 7, 1))
                        && account.getCreatedDate()
                        .isBefore(LocalDate.of(2021, 8, 1)));
    }

    private long getAccountsValuesSum(List<Account> accounts) {
        return getFilteredAccounts(accounts)
                .map(Account::getSum)
                .mapToLong(Long::longValue)
                .sum();
    }
}
