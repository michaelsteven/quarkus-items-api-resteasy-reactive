package com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.resource;

import java.net.URI;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.model.ApiError;
import com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.model.ItemDto;
import com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.repository.ItemsRepository;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;


/**
 * The Class ItemsResource.
 */
@Tag(name = "Items", description = "The items api can be used to perform actions on Items")
@Path("/api/v1/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemsResource {


    /** The items repository. */
    @Inject 
    ItemsRepository itemsRepository;

    
    /**
     * Gets the items.
     *
     * @return the items
     */
    @Operation(summary = "Retrieve items", description = "Use this API to retrieve a paginated collection of items.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = Multi.class))),
            @APIResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @APIResponse(responseCode = "503", description = "Service unavailable", content = @Content(schema = @Schema(implementation = ApiError.class))) 
    	})
    @GET
    public Multi<ItemDto> getItems() {
        return itemsRepository.findAll();
    }

    
    /**
     * Gets the item by id.
     *
     * @param id the id
     * @return the item by id
     */
    @Operation(summary = "Gets an item", description = "Use this API to retrieve an existing item.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ItemDto.class))),
            @APIResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @APIResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = Void.class))),
            @APIResponse(responseCode = "503", description = "Service unavailable", content = @Content(schema = @Schema(implementation = ApiError.class)))
         })
    @GET
    @Path("/{id}")
    public Uni<Response> getItemById(@PathParam("id") Long id) {
        return itemsRepository.findById(id)
                .onItem().transform(fruit -> fruit != null ? Response.ok(fruit) : Response.status(Status.NOT_FOUND))
                .onItem().transform(ResponseBuilder::build);
    }

    
    /**
     * Save item.
     *
     * @param itemDto the item dto
     * @return the response entity
     */
    @Operation(summary = "Submit a new item", description = "Use this API to generate a new item. "
            + "In some cases this POST method may return a 202 ACCEPTED response code, "
            + "in which case the data returned will contain a status code, and an identifier. "
            + "The identifier can then be used in subsequent GET calls to obtain the item at a later time.")
    @APIResponses(value = {
            @APIResponse(responseCode = "202", description = "accepted", content = @Content(schema = @Schema(implementation = ItemDto.class))),
            @APIResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @APIResponse(responseCode = "503", description = "Service unavailable", content = @Content(schema = @Schema(implementation = ApiError.class))) 
    	})
    @POST
    public Uni<Response> saveItem(@Valid ItemDto itemDto) {
        return itemsRepository.save(itemDto)
                .onItem().transform(id -> URI.create("/items/" + id))
                .onItem().transform(uri -> Response.created(uri).build());
    }

    
    /**
     * Edits the item.
     *
     * @param id the id
     * @param itemDto the item dto
     * @return the response entity
     */
    @Operation(summary = "Modifies an item", description = "Use this API to modify an item. "
            + "In some cases this PUT method may return a 202 ACCEPTED response code, "
            + "in which case the data returned will contain a status code, and an identifier. "
            + "The identifier can then be used in subsequent GET calls to obtain the item at a later time.")
    @APIResponses(value = {
            @APIResponse(responseCode = "202", description = "accepted", content = @Content(schema = @Schema(implementation = ItemDto.class))),
            @APIResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @APIResponse(responseCode = "503", description = "Service unavailable", content = @Content(schema = @Schema(implementation = ApiError.class)))
        })
    @PUT
    @Path("/{id}")
    public Uni<Response> editItem(@PathParam("id") Long id, @Valid ItemDto itemDto) {
        return itemsRepository.update(itemDto)
                .onItem().transform(updated -> updated ? Status.OK : Status.NOT_FOUND)
                .onItem().transform(status -> Response.status(status).build());
    }

    
    /**
     * Delete by id.
     *
     * @param id the id
     * @return the uni
     */
    @Operation(summary = "Deletes an item", description = "Use this API to delete an item.")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = Void.class))),
            @APIResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @APIResponse(responseCode = "503", description = "Service unavailable", content = @Content(schema = @Schema(implementation = ApiError.class)))
         })
    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteById(@PathParam("id") Long id) {
        return itemsRepository.delete(id)
                .onItem().transform(deleted -> deleted ? Status.NO_CONTENT : Status.NOT_FOUND)
                .onItem().transform(status -> Response.status(status).build());
    }
}
