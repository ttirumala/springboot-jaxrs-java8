package com.account.jaxrs.builders;

import com.account.jaxrs.beans.Account;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountTestDataBuilder {

    public static List<Account> accountList = new ArrayList<>();

    public static void loadTestAccountData(){

        Account metaAccount = new AccountBuilder().with(
                accountBuilder-> {
                accountBuilder.customerName = "Tirumala T";
                accountBuilder.amount= new BigDecimal("1500.00");
                accountBuilder.currency="USD";
                accountBuilder.id=1;
                accountBuilder.uri="/v1/accounts/account/1";
        }).createAccount();
        accountList.add(metaAccount);

        metaAccount = new AccountBuilder().with(
                accountBuilder-> {
                    accountBuilder.customerName = "Kriss W";
                    accountBuilder.amount= new BigDecimal("2500.00");
                    accountBuilder.currency="INR";
                    accountBuilder.id=2;
                    accountBuilder.uri="/v1/accounts/account/2";
                }).createAccount();
        accountList.add(metaAccount);

        metaAccount = new AccountBuilder().with(
                accountBuilder-> {
                    accountBuilder.customerName = "Jason P";
                    accountBuilder.amount= new BigDecimal("3500.00");
                    accountBuilder.currency="AED";
                    accountBuilder.id=3;
                    accountBuilder.uri="/v1/accounts/account/3";
                }).createAccount();
        accountList.add(metaAccount);

    }
}
