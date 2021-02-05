package com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.model;

import java.time.Instant;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.eclipse.microprofile.openapi.annotations.media.Schema;


/**
 * Instantiates a new item dto.
 *
 * @param id the id
 * @param name the name
 * @param description the description
 * @param dateSubmitted the date submitted
 */
public class ItemDto {

	/** The id. */
	@Schema(name = "id", description="The ID of the item", example ="1234567890")
	private Long id;
	
	/** The name. */
	@Schema(name = "name", description="The name of the item", example = "wigit5spr")
	@NotNull(message = "{itemdto.null}")
	private String name;
	
	/** The description. */
	@Schema(name = "description", description="The description of the item", example = "5 sprocket wigit")
	@Size(min  = 1, max = 200, message = "{itemdto.textlimit}")
	private String description;
	
	/** The date submitted. */
	@Schema(hidden = true)
	private Instant dateSubmitted;
	
	public ItemDto() {
		
	}
	
    public ItemDto(String name) {
        this.name = name;
    }

    public ItemDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ItemDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    public ItemDto(Long id, String name, String description, Instant offsetDateTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateSubmitted = offsetDateTime;
    }
    
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

	public Instant getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(Instant dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}	
}
