package com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.model;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * To string.
 *
 * @return the java.lang. string
 */
@RegisterForReflection
public class ApiError {

	/** The status. */
	private Status status;
	
	/** The message. */
	private String message;
	
	/** The errors. */
	private List<String> errors;
	
    /**
     * Instantiates a new api error.
     *
     * @param status the status
     * @param message the message
     * @param errors the errors
     */
    public ApiError(Status status, String message, List<String> errors) {
		this.status =  status;
		this.message = message;
		this.errors = errors;
	}
    
    /**
     * Instantiates a new api error.
     *
     * @param status the status
     * @param message the message
     * @param error the error
     */
    public ApiError(Status status, String message, String error) {
 		this.status =  status;
 		this.message = message;
 		this.errors = Arrays.asList(error);
 	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
