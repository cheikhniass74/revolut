package com.money.transfer.exception;

import com.money.transfer.exception.InsufficientFundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InsufficientFundMapper implements ExceptionMapper<InsufficientFundException> {
    @Override
    public Response toResponse(InsufficientFundException e) {
        return Response.status(409).build();
    }
}
