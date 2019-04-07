package com.csi3370.dnd.web.rest;
import com.csi3370.dnd.domain.CharacterSheet;
import com.csi3370.dnd.repository.CharacterSheetRepository;
import com.csi3370.dnd.repository.search.CharacterSheetSearchRepository;
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
 * REST controller for managing CharacterSheet.
 */
@RestController
@RequestMapping("/api")
public class CharacterSheetResource {

    private final Logger log = LoggerFactory.getLogger(CharacterSheetResource.class);

    private static final String ENTITY_NAME = "characterSheet";

    private final CharacterSheetRepository characterSheetRepository;

    private final CharacterSheetSearchRepository characterSheetSearchRepository;

    public CharacterSheetResource(CharacterSheetRepository characterSheetRepository, CharacterSheetSearchRepository characterSheetSearchRepository) {
        this.characterSheetRepository = characterSheetRepository;
        this.characterSheetSearchRepository = characterSheetSearchRepository;
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
        characterSheetSearchRepository.save(result);
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
        characterSheetSearchRepository.save(result);
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
       // return characterSheetRepository.findAll();
	   return characterSheetRepository.findByUserIsCurrentUser();
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
        characterSheetSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/character-sheets?query=:query : search for the characterSheet corresponding
     * to the query.
     *
     * @param query the query of the characterSheet search
     * @return the result of the search
     */
    @GetMapping("/_search/character-sheets")
    public List<CharacterSheet> searchCharacterSheets(@RequestParam String query) {
        log.debug("REST request to search CharacterSheets for query {}", query);
        return StreamSupport
            .stream(characterSheetSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
