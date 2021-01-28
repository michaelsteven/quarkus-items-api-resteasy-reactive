package com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.model.ApiError;


/**
 * The Class ConstraintViolationExceptionMapper.
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{

	/**
	 * To response.
	 *
	 * @param exception the exception
	 * @return the response
	 */
	@Override
	public Response toResponse(ConstraintViolationException exception) {
		exception.printStackTrace();
	    List<String> errors = new ArrayList<>();
	    for (ConstraintViolation<?> violation : exception.getConstraintViolations())
	    {
	    	errors.add( violation.getPropertyPath() + ": " + violation.getMessage());
	    }

	    ApiError apiError = new ApiError(Status.BAD_REQUEST, "One or more constraint violations occurred" , errors);
	    return Response.status(apiError.getStatus()).entity(apiError).build();
	}
}