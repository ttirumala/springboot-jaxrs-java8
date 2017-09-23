package com.account.jaxrs.builders;

import com.account.jaxrs.beans.Account;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.function.Consumer;


public class AccountBuilder {

    public String customerName;
    public String currency;
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    public BigDecimal amount;
    public int id;
    public String uri;

    public AccountBuilder with(
            Consumer<AccountBuilder> builderFunction) {
        builderFunction.accept(this);
        return this;
    }


    public Account createAccount() {
        return new Account(customerName, currency, amount, id, uri);
    }

}
