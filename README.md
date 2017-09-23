# springboot-jaxrs-java8
springboot application with REST prototype , using PATCH.
Here is the sample PATH request.

[{ "op": "replace", 
"path": "/currency", 
"value": "USD"}]

ask 1 and Workflow Explain -

 

Task 1 - build a Maven project using IntelliJ, providing RESTful service using JAX RS 2.0 and spring components. Showcase use of these components -

 

(1)  JAX RS annotation including @Path, @ApplicationPath, @GET/POST/PATCH, @Produces, @Consumes, @PathParam,

 

Example codes I expect to see in your submission -

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
@Path(“/v1/accounts/account”)
public class AccountRestService {
    @GET
    @Path(“/{param}”)
    @Produces(“application/json”)
    public Response getAccount(@PathParam(“param”) String accountId) {
        SomeResource result = lookup(accountId);
        Response response = Response.status(200).entity(result).build();
        return response;
    }

    @POST
    @Consumes(“application/json”)
    public void createAccount(Account account) { … }
}

(2) Database - for simplicity, I don’t want to use any database. Use only 2 JSON files - 1 provided information on metadata, 1 provided real data. The Restful POST API, will add entry to the JSON data file. Suggest to use com.fasterxml.jackson.databind.ObjectMapper library. Please explain and follow this standard - RFC 6902 JSON Patch

https://tools.ietf.org/html/rfc6902

Make sure you have POJO and Builder. I need only 1 simple prototype -
Account - contains customer name, currency and amount of money in the account;

The RESTful API -
/v1/accounts/account     // to create an account, JSON input, POST
/v1/accounts/account/account_id      // to get details of an account, GET
/v1/accounts/account/account_id      // to update an account, PATCH
/v1/accounts/account         // list all the account, GET

(3) Java 8 feature, mostly Lambda expression, Stream, Optional. Please find appropriate to use Builder pattern and generic. These are samples you need to show -

customObject1.map(customObject2::getCustomObject2).ifPresent(x -> builder.setTimeOpened(x));
if (fd.isNotEmpty()) {
    fd.getUnit.stream().forEach (x -> {System.out.println(x.getName())});
}
