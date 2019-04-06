package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.domain.Spells;
import com.mycompany.myapp.repository.SpellsRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Spells.
 */
@RestController
@RequestMapping("/api")
public class SpellsResource {

    private final Logger log = LoggerFactory.getLogger(SpellsResource.class);

    private static final String ENTITY_NAME = "spells";

    private final SpellsRepository spellsRepository;

    public SpellsResource(SpellsRepository spellsRepository) {
        this.spellsRepository = spellsRepository;
    }

    /**
     * POST  /spells : Create a new spells.
     *
     * @param spells the spells to create
     * @return the ResponseEntity with status 201 (Created) and with body the new spells, or with status 400 (Bad Request) if the spells has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/spells")
    public ResponseEntity<Spells> createSpells(@RequestBody Spells spells) throws URISyntaxException {
        log.debug("REST request to save Spells : {}", spells);
        if (spells.getId() != null) {
            throw new BadRequestAlertException("A new spells cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Spells result = spellsRepository.save(spells);
        return ResponseEntity.created(new URI("/api/spells/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /spells : Updates an existing spells.
     *
     * @param spells the spells to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated spells,
     * or with status 400 (Bad Request) if the spells is not valid,
     * or with status 500 (Internal Server Error) if the spells couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/spells")
    public ResponseEntity<Spells> updateSpells(@RequestBody Spells spells) throws URISyntaxException {
        log.debug("REST request to update Spells : {}", spells);
        if (spells.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Spells result = spellsRepository.save(spells);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, spells.getId().toString()))
            .body(result);
    }

    /**
     * GET  /spells : get all the spells.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of spells in body
     */
    @GetMapping("/spells")
    public List<Spells> getAllSpells() {
        log.debug("REST request to get all Spells");
        return spellsRepository.findAll();
    }

    /**
     * GET  /spells/:id : get the "id" spells.
     *
     * @param id the id of the spells to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the spells, or with status 404 (Not Found)
     */
    @GetMapping("/spells/{id}")
    public ResponseEntity<Spells> getSpells(@PathVariable Long id) {
        log.debug("REST request to get Spells : {}", id);
        Optional<Spells> spells = spellsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(spells);
    }

    /**
     * DELETE  /spells/:id : delete the "id" spells.
     *
     * @param id the id of the spells to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/spells/{id}")
    public ResponseEntity<Void> deleteSpells(@PathVariable Long id) {
        log.debug("REST request to delete Spells : {}", id);
        spellsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
