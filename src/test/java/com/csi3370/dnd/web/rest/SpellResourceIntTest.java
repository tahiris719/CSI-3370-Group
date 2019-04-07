package com.csi3370.dnd.web.rest;

import com.csi3370.dnd.DungeonsandDatabasesApp;

import com.csi3370.dnd.domain.Spell;
import com.csi3370.dnd.repository.SpellRepository;
import com.csi3370.dnd.repository.search.SpellSearchRepository;
import com.csi3370.dnd.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;


import static com.csi3370.dnd.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SpellResource REST controller.
 *
 * @see SpellResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DungeonsandDatabasesApp.class)
public class SpellResourceIntTest {

    private static final String DEFAULT_BOOK = "AAAAAAAAAA";
    private static final String UPDATED_BOOK = "BBBBBBBBBB";

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final String DEFAULT_SCHOOL = "AAAAAAAAAA";
    private static final String UPDATED_SCHOOL = "BBBBBBBBBB";

    private static final String DEFAULT_TIME = "AAAAAAAAAA";
    private static final String UPDATED_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_RANGE = "AAAAAAAAAA";
    private static final String UPDATED_RANGE = "BBBBBBBBBB";

    private static final String DEFAULT_COMPONENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMPONENTS = "BBBBBBBBBB";

    private static final String DEFAULT_DURATION = "AAAAAAAAAA";
    private static final String UPDATED_DURATION = "BBBBBBBBBB";

    private static final String DEFAULT_CLASSES = "AAAAAAAAAA";
    private static final String UPDATED_CLASSES = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private SpellRepository spellRepository;

    /**
     * This repository is mocked in the com.csi3370.dnd.repository.search test package.
     *
     * @see com.csi3370.dnd.repository.search.SpellSearchRepositoryMockConfiguration
     */
    @Autowired
    private SpellSearchRepository mockSpellSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restSpellMockMvc;

    private Spell spell;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SpellResource spellResource = new SpellResource(spellRepository, mockSpellSearchRepository);
        this.restSpellMockMvc = MockMvcBuilders.standaloneSetup(spellResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Spell createEntity(EntityManager em) {
        Spell spell = new Spell()
            .book(DEFAULT_BOOK)
            .level(DEFAULT_LEVEL)
            .school(DEFAULT_SCHOOL)
            .time(DEFAULT_TIME)
            .range(DEFAULT_RANGE)
            .components(DEFAULT_COMPONENTS)
            .duration(DEFAULT_DURATION)
            .classes(DEFAULT_CLASSES)
            .description(DEFAULT_DESCRIPTION)
            .name(DEFAULT_NAME);
        return spell;
    }

    @Before
    public void initTest() {
        spell = createEntity(em);
    }

    @Test
    @Transactional
    public void createSpell() throws Exception {
        int databaseSizeBeforeCreate = spellRepository.findAll().size();

        // Create the Spell
        restSpellMockMvc.perform(post("/api/spells")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(spell)))
            .andExpect(status().isCreated());

        // Validate the Spell in the database
        List<Spell> spellList = spellRepository.findAll();
        assertThat(spellList).hasSize(databaseSizeBeforeCreate + 1);
        Spell testSpell = spellList.get(spellList.size() - 1);
        assertThat(testSpell.getBook()).isEqualTo(DEFAULT_BOOK);
        assertThat(testSpell.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testSpell.getSchool()).isEqualTo(DEFAULT_SCHOOL);
        assertThat(testSpell.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testSpell.getRange()).isEqualTo(DEFAULT_RANGE);
        assertThat(testSpell.getComponents()).isEqualTo(DEFAULT_COMPONENTS);
        assertThat(testSpell.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testSpell.getClasses()).isEqualTo(DEFAULT_CLASSES);
        assertThat(testSpell.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSpell.getName()).isEqualTo(DEFAULT_NAME);

        // Validate the Spell in Elasticsearch
        verify(mockSpellSearchRepository, times(1)).save(testSpell);
    }

    @Test
    @Transactional
    public void createSpellWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = spellRepository.findAll().size();

        // Create the Spell with an existing ID
        spell.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSpellMockMvc.perform(post("/api/spells")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(spell)))
            .andExpect(status().isBadRequest());

        // Validate the Spell in the database
        List<Spell> spellList = spellRepository.findAll();
        assertThat(spellList).hasSize(databaseSizeBeforeCreate);

        // Validate the Spell in Elasticsearch
        verify(mockSpellSearchRepository, times(0)).save(spell);
    }

    @Test
    @Transactional
    public void getAllSpells() throws Exception {
        // Initialize the database
        spellRepository.saveAndFlush(spell);

        // Get all the spellList
        restSpellMockMvc.perform(get("/api/spells?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(spell.getId().intValue())))
            .andExpect(jsonPath("$.[*].book").value(hasItem(DEFAULT_BOOK.toString())))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].school").value(hasItem(DEFAULT_SCHOOL.toString())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())))
            .andExpect(jsonPath("$.[*].range").value(hasItem(DEFAULT_RANGE.toString())))
            .andExpect(jsonPath("$.[*].components").value(hasItem(DEFAULT_COMPONENTS.toString())))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION.toString())))
            .andExpect(jsonPath("$.[*].classes").value(hasItem(DEFAULT_CLASSES.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getSpell() throws Exception {
        // Initialize the database
        spellRepository.saveAndFlush(spell);

        // Get the spell
        restSpellMockMvc.perform(get("/api/spells/{id}", spell.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(spell.getId().intValue()))
            .andExpect(jsonPath("$.book").value(DEFAULT_BOOK.toString()))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.school").value(DEFAULT_SCHOOL.toString()))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.toString()))
            .andExpect(jsonPath("$.range").value(DEFAULT_RANGE.toString()))
            .andExpect(jsonPath("$.components").value(DEFAULT_COMPONENTS.toString()))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION.toString()))
            .andExpect(jsonPath("$.classes").value(DEFAULT_CLASSES.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSpell() throws Exception {
        // Get the spell
        restSpellMockMvc.perform(get("/api/spells/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSpell() throws Exception {
        // Initialize the database
        spellRepository.saveAndFlush(spell);

        int databaseSizeBeforeUpdate = spellRepository.findAll().size();

        // Update the spell
        Spell updatedSpell = spellRepository.findById(spell.getId()).get();
        // Disconnect from session so that the updates on updatedSpell are not directly saved in db
        em.detach(updatedSpell);
        updatedSpell
            .book(UPDATED_BOOK)
            .level(UPDATED_LEVEL)
            .school(UPDATED_SCHOOL)
            .time(UPDATED_TIME)
            .range(UPDATED_RANGE)
            .components(UPDATED_COMPONENTS)
            .duration(UPDATED_DURATION)
            .classes(UPDATED_CLASSES)
            .description(UPDATED_DESCRIPTION)
            .name(UPDATED_NAME);

        restSpellMockMvc.perform(put("/api/spells")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSpell)))
            .andExpect(status().isOk());

        // Validate the Spell in the database
        List<Spell> spellList = spellRepository.findAll();
        assertThat(spellList).hasSize(databaseSizeBeforeUpdate);
        Spell testSpell = spellList.get(spellList.size() - 1);
        assertThat(testSpell.getBook()).isEqualTo(UPDATED_BOOK);
        assertThat(testSpell.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testSpell.getSchool()).isEqualTo(UPDATED_SCHOOL);
        assertThat(testSpell.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testSpell.getRange()).isEqualTo(UPDATED_RANGE);
        assertThat(testSpell.getComponents()).isEqualTo(UPDATED_COMPONENTS);
        assertThat(testSpell.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testSpell.getClasses()).isEqualTo(UPDATED_CLASSES);
        assertThat(testSpell.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSpell.getName()).isEqualTo(UPDATED_NAME);

        // Validate the Spell in Elasticsearch
        verify(mockSpellSearchRepository, times(1)).save(testSpell);
    }

    @Test
    @Transactional
    public void updateNonExistingSpell() throws Exception {
        int databaseSizeBeforeUpdate = spellRepository.findAll().size();

        // Create the Spell

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpellMockMvc.perform(put("/api/spells")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(spell)))
            .andExpect(status().isBadRequest());

        // Validate the Spell in the database
        List<Spell> spellList = spellRepository.findAll();
        assertThat(spellList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Spell in Elasticsearch
        verify(mockSpellSearchRepository, times(0)).save(spell);
    }

    @Test
    @Transactional
    public void deleteSpell() throws Exception {
        // Initialize the database
        spellRepository.saveAndFlush(spell);

        int databaseSizeBeforeDelete = spellRepository.findAll().size();

        // Delete the spell
        restSpellMockMvc.perform(delete("/api/spells/{id}", spell.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Spell> spellList = spellRepository.findAll();
        assertThat(spellList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Spell in Elasticsearch
        verify(mockSpellSearchRepository, times(1)).deleteById(spell.getId());
    }

    @Test
    @Transactional
    public void searchSpell() throws Exception {
        // Initialize the database
        spellRepository.saveAndFlush(spell);
        when(mockSpellSearchRepository.search(queryStringQuery("id:" + spell.getId())))
            .thenReturn(Collections.singletonList(spell));
        // Search the spell
        restSpellMockMvc.perform(get("/api/_search/spells?query=id:" + spell.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(spell.getId().intValue())))
            .andExpect(jsonPath("$.[*].book").value(hasItem(DEFAULT_BOOK)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].school").value(hasItem(DEFAULT_SCHOOL)))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME)))
            .andExpect(jsonPath("$.[*].range").value(hasItem(DEFAULT_RANGE)))
            .andExpect(jsonPath("$.[*].components").value(hasItem(DEFAULT_COMPONENTS)))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION)))
            .andExpect(jsonPath("$.[*].classes").value(hasItem(DEFAULT_CLASSES)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Spell.class);
        Spell spell1 = new Spell();
        spell1.setId(1L);
        Spell spell2 = new Spell();
        spell2.setId(spell1.getId());
        assertThat(spell1).isEqualTo(spell2);
        spell2.setId(2L);
        assertThat(spell1).isNotEqualTo(spell2);
        spell1.setId(null);
        assertThat(spell1).isNotEqualTo(spell2);
    }
}
