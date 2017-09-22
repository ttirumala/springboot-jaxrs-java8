package com.account.jaxrs.data;

import com.account.jaxrs.beans.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AccountJSONFileWrite {

    public void writeTestData(ArrayList<Account> accountArrayList) {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            File file = new File(classLoader.getResource(".").getFile() + "/metadata.txt");
            if (!file.exists())
                file.createNewFile();

            FileWriter fileWrt = new FileWriter(file);
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
            fileWrt.write(objectWriter.writeValueAsString(accountArrayList));
            // objectMapper.writeValue(file,account);
            fileWrt.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
