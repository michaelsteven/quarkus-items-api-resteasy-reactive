package com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.github.michaelsteven.archetype.quarkus.resteasy.reactive.items.model.ItemDto;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;


/**
 * The Class ItemsRepository.
 */
@ApplicationScoped
public class ItemsRepository {
	
	private final MySQLPool client;
	
	/**
	 * Instantiates a new items repository.
	 *
	 * @param client the client
	 */
	@Inject
	public ItemsRepository(MySQLPool client) {
		this.client = client;
	}
	
	/**
	 * Find all.
	 *
	 * @return the multi
	 */
	public Multi<ItemDto> findAll() {
        return client.query("SELECT id, name, description, created_ts FROM items ORDER BY id ASC").execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(ItemsRepository::from);
	}

    /**
     * Find by id.
     *
     * @param id the id
     * @return the uni
     */
    public Uni<ItemDto> findById(Long id) {
        return client.preparedQuery("SELECT id, name, description, created_ts FROM items WHERE id = ?").execute(Tuple.of(id))
                .onItem().transform(RowSet::iterator)
                .onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
    }

    /**
     * Save.
     *
     * @param itemDto the item dto
     * @return the uni
     */
    public Uni<Long> save(@NotNull @Valid ItemDto itemDto) {
        return client.preparedQuery("INSERT INTO items (name, description) VALUES (?, ?)").execute(Tuple.of(itemDto.getName(), itemDto.getDescription()))
                .onItem().transform(pgRowSet -> pgRowSet.iterator().next().getLong("id"));
    }

    /**
     * Update.
     *
     * @param itemDto the item dto
     * @return the uni
     */
    public Uni<Boolean> update(@Valid ItemDto itemDto) {
        return client.preparedQuery("UPDATE items SET name = ? WHERE id = ?").execute(Tuple.of(itemDto.getName(), itemDto.getDescription()))
                .onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
    }

    /**
     * Delete.
     *
     * @param id the id
     * @return the uni
     */
    public Uni<Boolean> delete(Long id) {
        return client.preparedQuery("DELETE FROM items WHERE id = ?").execute(Tuple.of(id))
                .onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
    }

    /**
     * From.
     *
     * @param row the row
     * @return the item dto
     */
    private static ItemDto from(Row row) {
        return new ItemDto(row.getLong("id"), row.getString("name"), row.getString("description"), row.getLocalDateTime("created_ts"));
    }
}
