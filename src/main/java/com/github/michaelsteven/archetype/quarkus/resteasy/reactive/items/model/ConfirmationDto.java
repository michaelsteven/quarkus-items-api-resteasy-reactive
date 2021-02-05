package com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.model;

import java.time.ZonedDateTime;


/**
 * Instantiates a new confirmation dto.
 */
public class ConfirmationDto {
	
	/** The id. */
	private Long id;
	
	/** The status. */
	private ItemStatus status;
	
	/** The date submitted. */
	private ZonedDateTime dateSubmitted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemStatus getStatus() {
		return status;
	}

	public void setStatus(ItemStatus status) {
		this.status = status;
	}

	public ZonedDateTime getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(ZonedDateTime dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
}
