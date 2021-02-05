package com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.configuration;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.model.ItemDto;
import com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.model.ItemEntity;

/**
 * The Interface ItemDtoMapper.
 */
@Mapper(componentModel = "cdi")
public interface ItemDtoMapper {

	/** The instance. */
	ItemDtoMapper INSTANCE = Mappers.getMapper(ItemDtoMapper.class);

    /**
     * Map to entity.
     *
     * @param dto the dto
     * @return the item entity
     */
    ItemEntity mapToEntity(ItemDto dto);

    /**
     * Map to dto.
     *
     * @param entity the entity
     * @return the item dto
     */
    @Mapping(source = "createdTimestamp", target = "dateSubmitted")
    ItemDto mapToDto(ItemEntity entity);

    /**
     * Map list to entity.
     *
     * @param dtoList the dto list
     * @return the list
     */
    List<ItemEntity> mapListToEntity(List<ItemDto> dtoList);

    /**
     * Map list to dto.
     *
     * @param entity the entity
     * @return the list
     */
    List<ItemDto> mapListToDto(List<ItemEntity> entity);
}