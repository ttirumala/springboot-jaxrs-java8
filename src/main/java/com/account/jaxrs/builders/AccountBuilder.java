package com.account.jaxrs.builders;

import com.account.jaxrs.beans.Account;

import java.util.function.Consumer;
import java.util.stream.Stream;


public class AccountBuilder {

    public static Account buildAccount(AccountSetter... accountSetters) {
        final Account account = new Account();

        Stream.of(accountSetters).forEach(s -> s.accept(account));

        return account;
    }

    @FunctionalInterface
    public interface AccountSetter extends Consumer<Account> {
    }

}
