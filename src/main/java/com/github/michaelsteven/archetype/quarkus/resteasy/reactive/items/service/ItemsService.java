package com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.model.ConfirmationDto;
import com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.model.ItemDto;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

/**
 * The Interface ItemsService.
 */
public interface ItemsService {
	
	/**
	 * Gets the items.
	 *
	 * @param pageable the pageable
	 * @return the items
	 */
	public abstract Multi<ItemDto> getItems();
	
	/**
	 * Gets the item by id.
	 *
	 * @param id the id
	 * @return the item by id
	 */
	public abstract Uni<ItemDto> getItemById(long id);
	
	/**
	 * Save item.
	 *
	 * @param itemDto the item dto
	 * @return the confirmation dto
	 */
	public abstract Uni<ConfirmationDto> saveItem(@NotNull @Valid ItemDto itemDto);
	
	/**
	 * Edits the item.
	 *
	 * @param itemDto the item dto
	 * @return the confirmation dto
	 */
	public abstract Uni<ConfirmationDto> editItem(@NotNull @Valid ItemDto itemDto);
	
	/**
	 * Delete item by id.
	 *
	 * @param id the id
	 */
	public abstract void deleteItemById(long id);

}
