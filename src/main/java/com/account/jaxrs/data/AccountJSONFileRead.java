package com.account.jaxrs.data;

import com.account.jaxrs.beans.Account;
import com.account.jaxrs.beans.Accounts;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountJSONFileRead {

    public Accounts readTestData() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT,true);
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File jsonFile = new File(classLoader.getResource(".").getFile() + "/metadata.txt");
            if (!jsonFile.exists())
                jsonFile.createNewFile();
            Accounts accounts = new Accounts();
            TypeReference<ArrayList<Account>> mapAcctType = new TypeReference<ArrayList<Account>>() {};
            ArrayList<Account> jsonToAccountList = mapper.readValue(jsonFile, mapAcctType);
           // Account[] accountArray = mapper.readValue(jsonFile, Account[].class);
            //accounts.setAccounts(new ArrayList<>(Arrays.asList(accountArray)));
            accounts.setAccounts(jsonToAccountList);
            return accounts;
        } catch (Exception e) {
            System.out.println("*************************");
            e.printStackTrace();
        }
        System.out.println("+++++++++++++++++++++++++++");
        return null;
    }
}
