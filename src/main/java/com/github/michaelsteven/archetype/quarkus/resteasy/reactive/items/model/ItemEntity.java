package com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Instantiates a new item entity.
 */
@Entity
@Table(name = "items")
public class ItemEntity {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	@Column(name = "created_ts")
	private Instant createdTimestamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instant getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Instant createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
}
