package com.money.transfer.api;

import com.money.transfer.entity.TransferForm;
import com.money.transfer.service.Userservice;
import com.money.transfer.entity.User;
import com.money.transfer.exception.AccountNotFoundException;
import com.money.transfer.exception.InsufficientFundException;
import com.money.transfer.service.AccountService;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/transfer")
public class AccountTranferApi {
    private Userservice userservice = new Userservice();
    private AccountService accountService = new AccountService();


    @POST
    @Path("/enroll")
    public Response createUser(User user) {
        userservice.createUser(user);
        return Response.status(200).build();
    }
    @POST
    @Path("/money")
    public Response transfer(TransferForm transferForm) throws InsufficientFundException, AccountNotFoundException {
        accountService.transfer(transferForm);
        return Response.status(200).build();

    }

}
