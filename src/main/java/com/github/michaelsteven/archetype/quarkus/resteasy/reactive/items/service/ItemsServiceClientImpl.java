package com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.model.ConfirmationDto;
import com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.model.ItemDto;
import com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.model.ItemStatus;
import com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.repository.ItemsRepository;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

/**
 * The Class ItemsServiceClientImpl.
 */
@ApplicationScoped
public class ItemsServiceClientImpl implements ItemsService {
	
	public static final Logger logger = LoggerFactory.getLogger(ItemsServiceClientImpl.class);

    @Inject 
    ItemsRepository itemsRepository;
	
	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	@Override
	public Multi<ItemDto> getItems() {
		return itemsRepository.findAll();
	}

	/**
	 * Gets the item by id.
	 *
	 * @param id the id
	 * @return the item by id
	 */
	@Override
	public Uni<ItemDto> getItemById(long id) {
        return itemsRepository.findById(id);
	}

	/**
	 * Save item.
	 *
	 * @param itemDto the item dto
	 * @return the uni
	 */
	@Override
	public Uni<ConfirmationDto> saveItem(@NotNull @Valid ItemDto itemDto) {
		return itemsRepository.save(itemDto)
				  .map(entity -> createConfirmationDto(ItemStatus.SUBMITTED, itemDto));
	}

	/**
	 * Edits the item.
	 *
	 * @param itemDto the item dto
	 * @return the uni
	 */
	@Override
	public Uni<ConfirmationDto> editItem(@NotNull @Valid ItemDto itemDto) {
	    return itemsRepository.update(itemDto).map(status -> createConfirmationDto(ItemStatus.SUBMITTED, itemDto));
	}

	/**
	 * Delete item by id.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteItemById(long id) {
		itemsRepository.delete(id);
	}
	
	/**
	 * Creates the confirmation dto.
	 *
	 * @param itemStatus the item status
	 * @param dto the entity
	 * @return the confirmation dto
	 */
	private ConfirmationDto createConfirmationDto(ItemStatus itemStatus, ItemDto dto) {
		ConfirmationDto confirmationDto = new ConfirmationDto();
		confirmationDto.setStatus(itemStatus);
		if(null != dto) {
			confirmationDto.setId(dto.getId());
			if(null != dto.getDateSubmitted()) {
				ZonedDateTime dateSubmitted = ZonedDateTime.ofInstant(dto.getDateSubmitted(), ZoneOffset.UTC);
				confirmationDto.setDateSubmitted(dateSubmitted);
			}
		}
		else {
			logger.warn("itemEntity is null");
		}
		return confirmationDto;
	}

}
