package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.DungeonsAndDatabasesApp;

import com.mycompany.myapp.domain.Spells;
import com.mycompany.myapp.repository.SpellsRepository;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

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
import java.util.List;


import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SpellsResource REST controller.
 *
 * @see SpellsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DungeonsAndDatabasesApp.class)
public class SpellsResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

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

    @Autowired
    private SpellsRepository spellsRepository;

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

    private MockMvc restSpellsMockMvc;

    private Spells spells;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SpellsResource spellsResource = new SpellsResource(spellsRepository);
        this.restSpellsMockMvc = MockMvcBuilders.standaloneSetup(spellsResource)
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
    public static Spells createEntity(EntityManager em) {
        Spells spells = new Spells()
            .name(DEFAULT_NAME)
            .book(DEFAULT_BOOK)
            .level(DEFAULT_LEVEL)
            .school(DEFAULT_SCHOOL)
            .time(DEFAULT_TIME)
            .range(DEFAULT_RANGE)
            .components(DEFAULT_COMPONENTS)
            .duration(DEFAULT_DURATION)
            .classes(DEFAULT_CLASSES)
            .description(DEFAULT_DESCRIPTION);
        return spells;
    }

    @Before
    public void initTest() {
        spells = createEntity(em);
    }

    @Test
    @Transactional
    public void createSpells() throws Exception {
        int databaseSizeBeforeCreate = spellsRepository.findAll().size();

        // Create the Spells
        restSpellsMockMvc.perform(post("/api/spells")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(spells)))
            .andExpect(status().isCreated());

        // Validate the Spells in the database
        List<Spells> spellsList = spellsRepository.findAll();
        assertThat(spellsList).hasSize(databaseSizeBeforeCreate + 1);
        Spells testSpells = spellsList.get(spellsList.size() - 1);
        assertThat(testSpells.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSpells.getBook()).isEqualTo(DEFAULT_BOOK);
        assertThat(testSpells.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testSpells.getSchool()).isEqualTo(DEFAULT_SCHOOL);
        assertThat(testSpells.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testSpells.getRange()).isEqualTo(DEFAULT_RANGE);
        assertThat(testSpells.getComponents()).isEqualTo(DEFAULT_COMPONENTS);
        assertThat(testSpells.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testSpells.getClasses()).isEqualTo(DEFAULT_CLASSES);
        assertThat(testSpells.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createSpellsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = spellsRepository.findAll().size();

        // Create the Spells with an existing ID
        spells.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSpellsMockMvc.perform(post("/api/spells")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(spells)))
            .andExpect(status().isBadRequest());

        // Validate the Spells in the database
        List<Spells> spellsList = spellsRepository.findAll();
        assertThat(spellsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSpells() throws Exception {
        // Initialize the database
        spellsRepository.saveAndFlush(spells);

        // Get all the spellsList
        restSpellsMockMvc.perform(get("/api/spells?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(spells.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].book").value(hasItem(DEFAULT_BOOK.toString())))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].school").value(hasItem(DEFAULT_SCHOOL.toString())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())))
            .andExpect(jsonPath("$.[*].range").value(hasItem(DEFAULT_RANGE.toString())))
            .andExpect(jsonPath("$.[*].components").value(hasItem(DEFAULT_COMPONENTS.toString())))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION.toString())))
            .andExpect(jsonPath("$.[*].classes").value(hasItem(DEFAULT_CLASSES.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getSpells() throws Exception {
        // Initialize the database
        spellsRepository.saveAndFlush(spells);

        // Get the spells
        restSpellsMockMvc.perform(get("/api/spells/{id}", spells.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(spells.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.book").value(DEFAULT_BOOK.toString()))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.school").value(DEFAULT_SCHOOL.toString()))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.toString()))
            .andExpect(jsonPath("$.range").value(DEFAULT_RANGE.toString()))
            .andExpect(jsonPath("$.components").value(DEFAULT_COMPONENTS.toString()))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION.toString()))
            .andExpect(jsonPath("$.classes").value(DEFAULT_CLASSES.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSpells() throws Exception {
        // Get the spells
        restSpellsMockMvc.perform(get("/api/spells/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSpells() throws Exception {
        // Initialize the database
        spellsRepository.saveAndFlush(spells);

        int databaseSizeBeforeUpdate = spellsRepository.findAll().size();

        // Update the spells
        Spells updatedSpells = spellsRepository.findById(spells.getId()).get();
        // Disconnect from session so that the updates on updatedSpells are not directly saved in db
        em.detach(updatedSpells);
        updatedSpells
            .name(UPDATED_NAME)
            .book(UPDATED_BOOK)
            .level(UPDATED_LEVEL)
            .school(UPDATED_SCHOOL)
            .time(UPDATED_TIME)
            .range(UPDATED_RANGE)
            .components(UPDATED_COMPONENTS)
            .duration(UPDATED_DURATION)
            .classes(UPDATED_CLASSES)
            .description(UPDATED_DESCRIPTION);

        restSpellsMockMvc.perform(put("/api/spells")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSpells)))
            .andExpect(status().isOk());

        // Validate the Spells in the database
        List<Spells> spellsList = spellsRepository.findAll();
        assertThat(spellsList).hasSize(databaseSizeBeforeUpdate);
        Spells testSpells = spellsList.get(spellsList.size() - 1);
        assertThat(testSpells.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSpells.getBook()).isEqualTo(UPDATED_BOOK);
        assertThat(testSpells.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testSpells.getSchool()).isEqualTo(UPDATED_SCHOOL);
        assertThat(testSpells.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testSpells.getRange()).isEqualTo(UPDATED_RANGE);
        assertThat(testSpells.getComponents()).isEqualTo(UPDATED_COMPONENTS);
        assertThat(testSpells.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testSpells.getClasses()).isEqualTo(UPDATED_CLASSES);
        assertThat(testSpells.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingSpells() throws Exception {
        int databaseSizeBeforeUpdate = spellsRepository.findAll().size();

        // Create the Spells

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpellsMockMvc.perform(put("/api/spells")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(spells)))
            .andExpect(status().isBadRequest());

        // Validate the Spells in the database
        List<Spells> spellsList = spellsRepository.findAll();
        assertThat(spellsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSpells() throws Exception {
        // Initialize the database
        spellsRepository.saveAndFlush(spells);

        int databaseSizeBeforeDelete = spellsRepository.findAll().size();

        // Delete the spells
        restSpellsMockMvc.perform(delete("/api/spells/{id}", spells.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Spells> spellsList = spellsRepository.findAll();
        assertThat(spellsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Spells.class);
        Spells spells1 = new Spells();
        spells1.setId(1L);
        Spells spells2 = new Spells();
        spells2.setId(spells1.getId());
        assertThat(spells1).isEqualTo(spells2);
        spells2.setId(2L);
        assertThat(spells1).isNotEqualTo(spells2);
        spells1.setId(null);
        assertThat(spells1).isNotEqualTo(spells2);
    }
}
