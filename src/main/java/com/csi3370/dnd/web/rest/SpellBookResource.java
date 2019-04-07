package com.csi3370.dnd.web.rest;
import com.csi3370.dnd.domain.SpellBook;
import com.csi3370.dnd.repository.SpellBookRepository;
import com.csi3370.dnd.repository.search.SpellBookSearchRepository;
import com.csi3370.dnd.web.rest.errors.BadRequestAlertException;
import com.csi3370.dnd.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing SpellBook.
 */
@RestController
@RequestMapping("/api")
public class SpellBookResource {

    private final Logger log = LoggerFactory.getLogger(SpellBookResource.class);

    private static final String ENTITY_NAME = "spellBook";

    private final SpellBookRepository spellBookRepository;

    private final SpellBookSearchRepository spellBookSearchRepository;

    public SpellBookResource(SpellBookRepository spellBookRepository, SpellBookSearchRepository spellBookSearchRepository) {
        this.spellBookRepository = spellBookRepository;
        this.spellBookSearchRepository = spellBookSearchRepository;
    }

    /**
     * POST  /spell-books : Create a new spellBook.
     *
     * @param spellBook the spellBook to create
     * @return the ResponseEntity with status 201 (Created) and with body the new spellBook, or with status 400 (Bad Request) if the spellBook has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/spell-books")
    public ResponseEntity<SpellBook> createSpellBook(@RequestBody SpellBook spellBook) throws URISyntaxException {
        log.debug("REST request to save SpellBook : {}", spellBook);
        if (spellBook.getId() != null) {
            throw new BadRequestAlertException("A new spellBook cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SpellBook result = spellBookRepository.save(spellBook);
        spellBookSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/spell-books/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /spell-books : Updates an existing spellBook.
     *
     * @param spellBook the spellBook to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated spellBook,
     * or with status 400 (Bad Request) if the spellBook is not valid,
     * or with status 500 (Internal Server Error) if the spellBook couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/spell-books")
    public ResponseEntity<SpellBook> updateSpellBook(@RequestBody SpellBook spellBook) throws URISyntaxException {
        log.debug("REST request to update SpellBook : {}", spellBook);
        if (spellBook.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SpellBook result = spellBookRepository.save(spellBook);
        spellBookSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, spellBook.getId().toString()))
            .body(result);
    }

    /**
     * GET  /spell-books : get all the spellBooks.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of spellBooks in body
     */
    @GetMapping("/spell-books")
    public List<SpellBook> getAllSpellBooks(@RequestParam(required = false) String filter) {
        if ("book-is-null".equals(filter)) {
            log.debug("REST request to get all SpellBooks where book is null");
            return StreamSupport
                .stream(spellBookRepository.findAll().spliterator(), false)
                .filter(spellBook -> spellBook.getBook() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all SpellBooks");
        return spellBookRepository.findAll();
    }

    /**
     * GET  /spell-books/:id : get the "id" spellBook.
     *
     * @param id the id of the spellBook to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the spellBook, or with status 404 (Not Found)
     */
    @GetMapping("/spell-books/{id}")
    public ResponseEntity<SpellBook> getSpellBook(@PathVariable Long id) {
        log.debug("REST request to get SpellBook : {}", id);
        Optional<SpellBook> spellBook = spellBookRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(spellBook);
    }

    /**
     * DELETE  /spell-books/:id : delete the "id" spellBook.
     *
     * @param id the id of the spellBook to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/spell-books/{id}")
    public ResponseEntity<Void> deleteSpellBook(@PathVariable Long id) {
        log.debug("REST request to delete SpellBook : {}", id);
        spellBookRepository.deleteById(id);
        spellBookSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/spell-books?query=:query : search for the spellBook corresponding
     * to the query.
     *
     * @param query the query of the spellBook search
     * @return the result of the search
     */
    @GetMapping("/_search/spell-books")
    public List<SpellBook> searchSpellBooks(@RequestParam String query) {
        log.debug("REST request to search SpellBooks for query {}", query);
        return StreamSupport
            .stream(spellBookSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
