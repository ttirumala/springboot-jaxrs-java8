package com.account.jaxrs.data;

import com.account.jaxrs.beans.Account;
import com.account.jaxrs.beans.Accounts;
import com.account.jaxrs.builders.AccountBuilder;
import com.account.jaxrs.builders.AccountTestDataBuilder;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.lang.reflect.Array;
import java.math.BigDecimal;
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
            if (!jsonFile.exists()){
                jsonFile.createNewFile();
               // AccountTestDataBuilder.loadTestAccountData();
                AccountTestDataBuilder.loadProtobufTestAccountData();
                ObjectMapper mapperMetaData = new ObjectMapper();
                mapperMetaData.writeValue(jsonFile, AccountTestDataBuilder.accountList);
            }
            Accounts accounts = new Accounts();
            TypeReference<ArrayList<Account>> mapAcctType = new TypeReference<ArrayList<Account>>() {};
            ArrayList<Account> jsonToAccountList = mapper.readValue(jsonFile, mapAcctType);
            accounts.setAccounts(jsonToAccountList);
            return accounts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
