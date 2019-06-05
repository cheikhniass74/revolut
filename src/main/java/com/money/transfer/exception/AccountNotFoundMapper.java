package com.money.transfer.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AccountNotFoundMapper implements ExceptionMapper<AccountNotFoundException> {
    @Override
    public Response toResponse(AccountNotFoundException e) {
        return Response.status(404).build();
    }
}
