package com.csi3370.dnd.web.rest;
import com.csi3370.dnd.domain.Spell;
import com.csi3370.dnd.repository.SpellRepository;
import com.csi3370.dnd.repository.search.SpellSearchRepository;
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
 * REST controller for managing Spell.
 */
@RestController
@RequestMapping("/api")
public class SpellResource {

    private final Logger log = LoggerFactory.getLogger(SpellResource.class);

    private static final String ENTITY_NAME = "spell";

    private final SpellRepository spellRepository;

    private final SpellSearchRepository spellSearchRepository;

    public SpellResource(SpellRepository spellRepository, SpellSearchRepository spellSearchRepository) {
        this.spellRepository = spellRepository;
        this.spellSearchRepository = spellSearchRepository;
    }

    /**
     * POST  /spells : Create a new spell.
     *
     * @param spell the spell to create
     * @return the ResponseEntity with status 201 (Created) and with body the new spell, or with status 400 (Bad Request) if the spell has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/spells")
    public ResponseEntity<Spell> createSpell(@RequestBody Spell spell) throws URISyntaxException {
        log.debug("REST request to save Spell : {}", spell);
        if (spell.getId() != null) {
            throw new BadRequestAlertException("A new spell cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Spell result = spellRepository.save(spell);
        spellSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/spells/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /spells : Updates an existing spell.
     *
     * @param spell the spell to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated spell,
     * or with status 400 (Bad Request) if the spell is not valid,
     * or with status 500 (Internal Server Error) if the spell couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/spells")
    public ResponseEntity<Spell> updateSpell(@RequestBody Spell spell) throws URISyntaxException {
        log.debug("REST request to update Spell : {}", spell);
        if (spell.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Spell result = spellRepository.save(spell);
        spellSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, spell.getId().toString()))
            .body(result);
    }

    /**
     * GET  /spells : get all the spells.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of spells in body
     */
    @GetMapping("/spells")
    public List<Spell> getAllSpells() {
        log.debug("REST request to get all Spells");
        return spellRepository.findAll();
    }

    /**
     * GET  /spells/:id : get the "id" spell.
     *
     * @param id the id of the spell to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the spell, or with status 404 (Not Found)
     */
    @GetMapping("/spells/{id}")
    public ResponseEntity<Spell> getSpell(@PathVariable Long id) {
        log.debug("REST request to get Spell : {}", id);
        Optional<Spell> spell = spellRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(spell);
    }

    /**
     * DELETE  /spells/:id : delete the "id" spell.
     *
     * @param id the id of the spell to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/spells/{id}")
    public ResponseEntity<Void> deleteSpell(@PathVariable Long id) {
        log.debug("REST request to delete Spell : {}", id);
        spellRepository.deleteById(id);
        spellSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/spells?query=:query : search for the spell corresponding
     * to the query.
     *
     * @param query the query of the spell search
     * @return the result of the search
     */
    @GetMapping("/_search/spells")
    public List<Spell> searchSpells(@RequestParam String query) {
        log.debug("REST request to search Spells for query {}", query);
        return StreamSupport
            .stream(spellSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
