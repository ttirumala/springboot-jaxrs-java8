package com.account.jaxrs.controller;

import com.account.jaxrs.beans.Account;
import com.account.jaxrs.beans.Accounts;
import com.account.jaxrs.data.AccountJSONFileRead;
import com.account.jaxrs.data.AccountJSONFileWrite;
import com.account.jaxrs.interfaces.ObjectPatch;
import com.account.jaxrs.interfaces.PATCH;
import com.account.jaxrs.patch.MediaTypes;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "accounts")
@Path("/accounts")
@RestController
public class AccountController {

    @GET
    @Path("/account/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAccountById(@PathParam("id") String accountId) throws URISyntaxException {
        AccountJSONFileRead fileRead = new AccountJSONFileRead();
        Accounts accounts = fileRead.readTestData();
        Stream<Account> accountList = accounts.getAccounts().parallelStream();
        Account account = accountList.filter(s -> accountId.equals(String.valueOf(s.getId()))).findAny().orElse(null);
        Response response = Response.status(200).entity(account).contentLocation(new URI("/account/" + accountId)).build();
        return response;
    }

    @GET
    @Path("/")
    //@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaTypes.APPLICATION_PROTOBUF})
  //  public Response getAllAccounts() {
    public com.jaxrstask.protobuf.Accounts getAllAccounts() {
        System.out.println("DEBUG-getAllAccounts()-Entered");
        AccountJSONFileRead fileRead = new AccountJSONFileRead();
        Accounts accounts = fileRead.readTestData();
        List<com.jaxrstask.protobuf.Account> protBufAcctList=new ArrayList<>();
        for(Account a:accounts.getAccounts()){
            com.jaxrstask.protobuf.Account.Builder acctBuilder=com.jaxrstask.protobuf.Account.newBuilder()
            .setId(a.getId())
            .setBalance(a.getBalance())
            .setCurrency(a.getCurrency())
            .setCustomerName(a.getCustomerName())
            .setUri(a.getUri());
            protBufAcctList.add(acctBuilder.build());
        }
        return com.jaxrstask.protobuf.Accounts.newBuilder().addAllAccount(protBufAcctList).build();
        //Stream<Account> accountList= accounts.getAccounts().parallelStream();
       // Response response = Response.status(200).entity(accounts).build();
       // return response;
    }

    @POST
    @Path("/account")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createAccount(Account account) throws URISyntaxException {
        System.out.println("DEBUG-createAccount()-Entered");
        if (account.getCustomerName() == null) {
            return Response.status(400).entity("Please provide all mandatory inputs").build();
        }
        AccountJSONFileRead fileRead = new AccountJSONFileRead();
        Accounts accounts = fileRead.readTestData();
        Stream<Account> accountListStream = accounts.getAccounts().parallelStream();
        Account accountExist = accountListStream.filter(s -> account.getId() == s.getId()).findAny().orElse(null);
        if (accountExist != null) {
            return Response.status(400).entity("Account Already Exist!!").build();
        }

        account.setUri("/v1/accounts/account/" + account.getId());
        ArrayList<Account> accountArrayList = accounts.getAccounts();
        accountArrayList.add(account);
        AccountJSONFileWrite fileWriter = new AccountJSONFileWrite();
        fileWriter.writeTestData(accountArrayList);
        return Response.status(201).contentLocation(new URI(account.getUri())).build();
    }

    @PUT
    @Path("/account/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response replaceAccount(@PathParam("id") int id, Account account) throws URISyntaxException {
        AccountJSONFileRead fileRead = new AccountJSONFileRead();
        Accounts accounts = fileRead.readTestData();
        //Stream<Account> accountList= accounts.getAccounts().parallelStream();
        Predicate<Account> personPredicate = p -> p.getId() == id;
        accounts.getAccounts().removeIf(personPredicate);
        if (accounts == null) {
            return Response.status(404).build();
        }

        accounts.getAccounts().add(account);
        AccountJSONFileWrite fileWriter = new AccountJSONFileWrite();
        fileWriter.writeTestData(accounts.getAccounts());

        return Response.status(200).entity(accounts.getAccounts()).build();
    }

    @PATCH
    @Path("/account/{id}")
    @Consumes({MediaTypes.APPLICATION_JSON_PATCH})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response patchAccount(@PathParam("id") int id, ObjectPatch patch) throws URISyntaxException {
        AccountJSONFileRead fileRead = new AccountJSONFileRead();
        Accounts accounts = fileRead.readTestData();
        if (accounts == null) {
            return Response.status(404).build();
        }
        ArrayList<Account> accountDB = accounts.getAccounts();
        Predicate<Account> acctPredicate = p -> p.getId() == id;
        List<Account> acctList = accountDB.stream().filter(acctPredicate).collect(Collectors.<Account>toList());
        Account account = patch.apply(acctList.get(0));
        accountDB.forEach(accountL -> {
            if (accountL.getId() == id) {
                accountL.setCurrency(account.getCurrency());
                accountL.setAmount(account.getAmount());
                accountL.setCustomerName(account.getCustomerName());
            }
        });
        AccountJSONFileWrite fileWriter = new AccountJSONFileWrite();
        fileWriter.writeTestData(accountDB);

        return Response.status(200).entity(accountDB).build();
    }

    @GET
    @Path("/greetings")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getGreeting() {
        return Response.ok("Hello, Jason!").build();
    }
}
