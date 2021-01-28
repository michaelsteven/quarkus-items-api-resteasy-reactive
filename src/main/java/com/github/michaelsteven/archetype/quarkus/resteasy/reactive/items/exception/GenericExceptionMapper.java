package com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.exception;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.model.ApiError;


/**
 * The Class GenericExceptionMapper.
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {
   

	/**
	 * To response.
	 *
	 * @param arg0 the arg 0
	 * @return the response
	 */
	@Override
	public Response toResponse(Exception exception) {
	  	exception.printStackTrace();
		String errorMessage = "An exception occured while processing the request.";
        List<String> errors = new ArrayList<>();
        errors.add(errorMessage);
        ApiError apiError = new ApiError(Status.BAD_GATEWAY, errorMessage, errors);
        return Response.status(apiError.getStatus()).entity(apiError).build();
	}
}