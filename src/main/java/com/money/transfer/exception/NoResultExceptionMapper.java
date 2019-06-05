package com.money.transfer.exception;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NoResultExceptionMapper  implements ExceptionMapper<NoResultException> {
    @Override
    public Response toResponse(NoResultException e) {
        return Response.status(404).build();
    }
}
