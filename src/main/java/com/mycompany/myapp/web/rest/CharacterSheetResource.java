package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.domain.CharacterSheet;
import com.mycompany.myapp.repository.CharacterSheetRepository;
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
 * REST controller for managing CharacterSheet.
 */
@RestController
@RequestMapping("/api")
public class CharacterSheetResource {

    private final Logger log = LoggerFactory.getLogger(CharacterSheetResource.class);

    private static final String ENTITY_NAME = "characterSheet";

    private final CharacterSheetRepository characterSheetRepository;

    public CharacterSheetResource(CharacterSheetRepository characterSheetRepository) {
        this.characterSheetRepository = characterSheetRepository;
    }

    /**
     * POST  /character-sheets : Create a new characterSheet.
     *
     * @param characterSheet the characterSheet to create
     * @return the ResponseEntity with status 201 (Created) and with body the new characterSheet, or with status 400 (Bad Request) if the characterSheet has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/character-sheets")
    public ResponseEntity<CharacterSheet> createCharacterSheet(@RequestBody CharacterSheet characterSheet) throws URISyntaxException {
        log.debug("REST request to save CharacterSheet : {}", characterSheet);
        if (characterSheet.getId() != null) {
            throw new BadRequestAlertException("A new characterSheet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CharacterSheet result = characterSheetRepository.save(characterSheet);
        return ResponseEntity.created(new URI("/api/character-sheets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /character-sheets : Updates an existing characterSheet.
     *
     * @param characterSheet the characterSheet to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated characterSheet,
     * or with status 400 (Bad Request) if the characterSheet is not valid,
     * or with status 500 (Internal Server Error) if the characterSheet couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/character-sheets")
    public ResponseEntity<CharacterSheet> updateCharacterSheet(@RequestBody CharacterSheet characterSheet) throws URISyntaxException {
        log.debug("REST request to update CharacterSheet : {}", characterSheet);
        if (characterSheet.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CharacterSheet result = characterSheetRepository.save(characterSheet);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, characterSheet.getId().toString()))
            .body(result);
    }

    /**
     * GET  /character-sheets : get all the characterSheets.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of characterSheets in body
     */
    @GetMapping("/character-sheets")
    public List<CharacterSheet> getAllCharacterSheets() {
        log.debug("REST request to get all CharacterSheets");
        return characterSheetRepository.findAll();
    }

    /**
     * GET  /character-sheets/:id : get the "id" characterSheet.
     *
     * @param id the id of the characterSheet to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the characterSheet, or with status 404 (Not Found)
     */
    @GetMapping("/character-sheets/{id}")
    public ResponseEntity<CharacterSheet> getCharacterSheet(@PathVariable Long id) {
        log.debug("REST request to get CharacterSheet : {}", id);
        Optional<CharacterSheet> characterSheet = characterSheetRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(characterSheet);
    }

    /**
     * DELETE  /character-sheets/:id : delete the "id" characterSheet.
     *
     * @param id the id of the characterSheet to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/character-sheets/{id}")
    public ResponseEntity<Void> deleteCharacterSheet(@PathVariable Long id) {
        log.debug("REST request to delete CharacterSheet : {}", id);
        characterSheetRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
