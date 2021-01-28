package com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.model.ApiError;

/**
 * The Class ValidationExceptionMapper.
 */
@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

	/**
	 * To response.
	 *
	 * @param exception the exception
	 * @return the response
	 */
	@Override
	public Response toResponse(ValidationException exception) {
		exception.printStackTrace();
        List<String> errors = new ArrayList<>();
        errors.add(exception.getLocalizedMessage());
        ApiError apiError = new ApiError(Status.BAD_REQUEST, exception.getLocalizedMessage(), errors);
        return Response.status(apiError.getStatus()).entity(apiError).build();
	}
}